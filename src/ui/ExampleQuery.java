/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import kindergarten.model.Kind;

/**
 *
 * @author egon
 */
public class ExampleQuery {

    public static void main(String[] args){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("kindergartenPU");
        EntityManager em = emf.createEntityManager();
        // die namedQueries stehen in den einzelnen Klassen
        TypedQuery<Kind> query = em.createNamedQuery("Kind.findAll", Kind.class);
        List<Kind> results = query.getResultList();
        System.out.println(results.toString());
    }
}
