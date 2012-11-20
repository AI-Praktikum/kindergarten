/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;


public class Sec {
    
  
    public static boolean isValidDate(String d){
       if (d == null) {
            return false;
        }

        //set the format to use as a constructor argument
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        if (d.trim().length() != dateFormat.toPattern().length()) {
            return false;
        }

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
    
    public static void showMsgBox(String s){
        JOptionPane.showMessageDialog(null, s, "InfoBox: Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}
