/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import kindergarten.model.Elternteil;


/**
 *
 * @author andy
 */
public class DBElternteil {
    public static Elternteil newElternteil(String name, Object famgr, Object n, String adr){
        
        long gr = ((Number) famgr).intValue(); 
        long netto = ((Number) n).intValue(); 
        
        EntityManager em = DBhelpers.getEntityManager();
        
        
        EntityTransaction entr = em.getTransaction();
        entr.begin();
        
        long nextId = DBhelpers.nextElternteilIdent();
        Elternteil e = new Elternteil();
        e.setIdent(nextId);
        e.setAdresse(adr);
        e.setFamiliengroesse(gr);
        e.setName(name);
        e.setNettoeinkommen(netto);
        e.setFamiliengroesse(netto);
        em.persist(e);
        entr.commit();
        
        return e;
    }
}
