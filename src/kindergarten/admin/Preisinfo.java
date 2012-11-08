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
import kindergarten.helper.Files;
import kindergarten.model.Elternteil;
import kindergarten.model.Kind;

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
    public static String write(String info, String childName){
        String dir = "Preisinfo"; // bei änderung hier auch gitignore ändern!
        String file = childName+".txt";
        String fileSeparator = System.getProperty("file.separator");
        // String file = createFileName(info); // namen aus kindinfo erzeugen (zb id, vor und nachmame)
        String dirAndFile = dir + fileSeparator + file;
        System.out.println(dirAndFile);//test
        
        boolean success = false;
        
        // ordner erstellen
        try {
            File f = new File(dir);
            success = f.exists();
            if (!success){
                success = f.mkdir();
            }
        }
        catch (Exception ex) {
            success = false;
            System.out.println("fehler beim erstellen des ordners");
            ex.printStackTrace();
        }
        
        // in datei schreiben
        //TODO: wenn datei existiert wird sie noch nicht überschrieben!
        if (success){
            try {
//                File myFile = new File(dirAndFile);
//                Writer output = new BufferedWriter(new FileWriter(myFile));
//                output.write(info);
//                output.close();
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
    
    public static String buildBill(String contactPerson, String address, String child, String birthdate, String fee){
        String result = "";
        String lineSeparator = System.getProperty("line.separator");
        result += "An:\tFrau/Herr " + contactPerson;
        result += lineSeparator + "\t"+address;
        result += lineSeparator+lineSeparator+lineSeparator+"Sehr geehrte/r Frau/Herr " + contactPerson + ",";
        result += lineSeparator+lineSeparator + "dies ist die Rechnung ueber die Gebuehr fuer die Kindergartenbetreuung" + lineSeparator + "Ihres Kindes:";
        result += lineSeparator+lineSeparator + "\t" + child +",";
        result += lineSeparator + "\t" + "Geboren am: " + birthdate;
        result += lineSeparator+lineSeparator+"Die Gebuehr betraegt " + fee + " Euro.";
        result += lineSeparator+"Bitte begleichen Sie unverzueglich diese Rechnung.";
        result += lineSeparator+lineSeparator+"Mit freundlichen Gruessen,";
        result += lineSeparator+"Ihr Team vom SuperKiGaV :-)";
        System.out.println(result);//test
        return result;
    }
    
    /**
     * prints the info AND creates a file
     * 
     * @param info
     * @return true if successfull, false if not
     */
    public static boolean print(String info, String childName){
        boolean success = false;
        
        String dirAndFile = write(info, childName);
        
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
    
    public static long getPrice(Kind k){
        long preis = 0;
        
        Elternteil kind_eltern = k.getElternteilid();
        String preismodell = k.getPreismodellId().getBezeichnung();
        int kind_fg = (int)kind_eltern.getFamiliengroesse();
        long kind_netto = kind_eltern.getNettoeinkommen();
        
        long[][] preisliste = readPriceList(preismodell);
        for (int  i = 0; i < preisliste.length && preisliste[i][0] < kind_netto; i++){
            preis = preisliste[i][kind_fg-1]; // familiengröße 2 -> feld nr 2 -> 0-basiert 1
        }
        
        return preis;
    }
    
    private static long[][] readPriceList(String list){
        String preislisteString = Files.readAll("Preislisten"+System.getProperty("file.separator")+list+".csv");
        String[] stringZeilen = preislisteString.split(System.getProperty("line.separator"));
        long[][] preisliste = new long[stringZeilen.length - 1][6];
        // durch zeilen iterieren
        for (int i = 1; i < stringZeilen.length; i++){
            String[] stringZeile = stringZeilen[i].split(","); // hier ändern falls kommawerte in csv und trennzeichen ein anderes zeichen
            // durch spalten iterieren
            for (int j = 0; j < stringZeile.length; j++){
                preisliste[i-1][j] = Long.valueOf(stringZeile[j]);
            }
        }
        return preisliste;
    }
}
