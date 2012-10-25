/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import kindergarten.model.Elternteil;
import kindergarten.model.Gruppe;
import kindergarten.model.Kind;
import kindergarten.model.Preismodell;
import kindergarten.model.Registrierung;
import kindergarten.model.Warteliste;

/**
 *
 * @author andy
 */
public class DBKind {
   
    
    public static void newKind(String vorname, String nachname, String gebDat, Elternteil eltern, Object p, Object[] groups) throws ParseException{
        
        
        
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        
        Date geb = DBhelpers.stringToDate(gebDat);
        
        BigDecimal nextId = DBhelpers.nextKindIdent();
        
        EntityTransaction entr = em.getTransaction();
        entr.begin();
        
        Kind k = new Kind();
        
        List<Gruppe> gl = new ArrayList<Gruppe>();
        List<Registrierung> reg = new ArrayList<Registrierung>();
        Warteliste wl;
        Date now = new Date();
        
        
        
        k.setElternteilId(eltern);
        k.setVorname(vorname);
        k.setNachname(nachname);
        k.setGeburtsdatum(geb);
        k.setIdent(nextId);
        k.setPreismodellId((Preismodell)p);
        int hashv = hashV(eltern, nachname, vorname, geb, nextId);
        BigInteger hash = new BigInteger(String.valueOf(hashv));
        k.setHashvalue(hash);
        em.persist(k);
        entr.commit();
        
        for(Object o : groups){
            String s = (String)o;
            
            if(s.startsWith("Warteliste")){
                reg.add(DBRegistrierung.insertNewReg(k, DBWarteliste.getWartelisteByName(s), now));
            }else{
                gl.add(DBGruppe.getGroupByName(s));
            }
            
        }
        
        String[] logIn = Files.readAll("C:\\Users\\sebastian\\Desktop\\pwd.txt").split(" ");
        DBJdbc db = new DBJdbc(logIn[0],logIn[1]);
        
        for(Gruppe g: gl){
            String gruppe = g.getIdent().toString();
            String kind = nextId.toString();
            System.out.println(gruppe);
            System.out.println(kind);
            String s = "Insert into kind_gruppe values("+gruppe+","+kind+")";
            System.out.println(s);
            try {
                db.update(s);
            } catch (SQLException ex) {
                Logger.getLogger(DBKind.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
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
    
    public static Kind getByVorNachname(String nachname, String vorname){
        List<Kind> kl;
        
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Kind> queryk = em.createNamedQuery("Kind.findByNachname", Kind.class);
        
        queryk.setParameter("nachname", nachname);
        
        kl = queryk.getResultList();
        
        for(Kind k : kl){
            if(k.getVorname().equals(vorname)) return k;
        }
        return null;
        
    }
    
    public static void shift(String child, Gruppe oldGroup, Gruppe newGroup){
        String[] nameParts = child.split(",");
        System.out.println(nameParts);
        Kind k = getByVorNachname(nameParts[0], nameParts[1]);
        System.out.println("Kind: "+k);
        DBGruppe.deleteFromGroup(k, oldGroup);
        
    }
}