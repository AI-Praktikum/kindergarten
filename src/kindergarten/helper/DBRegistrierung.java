/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import kindergarten.model.Gruppe;
import kindergarten.model.Kind;
import kindergarten.model.Kindergarten;
import kindergarten.model.Registrierung;
import kindergarten.model.RegistrierungPK;
import kindergarten.model.Warteliste;

/**
 *
 * @author andy
 */
public class DBRegistrierung {
    public static Registrierung insertNewReg(Kind k, Warteliste w, Date d){
        EntityManager em = DBhelpers.getEntityManager();
        
        EntityTransaction entr = em.getTransaction();
        entr.begin();
        
        Registrierung r = new Registrierung();
        r.setDatumRegistrierung(d);
        r.setKind(k);
        r.setWarteliste(w);
        r.setRegistrierungPK(new RegistrierungPK(k.getIdent().toBigInteger(),w.getIdent().toBigInteger()));
        
        em.persist(r);
        entr.commit();
        
        return r;
    }
    
//    public static void deleteReg(Registrierung r){
//        EntityManager em = getEntityManager();
//  try{
//    em.getTransaction().begin();
//    Users userx = em.find(Users.class, user.getUserId());
//    em.remove(userx); 
//    em.getTransaction().commit();
//  } finally {
//    em.close();
//    return false;
//  }
//    }
    
    public static List<Gruppe> getAllGroups(){
        EntityManager em = DBhelpers.getEntityManager();
        TypedQuery<Gruppe> queryg = em.createNamedQuery("Gruppe.findAll", Gruppe.class);
        
        List<Gruppe> result = queryg.getResultList();
        
        return result;
    }
    
    public static void deleteReg(Registrierung r){
        Kind k = r.getKind();
        Warteliste w = r.getWarteliste();
        DBJdbc db = DBhelpers.getDatabase();
        String kind = k.getIdent().toString();
        String gr = w.getIdent().toString();
        String s = "Delete from registrierung where kind_id = " + kind + " and warteliste_id = " + gr;
            try {
                db.delete(s);
            } catch (SQLException ex) {
                Logger.getLogger(DBGruppe.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
