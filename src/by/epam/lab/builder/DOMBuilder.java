/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.builder;

import by.epam.lab.model.Medicament;
import by.epam.lab.model.Medicine;
import by.epam.lab.parser.dom.Analyzer;
import by.epam.lab.util.validator.XmlValidator;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Soldier
 */
public class DOMBuilder extends Builder {

    public DOMBuilder(String filename) {
        super(filename);
    }

   

    @Override
    public Medicine getMedicine() {
       
        try {
            ResourceBundle resource = ResourceBundle.getBundle("configuration");
            
            if (XmlValidator.validate(getFileName(), resource.getString("xsd")))
                {
                
           Medicine  medicine = new Medicine();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(getFileName());
            Element root = document.getDocumentElement();
            List<Medicament> medicaments = medicine.getMedication();
            medicaments.addAll(Analyzer.listBuilder(root));
            return medicine;
             }
           
             
           
        } catch (SAXException ex) {
            Logger.getLogger(DOMBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DOMBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOMBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
 
   return null;
    }
}

