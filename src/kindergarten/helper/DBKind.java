/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import kindergarten.model.Elternteil;
import kindergarten.model.Kind;

/**
 *
 * @author andy
 */
public class DBKind {
    public static void newKind(String vorname, String nachname, String gebDat, Elternteil eltern) throws ParseException{
        
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        
        Date geb = DBhelpers.stringToDate(gebDat);
        
        BigDecimal nextId = DBhelpers.nextIdent("Kind", Kind.class);
        
        EntityTransaction entr = em.getTransaction();
        entr.begin();
        
        Kind k = new Kind();
        k.setElternteilId(eltern);
        k.setVorname(vorname);
        k.setNachname(nachname);
        k.setGeburtsdatum(geb);
        k.setIdent(nextId);
        int hashv = hashV(eltern, nachname, vorname, geb, nextId);
        BigInteger hash = new BigInteger(String.valueOf(hashv));
        k.setHashvalue(hash);
        em.persist(k);
        entr.commit();
    }
    
    private static int hashV(Elternteil e, String n, String v, Date g, BigDecimal id){
        int hash = 1;
        hash = hash * 17 + e.hashCode();
        hash = hash * 31 + n.hashCode();
        hash = hash * 13 + v.hashCode();
        hash = hash * 17 + g.hashCode();
        hash = hash * 31 + id.hashCode();
        return hash;
    }
}
