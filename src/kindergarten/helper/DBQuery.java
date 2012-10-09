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
import javax.persistence.TypedQuery;
import kindergarten.model.Kind;
import kindergarten.model.Gruppe;
import kindergarten.model.Kindergarten;
import kindergarten.model.Warteliste;
/**
 *
 * @author andy
 */
public class DBQuery {
    
    public static BigInteger frueh = new BigInteger("1");
    public static BigInteger vormittags = new BigInteger("2");
    public static BigInteger nachmittags = new BigInteger("3");
    public static BigInteger spaet = new BigInteger("4");
    public static BigInteger ganztags = new BigInteger("5");
    
    public static void insertNewGrp(Object groesse, Object inserttyp, String bezeichnung){
        
        String typ = (String) inserttyp;
        int gr = ((Number) groesse).intValue(); 
        
        BigInteger bigtyp;
        if(typ.equals("Fruehgruppe")){
            bigtyp = frueh;
        }
        else if(typ.equals("Vormittagsgruppe")){
            bigtyp = vormittags;
        }
        else if(typ.equals("Nachmittagsgruppe")){
            bigtyp = nachmittags;
        }
        else if(typ.equals("Spaetgruppe")){
            bigtyp = spaet;
        }
        else{
            bigtyp = ganztags;
        }
        
        System.out.println("Typ: "+ bigtyp);
        
        BigInteger biggroesse = new BigInteger(String.valueOf(gr));
        
        System.out.println("Groesse: "+ biggroesse);
        
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Kindergarten> queryk = em.createNamedQuery("Kindergarten.findByIdent", Kindergarten.class);
        TypedQuery<Warteliste> queryw = em.createNamedQuery("Warteliste.findByIdent", Warteliste.class);
        TypedQuery<Gruppe> queryg = em.createNamedQuery("Gruppe.findAll", Gruppe.class);
        
        queryw.setParameter("ident", bigtyp);
        queryk.setParameter("ident", new BigInteger("1"));
        Kindergarten kresult = queryk.getSingleResult();
        Warteliste wresult = queryw.getSingleResult();

        List<Gruppe> grps = queryg.getResultList();
        BigDecimal maxID = new BigDecimal("0");
        for(Gruppe elem : grps){
            if(elem.getIdent().compareTo(maxID) == 1)maxID = elem.getIdent();
        }
        
        
        System.out.println(maxID);
        
        EntityTransaction entr = em.getTransaction();
        entr.begin();
        
        Gruppe g = new Gruppe();
        g.setGruppengroesse(biggroesse);
        g.setIdent(maxID.add(new BigDecimal("1")));
        g.setKindergartenId(kresult);
        g.setWartelisteId(wresult);
        g.setBezeichnung(bezeichnung);
        em.persist(g);
        entr.commit();
    }
    
}
