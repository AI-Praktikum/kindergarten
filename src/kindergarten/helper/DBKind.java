/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import kindergarten.model.Elternteil;
import kindergarten.model.Gruppe;
import kindergarten.model.Kind;
import kindergarten.model.Preismodell;
import kindergarten.model.Registrierung;
import kindergarten.model.Warteliste;

/**
 *
 * @author andy
 */
public class DBKind {
   
    
    public static void newKind(String vorname, String nachname, String gebDat, Elternteil eltern, Object p, Object[] groups) throws ParseException{
        EntityManager em = DBhelpers.getEntityManager();
        
        Date geb = DBhelpers.stringToDate(gebDat);
        
        long nextId = nextKindIdent();
        
        EntityTransaction entr = em.getTransaction();
        entr.begin();
        
        Kind k = new Kind();
        
        List<Gruppe> gl = new ArrayList<Gruppe>();
        Date now = new Date();
        
        
        
        k.setElternteilid(eltern);
        k.setVorname(vorname);
        k.setNachname(nachname);
        k.setGeburtsdatum(geb);
        k.setIdent(nextId);
        k.setPreismodellId((Preismodell)p);
        long hashv = hashV(eltern, nachname, vorname, geb, nextId);
        k.setHashValue(hashv);
        em.persist(k);
        entr.commit();
        
        for(Object o : groups){            
            if(o instanceof Warteliste){
                DBRegistrierung.insertNewReg(k, (Warteliste)o, now);
            }else{
                gl.add((Gruppe)o);
            }            
        }       
        
        for(Gruppe g: gl){
            insertInGroup(k,g);
        }
    }
    
    private static void deleteFromDB(Kind k){
        Elternteil e = k.getElternteilid();
        DBJdbc db = DBhelpers.getDatabase();
        String ident = k.getIdent().toString();
        String sql = "Delete from kind where ident = " + ident;
        try {
                db.delete(sql);
                e.getKindCollection().remove(k);
            } catch (SQLException ex) {
                Logger.getLogger(DBGruppe.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void insertInGroup(Kind child, Gruppe gruppe){
        DBJdbc db = DBhelpers.getDatabase();
        String g = gruppe.getIdent().toString();
        String ch = child.getIdent().toString();
        String s = "Insert into kind_gruppe values("+g+","+ch+")";
        try{
            db.update(s);
            child.getGruppeCollection().add(gruppe);
            gruppe.getKindCollection().add(child);
        }catch(SQLException ex){
            Logger.getLogger(DBKind.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static long hashV(Elternteil e, String n, String v, Date g, long id){
        long hash = 1;
        hash = hash * 17 + e.hashCode();
        hash = hash * 31 + n.hashCode();
        hash = hash * 13 + v.hashCode();
        hash = hash * 17 + g.hashCode();
        hash = hash * 31 + id;
        return hash;
    }
    
    public static Kind getByVorNachname(String nachname, String vorname){
        List<Kind> kl;
        
        EntityManager em = DBhelpers.getEntityManager();
        TypedQuery<Kind> queryk = em.createNamedQuery("Kind.findByNachname", Kind.class);
        
        queryk.setParameter("nachname", nachname);
        
        kl = queryk.getResultList();
        
        for(Kind k : kl){
            if(k.getVorname().equals(vorname)) return k;
        }
        return null;
        
    }
    

    public static void shift(Kind k, Gruppe oldGroup, Gruppe newGroup){
        DBGruppe.deleteFromGroup(k, oldGroup);
        DBKind.insertInGroup(k, newGroup);   
    }
    
    public static void shift(Kind child, Gruppe oldGroup, Warteliste wl){
        DBGruppe.deleteFromGroup(child, oldGroup);
        DBRegistrierung.insertNewReg(child, wl, new Date());
    }

    public static void deleteFromGroup(Kind child, Gruppe gruppe) {
        DBJdbc db = DBhelpers.getDatabase();
        String kind = child.getIdent().toString();
        String gr = gruppe.getIdent().toString();
        String s = "Delete from kind_gruppe where kind_id = " + kind + " and gruppe_id = " + gr;
        try {
           db.delete(s);
           child.getGruppeCollection().remove(gruppe);
           gruppe.getKindCollection().remove(child);
        } catch (SQLException ex) {
           Logger.getLogger(DBGruppe.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
    private static long nextKindIdent(){
        
        EntityManager em = DBhelpers.getEntityManager();
        
        TypedQuery<Kind> queryk = em.createNamedQuery("Kind.findAll", Kind.class);
        
        List<Kind> kinder = queryk.getResultList();
        long maxID = 0;
        for(Kind elem : kinder){
            if(elem.getIdent().compareTo(maxID) == 1)maxID = elem.getIdent();
        }
        
        return maxID+1;
    }
    
    
    public static void shift(Registrierung r, Warteliste source, Warteliste target){
        Kind k = r.getKind();
        DBRegistrierung.deleteReg(r);
        DBRegistrierung.insertNewReg(k, target,new Date());
    }
    
    public static void shift(Registrierung r, Warteliste source, Gruppe target){
        Kind k = r.getKind();
        DBRegistrierung.deleteReg(r);
        DBKind.insertInGroup(k, target);
    }
    
    public static Kind getByIdent(BigDecimal ident){
        Kind result;
        
        EntityManager em = DBhelpers.getEntityManager();
        TypedQuery<Kind> queryk = em.createNamedQuery("Kind.findByIdent", Kind.class);
        
        queryk.setParameter("ident", ident);
        
        result = queryk.getSingleResult();
        return result;
    }

    public static void completeDeletion(Kind k) {
        Collection<Registrierung> registrierungen;
        Collection<Gruppe> gruppen;
        try{
            registrierungen = k.getRegistrierungCollection();
        }catch(Exception e){
            registrierungen = null;
        }
        if(registrierungen != null){
            for(Registrierung r : registrierungen){
                DBRegistrierung.deleteReg(r);
            }
        }
        
        try{
            gruppen = k.getGruppeCollection();
        }catch(Exception e){
            gruppen = null;
        }
        
        System.out.println("Gruppen: "+ gruppen.size());
        if(gruppen != null){
           for(Gruppe g : gruppen){
                DBKind.deleteFromGroup(k, g);
            }
        }
        Elternteil e = k.getElternteilid();
        deleteFromDB(k);
        DBElternteil.deleteElternteil(e);
       
       
        
        
        
    }
}