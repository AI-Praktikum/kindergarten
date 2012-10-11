/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import kindergarten.model.Kind;

/**
 *
 * @author andy
 */
public class DBhelpers {
    
    public static BigDecimal nextIdent(String modelName, Class klasse){
        
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Kind> queryk = em.createNamedQuery(modelName+".findAll", klasse);
        
        List<Kind> kinder = queryk.getResultList();
        BigDecimal maxID = new BigDecimal("0");
        for(Kind elem : kinder){
            if(elem.getIdent().compareTo(maxID) == 1)maxID = elem.getIdent();
        }
        
        return maxID.add(new BigDecimal("1"));
    }
    
    public static Date stringToDate(String geb) throws ParseException{
        
        SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
        
        Date d = sf.parse(geb);
        
        return d;
    }
    
//    private boolean checkDate(String s){
//        String[] ls = s.split(".");
//        
//        if(ls.length != 3){
//            return false;
//        }
//   
//    }
}
