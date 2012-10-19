/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import kindergarten.model.Elternteil;
import kindergarten.model.Gruppe;
import kindergarten.model.Kind;
import kindergarten.model.Preismodell;


public class Sec {
    public static void insertNewChild(String vorname, String nachname, String gebDat, Elternteil eltern, Object p, Object[] groups){
        if(isValidDate(gebDat) && (validPreismodell(p)) && validGroups(groups) ){
            try {
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
            return false;
        }
    }
    
    private static void showErrorDialog(String errorMessage){
        ErrorDialog e = new ErrorDialog(,true);
    }
    
    private static boolean validGroups(Object[] groups){
        for(Object o : groups){
            if(o == null) return false;
            if(!(o instanceof Gruppe)) return false;
        }
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
        return true;
    }
    
    
    
}
