/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.swing.DefaultListModel;
import kindergarten.model.Gruppe;
import kindergarten.model.Kind;
import kindergarten.model.Kindergarten;
import kindergarten.model.Warteliste;

/**
 *
 * @author andy
 */
public class DBGruppe {
    public static BigInteger frueh = new BigInteger("1");
    public static BigInteger vormittags = new BigInteger("2");
    public static BigInteger nachmittags = new BigInteger("3");
    public static BigInteger spaet = new BigInteger("4");
    public static BigInteger ganztags = new BigInteger("5");
    
    private static String kindergartensize = "20";
    
    public static void insertNewGrp(Object groesse, Object inserttyp, String bezeichnung){
        
        String typ = (String) inserttyp;
        long gr = ((Number) groesse).intValue(); 
        
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
                
        
        //if(checkGroupSize(bigtyp, biggroesse)){

            EntityManager em = DBhelpers.getEntityManager();
            TypedQuery<Kindergarten> queryk = em.createNamedQuery("Kindergarten.findByIdent", Kindergarten.class);
            TypedQuery<Warteliste> queryw = em.createNamedQuery("Warteliste.findByIdent", Warteliste.class);


            queryw.setParameter("ident", bigtyp);
            queryk.setParameter("ident", new BigInteger("1"));
            Kindergarten kresult = queryk.getSingleResult();
            Warteliste wresult = queryw.getSingleResult();

            EntityTransaction entr = em.getTransaction();
            entr.begin();

            Gruppe g = new Gruppe();
            g.setGruppengroesse(gr);
            g.setIdent(DBhelpers.nextGruppeIdent());
            g.setKindergartenId(kresult);
            g.setWartelisteId(wresult);
            g.setBezeichnung(bezeichnung);
            em.persist(g);
            entr.commit();
        //}
    }
    
    public static List<Gruppe> getAllGroups(){
        EntityManager em = DBhelpers.getEntityManager();
        TypedQuery<Gruppe> queryg = em.createNamedQuery("Gruppe.findAll", Gruppe.class);
        
        List<Gruppe> result = queryg.getResultList();
        
        return result;
    }
    
    
    public static void deleteFromGroup(Kind child, Gruppe gruppe) {
        boolean valid = false;
        System.out.println(child);
        System.out.println(gruppe);
        for(Gruppe g : child.getGruppeCollection()){
            if(g.equals(gruppe))valid = true;
            break;
        }
        if(valid){
            DBJdbc db = DBhelpers.getDatabase();
            String kind = child.getIdent().toString();
            String gr = gruppe.getIdent().toString();
            String s = "Delete from kind_gruppe where kind_id = " + kind + " and gruppe_id = " + gr;
            try {
                db.delete(s);
            } catch (SQLException ex) {
                Logger.getLogger(DBGruppe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        

    public static List<Gruppe> getFreeGroups(){
        
        List<Gruppe> all = getAllGroups();
        
        List<Gruppe> result = new ArrayList<Gruppe>();
        
        for(Gruppe g : all){
            int size = ((Number)g.getGruppengroesse()).intValue();
            System.out.println("Size: " + size);
            System.out.println("Size KindCollection: " + g.getKindCollection().size());
            
            if(size > g.getKindCollection().size()){
                result.add(g);
            }
        }
        return result;
    }
    
    public static Gruppe getGroupByName(String name){
        EntityManager em = DBhelpers.getEntityManager();
        TypedQuery<Gruppe> queryg = em.createNamedQuery("Gruppe.findByBezeichnung", Gruppe.class);
        
        queryg.setParameter("bezeichnung", name);
        try{
            Gruppe result = queryg.getSingleResult();

            return result;
        }catch(Exception e){
            return null;
        }
    }
    
    public static List<Gruppe> getGroupByType(long type){
        List<Gruppe> gl = getAllGroups();
        List<Gruppe> result = new ArrayList<Gruppe>();
        
        for(Gruppe g : gl){
            if(g.getWartelisteId().getIdent().equals(type)){
                result.add(g);
            }
        }
        return result;
        
    }
    
    public static List<Kind> getKindCollectionByBezeichnung(String bez){
        Gruppe g = getGroupByName(bez);
        List<Kind> result = new ArrayList<Kind>();
        result.addAll(g.getKindCollection());
        return result;
    }
    
//    private boolean checkGroupSize(BigInteger typ, BigInteger gr){
//        BigInteger cnt = new BigInteger("0");
//        BigInteger kindergarten = new BigInteger(kindergartensize);
//        if(typ.equals(DBGruppe.frueh)){
//            List<Gruppe> fg = getGroupByType(DBGruppe.frueh);
//            for(Gruppe g : fg){
//                cnt = cnt.add(g.getGruppengroesse());
//            }
//            if()
//        }
//        
//    }

    static List<Gruppe> getGroupsByKind(Kind k) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
  
    
}
