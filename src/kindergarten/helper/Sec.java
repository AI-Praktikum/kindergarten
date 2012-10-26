/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import kindergarten.model.Elternteil;
import kindergarten.model.Gruppe;
import kindergarten.model.Kind;
import kindergarten.model.Preismodell;
import kindergarten.model.Warteliste;


public class Sec {
    public static void insertNewChild(String vorname, String nachname, String gebDat, Elternteil eltern, Object p, Object[] groups){
        if(isValidDate(gebDat) && (validPreismodell(p)) && validGroups(groups) ){
//                for(Object o : groups){
//                    if(!isValidGroupSize((String)o)){
//                        System.out.println("Aaaaaand it's gone");
//                        showMsgBox("Kein Platz in Gruppe "+((Gruppe)o).toString());
//                        return;
//                    }
//                }
            try {
                System.out.println("new child aufruf in sec");
                DBKind.newKind(vorname, nachname, gebDat, eltern, p, groups);
            } catch (ParseException ex) {
               //error Message
            }
        }else{
            //error Message
        }
    }
    
    private static boolean validPreismodell(Object p){
        if(p == null) return false;
        if(p instanceof Preismodell){
            return true;
        }else{
            System.out.println("Invalid Preismodell");
            return false;
        }
    }
    
    private static boolean validGroups(Object[] groups){
        for(Object o : groups){
            if(o == null) return false;
            if((!(o instanceof Gruppe))&& (!(o instanceof Warteliste))){
                return false;
            }
        }
        System.out.println("valid groups");
        return true;
        
            
    }
  
    private static boolean isValidDate(String d){
       if (d == null) return false;

        //set the format to use as a constructor argument
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        if (d.trim().length() != dateFormat.toPattern().length())
          return false;

        dateFormat.setLenient(false);

        try {
          //parse the inDate parameter
          dateFormat.parse(d.trim());
        }
        catch (Exception pe) {
          return false;
        }
        System.out.println("Valid Date");
        return true;
    }
    
    private static boolean isValidGroupSize(String s){
        Gruppe g = DBGruppe.getGroupByName(s);
        if(g == null) return false;
        return (g.getGruppengroesse().compareTo(new BigInteger(String.valueOf(g.getKindCollection().size())))) == 1;
    }
    
    private static void showMsgBox(String s){
        JOptionPane.showMessageDialog(null, s, "InfoBox: Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}
