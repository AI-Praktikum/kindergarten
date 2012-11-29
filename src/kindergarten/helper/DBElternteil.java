/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import kindergarten.model.Elternteil;


/**
 *
 * @author andy
 */
public class DBElternteil {
    public static Elternteil newElternteil(String name, Object famgr, Object n, String adr, String facebookUrl){
        String facebookId;
        long gr = ((Number) famgr).intValue(); 
        long netto = ((Number) n).intValue(); 
        
        facebookId = DBhelpers.getFacebookIdFromUrl(facebookUrl);
        
        EntityTransaction entr = DBhelpers.em.getTransaction();
        entr.begin();
        
        Elternteil e = new Elternteil();
        e.setFacebookId(facebookId);
        e.setAdresse(adr);
        e.setFamiliengroesse(gr);
        e.setName(name);
        e.setNettoeinkommen(netto);
        e.setFamiliengroesse(netto);
        DBhelpers.em.persist(e);
        entr.commit();
        
        return e;
    }
    
//    public static void deleteElternteil(Elternteil e){
//        if(e.getKindCollection().isEmpty()){
//            DBJdbc db = DBhelpers.getDatabase();
//            String elternteil = e.getIdent().toString();
//            String s = "Delete from elternteil where ident = " + elternteil;
//            try {
//              db.delete(s);
//              System.out.println("Ausgeführt: "+s);
//            } catch (SQLException ex) {
//               Logger.getLogger(DBGruppe.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    public static void deleteElternteil(Elternteil e){
        DBhelpers.em.getTransaction().begin();
        e = DBhelpers.em.merge(e);
        DBhelpers.em.remove(e);
        DBhelpers.em.getTransaction().commit();
    }
    
}
