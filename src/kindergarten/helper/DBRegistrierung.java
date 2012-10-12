/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
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
        
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        
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
    
    public static List<Gruppe> getAllGroups(){
        
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Gruppe> queryg = em.createNamedQuery("Gruppe.findAll", Gruppe.class);
        
        List<Gruppe> result = queryg.getResultList();
        
        return result;
    }
}
