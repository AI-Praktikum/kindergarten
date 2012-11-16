/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.DefaultListModel;
import kindergarten.model.Gruppe;
import kindergarten.model.Warteliste;

/**
 *
 * @author andy
 */
public class DBhelpers {
    public static EntityManager em;
    
    static{
        Map<String, String> propMap = DBLogin.getPropMap();
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU", propMap);
        em = emf.createEntityManager();
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
        
        return em;
    }
            
        
    
    public static DBJdbc getDatabase(){
        DBJdbc db = new DBJdbc();
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
    
    
    public static Date stringToDate(String geb) throws ParseException{
        
        SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
        
        Date d = sf.parse(geb);
        
        return d;
    }
    
    private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }
    
    public static String getFacebookIdFromUrl(String url) {
        InputStream is;
        url = url.replaceAll("www","graph");
        if(url.startsWith("graph")){
            url = "http://" + url;
        }
        try {
            is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String text = readAll(rd);
            String[] attributes = text.split(",");
            return attributes[0].split(":")[1].replaceAll("\"", "");            
        } catch (Exception e) {
            return null;
        } 
    }
   
    
    
    
    public static void main(String args[]){
        
    }
   
//    
}
