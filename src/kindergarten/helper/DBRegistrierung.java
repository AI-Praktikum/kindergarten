/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import kindergarten.model.Kind;
import kindergarten.model.Registrierung;
import kindergarten.model.RegistrierungPK;
import kindergarten.model.Warteliste;

/**
 *
 * @author andy
 */
public class DBRegistrierung {
    public static Registrierung insertNewReg(Kind k, Warteliste w, Date d){
        
        EntityTransaction entr = DBhelpers.em.getTransaction();
        entr.begin();
        
        Registrierung r = new Registrierung();
        r.setDatumRegistrierung(d);
        r.setKind(k);
        //r.setWarteliste(w);
        r.setRegistrierungPK(new RegistrierungPK(k.getIdent(),w.getIdent()));
        
        DBhelpers.em.persist(r);
        entr.commit();
        
        return r;
    }
    
    public static List<Registrierung> getRegistrierungenByWarteliste(Warteliste w){
        
        TypedQuery<Registrierung> queryk = DBhelpers.em.createNamedQuery("Registrierung.findByWartelisteId", Registrierung.class);
        
        queryk.setParameter("wartelisteId", w.getIdent());
        
        return queryk.getResultList();
    }
    
  
    
//    public static void deleteReg(Registrierung r){
//        EntityManager em = DBhelpers.getEntityManager();
//        TypedQuery<Warteliste> queryg = em.createNamedQuery("Warteliste.findByIdent", Warteliste.class);
//        
//        long id = r.getRegistrierungPK().getWartelisteId();
//        queryg.setParameter("ident", id);
//        Warteliste w = queryg.getSingleResult();
//        
//        Kind k = r.getKind();
//        DBJdbc db = DBhelpers.getDatabase();
//        String kind = k.getIdent().toString();
//        String gr = w.getIdent().toString();
//        String s = "Delete from registrierung where kind_id = " + kind + " and warteliste_id = " + gr;
//            try {
//                db.delete(s);
//            } catch (SQLException ex) {
//                Logger.getLogger(DBGruppe.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    }
    
     public static void deleteReg(Registrierung r){
         DBhelpers.em.merge(r);
         DBhelpers.em.getTransaction().begin();
         DBhelpers.em.remove(r);
         DBhelpers.em.getTransaction().commit();
     }

    
    

    
}
