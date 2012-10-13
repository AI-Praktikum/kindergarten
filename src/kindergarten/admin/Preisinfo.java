/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.text.AttributeSet;

/**
 *
 * @author Philipp
 * 
 * schreibt datei ins verzeichnis der ausgeführten jar.
 * 
 */
public class Preisinfo {
    
    /**
     * creates file with info
     * 
     * @param info 
     * @return when successfull: directory and file name of the written file, otherwise null
     */
    public static String write(String info){
        String dir = "Preisinfo"; // bei änderung hier auch gitignore ändern!
        String file = "preisinfo.txt";
        // String file = createFileName(info); // namen aus kindinfo erzeugen (zb id, vor und nachmame)
        String dirAndFile = dir + "/" + file;
        
        boolean success = false;
        
        // ordner erstellen
        try {
            success = (new File(dir)).mkdir();
        }
        catch (Exception ex) {
            success = false;
            System.out.println("fehler beim erstellen des ordners");
            ex.printStackTrace();
        }
        
        // in datei schreiben
        if (success){
            try {
                PrintWriter out = new PrintWriter(new FileWriter(dirAndFile));
                out.println(info);
                out.close();
            } catch (IOException ex){
                success = false;
                System.out.println("fehler beim schreiben der datei");
                ex.printStackTrace();
            }
        }
        
        if (success){
            return dirAndFile;
        }
        else{
            return null;
        }
    }
    
    /**
     * prints the info AND creates a file
     * 
     * @param info
     * @return true if successfull, false if not
     */
    public static boolean print(String info){
        boolean success = false;
        
        String dirAndFile = write(info);
        
        // printService AttributeSet
        HashAttributeSet psaSet = new HashAttributeSet();
        // printRequest AttributeSet
        PrintRequestAttributeSet praSet = new HashPrintRequestAttributeSet();
        
        FileInputStream textStream = null;
        try{
            success = true;
            textStream = new FileInputStream(dirAndFile);

            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc mydoc = new SimpleDoc(textStream, flavor, null);

            PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, psaSet);
            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();

            if(services.length == 0) {
                if(defaultService == null) {
                      //no printer found
                } else {
                     //print using default
                     DocPrintJob job = defaultService.createPrintJob();
                     job.print(mydoc, praSet);
                }
             } else {
                //built in UI for printing you may not use this
                PrintService service = ServiceUI.printDialog(null, 200, 200, services, defaultService, flavor, praSet);
                 if (service != null)
                 {
                    DocPrintJob job = service.createPrintJob();
                    job.print(mydoc, praSet);
                 }
             }
        } catch (Exception ex){
            success = false;
            ex.printStackTrace();
        }
        
        return success;
    }
}
