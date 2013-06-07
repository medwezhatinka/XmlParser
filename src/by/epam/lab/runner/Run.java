/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.runner;

import by.epam.lab.model.Medicine;
import by.epam.lab.parser.ParserManager;
import by.epam.lab.util.transformer.Transform;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Alina_Shumel
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
       
        try {        
            // TODO code application logic here
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             System.out.print("Enter name of xml file:  ");
             String filename = bufferedReader.readLine().trim();
            System.out.print("Enter name  of parser:  ");
            String parser = bufferedReader.readLine().trim();
            ParserManager parserManager = new ParserManager(filename);
            Medicine medicine = parserManager.parseXml(parser);
            System.out.println(medicine.showMedicine());
        } catch (IllegalArgumentException ex) {
            System.out.println("Illegal argument! ");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
      
       
       
      
    }
}
