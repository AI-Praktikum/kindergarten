/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
        
        BigDecimal nextId = DBhelpers.nextIdent("Kind", Kind.class);
        
        EntityTransaction entr = em.getTransaction();
        entr.begin();
        
        Kind k = new Kind();
        
        List<Gruppe> gl = new ArrayList<Gruppe>();
        List<Registrierung> reg = new ArrayList<Registrierung>();
        Warteliste wl;
        Date now = new Date();
        for(Object o : groups){
            String s = (String)o;
            
            // String an Datenbank anpassen!
            
            if(s.equals("Warteliste Frueh")){
                reg.add(DBRegistrierung.insertNewReg(k, DBWarteliste.getWartelisteByName(s), now));
            }else if(s.equals("Warteliste Vormittag")){
                reg.add(DBRegistrierung.insertNewReg(k, DBWarteliste.getWartelisteByName(s), now));
            }else if(s.equals("Warteliste Nachmittag")){
                reg.add(DBRegistrierung.insertNewReg(k, DBWarteliste.getWartelisteByName(s), now));
            }else if(s.equals("Warteliste Spaet")){
                reg.add(DBRegistrierung.insertNewReg(k, DBWarteliste.getWartelisteByName(s), now));
            }else if(s.equals("Warteliste Ganztag")){
                reg.add(DBRegistrierung.insertNewReg(k, DBWarteliste.getWartelisteByName(s), now));
            }else{
                gl.add(DBGruppe.getGroupByName(s));
            }
            
        }
        
        
        k.setElternteilId(eltern);
        k.setVorname(vorname);
        k.setNachname(nachname);
        k.setGeburtsdatum(geb);
        k.setIdent(nextId);
        k.setPreismodellId((Preismodell)p);
        k.setGruppeCollection(gl);
        k.setRegistrierungCollection(reg);
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
