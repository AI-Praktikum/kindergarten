/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.swing.DefaultListModel;
import kindergarten.model.Elternteil;
import kindergarten.model.Gruppe;
import kindergarten.model.Kind;
import kindergarten.model.Warteliste;

/**
 *
 * @author andy
 */
public class DBhelpers {
    
    public static BigDecimal nextKindIdent(){
        
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Kind> queryk = em.createNamedQuery("Kind.findAll", Kind.class);
        
        List<Kind> kinder = queryk.getResultList();
        BigDecimal maxID = new BigDecimal("0");
        for(Kind elem : kinder){
            if(elem.getIdent().compareTo(maxID) == 1)maxID = elem.getIdent();
        }
        
        return maxID.add(new BigDecimal("1"));
    }
    public static BigDecimal nextGruppeIdent(){
        
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Gruppe> queryk = em.createNamedQuery("Gruppe.findAll", Gruppe.class);
        
        List<Gruppe> kinder = queryk.getResultList();
        BigDecimal maxID = new BigDecimal("0");
        for(Gruppe elem : kinder){
            if(elem.getIdent().compareTo(maxID) == 1)maxID = elem.getIdent();
        }
        
        return maxID.add(new BigDecimal("1"));
    }
    
    public static DefaultListModel lmFreeGroupsAndWartelisten(){
        DefaultListModel lm = new DefaultListModel();
        List<Object> free = freeGroupsAndWartelisten_String();
        
        for(Object o : free){
            lm.addElement(o);
        }
        
        return lm;
    }
    
    public static EntityManager getEntityManager(){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        return em;
    }
            
        
    
    public static DBJdbc  getDatabase(){
        String workingDir = System.getProperty("user.dir");
        String[] logIn = Files.readAll(workingDir+"\\pwd.txt").split(" ");
        DBJdbc db = new DBJdbc(logIn[0],logIn[1]);
        return db;
    }
    
    public static List<Object> freeGroupsAndWartelisten_String(){
        List<Object> result = new ArrayList<Object>();
        List<Gruppe> free = DBGruppe.getFreeGroups();
        
        result.addAll(free);
        List<Warteliste> wl = DBWarteliste.getAll();
        result.addAll(wl);
        
        return result;
    }
    public static BigDecimal nextWartelisteIdent(){
        
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Warteliste> queryk = em.createNamedQuery("Warteliste.findAll", Warteliste.class);
        
        List<Warteliste> kinder = queryk.getResultList();
        BigDecimal maxID = new BigDecimal("0");
        for(Warteliste elem : kinder){
            if(elem.getIdent().compareTo(maxID) == 1)maxID = elem.getIdent();
        }
        
        return maxID.add(new BigDecimal("1"));
    }
    public static BigDecimal nextElternteilIdent(){
        
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Elternteil> queryk = em.createNamedQuery("Elternteil.findAll", Elternteil.class);
        
        List<Elternteil> kinder = queryk.getResultList();
        BigDecimal maxID = new BigDecimal("0");
        for(Elternteil elem : kinder){
            if(elem.getIdent().compareTo(maxID) == 1)maxID = elem.getIdent();
        }
        
        return maxID.add(new BigDecimal("1"));
    }
    
    public static Date stringToDate(String geb) throws ParseException{
        
        SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
        
        Date d = sf.parse(geb);
        
        return d;
    }
   
   
//    
}
