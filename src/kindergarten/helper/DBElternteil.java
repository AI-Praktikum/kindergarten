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
        
        int gr = ((Number) famgr).intValue(); 
        BigInteger bigGr = new BigInteger(String.valueOf(gr));
        int netto = ((Number) n).intValue(); 
        BigInteger bigNetto = new BigInteger(String.valueOf(netto));
        
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        
        
        EntityTransaction entr = em.getTransaction();
        entr.begin();
        
        BigDecimal nextId = DBhelpers.nextIdent("Elternteil", Elternteil.class);
        Elternteil e = new Elternteil();
        e.setIdent(nextId);
        e.setAdresse(adr);
        e.setFamiliengroesse(bigGr);
        e.setName(name);
        e.setNettoeinkommen(bigNetto);
        e.setFamiliengroesse(bigNetto);
        em.persist(e);
        entr.commit();
        
        return e;
    }
}
