/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import kindergarten.model.Warteliste;

/**
 *
 * @author andy
 */
public class DBWarteliste {
    
    public static Warteliste getWartelisteByName(String typ){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Warteliste> queryw = em.createNamedQuery("Warteliste.findByWartelistentyp", Warteliste.class);
        
        queryw.setParameter("wartelistentyp", typ);
        Warteliste result;
        try{
            result = queryw.getSingleResult();
        }catch(Exception e){
            result = null;
        }
        return result;
    }
    
    
    public static List<Warteliste> getAll(){
        EntityManager em = DBhelpers.getEntityManager();
        TypedQuery<Warteliste> queryw = em.createNamedQuery("Warteliste.findAll", Warteliste.class);
        return queryw.getResultList();
        
    }
    
    
}
