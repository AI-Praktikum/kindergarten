/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
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
        
        EntityManager em = DBhelpers.getEntityManager();
        
        
        EntityTransaction entr = em.getTransaction();
        entr.begin();
        
        long nextId = nextElternteilIdent();
        Elternteil e = new Elternteil();
        e.setIdent(nextId);
        e.setFacebookId(facebookId);
        e.setAdresse(adr);
        e.setFamiliengroesse(gr);
        e.setName(name);
        e.setNettoeinkommen(netto);
        e.setFamiliengroesse(netto);
        em.persist(e);
        entr.commit();
        
        return e;
    }
    
    
    private static long nextElternteilIdent(){
        
        EntityManager em = DBhelpers.getEntityManager();
        
        TypedQuery<Elternteil> queryk = em.createNamedQuery("Elternteil.findAll", Elternteil.class);
        
        List<Elternteil> kinder = queryk.getResultList();
        long maxID = 0;
        for(Elternteil elem : kinder){
            if(elem.getIdent().compareTo(maxID) == 1)maxID = elem.getIdent();
        }
        
        return maxID+1;
    }
}
