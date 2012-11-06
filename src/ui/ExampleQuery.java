/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import kindergarten.model.Kind;
import kindergarten.model.Gruppe;
import kindergarten.model.Kindergarten;
import kindergarten.model.Warteliste;


/**
 *
 * @author egon
 */
public class ExampleQuery {

    public static void main(String[] args){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        // die namedQueries stehen in den einzelnen Klassen
        TypedQuery<Kind> query = em.createNamedQuery("Kind.findAll", Kind.class);
        List<Kind> results = query.getResultList();
        for(Kind kind: results){
            System.out.println(kind.getElternteilid());
        }
        System.out.println(results.toString());
    }
}
