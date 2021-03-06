/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
        
        Date geb = DBhelpers.stringToDate(gebDat);
                
        EntityTransaction entr = DBhelpers.em.getTransaction();
        entr.begin();
        
        Kind k = new Kind();
        
        List<Gruppe> gl = new ArrayList<Gruppe>();      
        
        
        k.setElternteilid(eltern);
        k.setVorname(vorname);
        k.setNachname(nachname);
        k.setGeburtsdatum(geb);
        k.setPreismodellId((Preismodell)p);
        long hashv = hashV(eltern, nachname, vorname, geb);
        k.setHashValue(hashv);
        DBhelpers.em.persist(k);
        entr.commit();
        
        for(Object o : groups){            
            if(o instanceof Warteliste){
                DBRegistrierung.insertNewReg(k, (Warteliste)o, new Date());
            }else{
                gl.add((Gruppe)o);
            }            
        }       
        
        for(Gruppe g: gl){
            insertInGroup(k,g);
        }
    }
    
//    private static void deleteFromDB(Kind k){
//        Elternteil e = k.getElternteilid();
//        DBJdbc db = DBhelpers.getDatabase();
//        String ident = k.getIdent().toString();
//        String sql = "Delete from kind where ident = " + ident;
//        try {
//                db.delete(sql);
//                e.getKindCollection().remove(k);
//            } catch (SQLException ex) {
//                Logger.getLogger(DBGruppe.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    }
    
    private static void deleteFromDB(Kind k){
        Elternteil e = k.getElternteilid();
        DBhelpers.em.getTransaction().begin();
        k = DBhelpers.em.merge(k);
        DBhelpers.em.remove(k);
        DBhelpers.em.getTransaction().commit(); 
//        if(e.getKindCollection().isEmpty()){
//            DBElternteil.deleteElternteil(e);
//        }
        
    }
    
    public static void insertInGroup(Kind child, Gruppe gruppe){
        Collection<Gruppe> gruppen = child.getGruppeCollection();
        gruppen.add(gruppe);
        Collection<Kind> kinder  = gruppe.getKindCollection();
        kinder.add(child);
        DBhelpers.em.getTransaction().begin();
        child.setGruppeCollection(gruppen);
        DBhelpers.em.merge(child);
        gruppe.setKindCollection(kinder);
        DBhelpers.em.merge(child);
        DBhelpers.em.getTransaction().commit();        
    }
    
    private static long hashV(Elternteil e, String n, String v, Date g){
        long hash = 1;
        hash = hash * 17 + e.hashCode();
        hash = hash * 31 + n.hashCode();
        hash = hash * 13 + v.hashCode();
        hash = hash * 17 + g.hashCode();
        return hash;
    }
    
    public static Kind getByVorNachname(String nachname, String vorname){
        List<Kind> kl;
        
        TypedQuery<Kind> queryk = DBhelpers.em.createNamedQuery("Kind.findByNachname", Kind.class);
        
        queryk.setParameter("nachname", nachname);
        
        kl = queryk.getResultList();
        
        for(Kind k : kl){
            if(k.getVorname().equals(vorname)) {
                return k;
            }
        }
        return null;
        
    }
    

    public static void shift(Kind k, Gruppe oldGroup, Gruppe newGroup){
        deleteFromGroup(k, oldGroup);
        DBKind.insertInGroup(k, newGroup);   
    }
    
    public static void shift(Kind child, Gruppe oldGroup, Warteliste wl){
        deleteFromGroup(child, oldGroup);
        DBRegistrierung.insertNewReg(child, wl, new Date());
    }
    
    public static void deleteFromGroup(Kind child, Gruppe gruppe) {
        
        Collection<Gruppe> gruppen = child.getGruppeCollection();
        gruppen.remove(gruppe);
        
        Collection<Kind> kinder = gruppe.getKindCollection();
        kinder.remove(child);

        
        DBhelpers.em.getTransaction().begin();
        child.setGruppeCollection(gruppen);
        DBhelpers.em.merge(child);
        gruppe.setKindCollection(kinder);
        DBhelpers.em.merge(gruppe);
        DBhelpers.em.getTransaction().commit(); 
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
        
        TypedQuery<Kind> queryk = DBhelpers.em.createNamedQuery("Kind.findByIdent", Kind.class);
        
        queryk.setParameter("ident", ident);
        
        result = queryk.getSingleResult();
        return result;
    }

    public static void completeDeletion(Kind k) throws IllegalArgumentException{
        if(k.getGruppeCollection().isEmpty() && k.getRegistrierungCollection().isEmpty()){
            deleteFromDB(k);
        }else{
            throw new IllegalArgumentException("Kind darf nicht mehr in Gruppe oder Warteliste sein");
        }
        
    }
    
}