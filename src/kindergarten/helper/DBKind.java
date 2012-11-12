/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        
        long nextId = DBhelpers.nextKindIdent();
        
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
    
    public static void insertInGroup(Kind child, Gruppe gruppe){
        DBJdbc db = DBhelpers.getDatabase();
        String g = gruppe.getIdent().toString();
        String ch = child.getIdent().toString();
        String s = "Insert into kind_gruppe values("+g+","+ch+")";
        try{
            db.update(s);
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
        boolean valid = false;
        System.out.println(child);
        System.out.println(gruppe);
        for(Gruppe g : child.getGruppeCollection()){
            if(g.equals(gruppe))valid = true;
            break;
        }
        if(valid){
            DBJdbc db = DBhelpers.getDatabase();
            String kind = child.getIdent().toString();
            String gr = gruppe.getIdent().toString();
            String s = "Delete from kind_gruppe where kind_id = " + kind + " and gruppe_id = " + gr;
            try {
                db.delete(s);
            } catch (SQLException ex) {
                Logger.getLogger(DBGruppe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        List<Registrierung> registrierungen = DBRegistrierung.getWartelistenByKind(k);
        List<Gruppe> gruppen = DBGruppe.getGroupsByKind(k);
        
        for(Registrierung r : registrierungen){
            DBRegistrierung.deleteReg(r);
        }
        for(Gruppe g : gruppen){
            DBKind.deleteFromGroup(k, g);
        }
    }
}