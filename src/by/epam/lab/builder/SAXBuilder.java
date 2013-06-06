/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.builder;

import by.epam.lab.model.Medicament;
import by.epam.lab.model.Medicine;
import by.epam.lab.parser.sax.MedicineHandler;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import xmlparser.XmlParser;

/**
 *
 * @author Soldier
 */
public class SAXBuilder extends Builder{

    public SAXBuilder(File fileXML) {
        super(fileXML);
    }

    
    @Override
    public Medicine getMedicine() {
         Medicine medicine = new Medicine();
        try {
              XMLReader  reader = XMLReaderFactory.createXMLReader();
            MedicineHandler  medicineHandler = new MedicineHandler();
            reader.setContentHandler(medicineHandler);
             reader.parse(getFileXML().getName());
              List<Medicament> medicaments= medicine.getMedication();
              medicaments.addAll(medicineHandler.getMedicine());
           
        } catch (IOException ex) {
            Logger.getLogger(SAXBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(SAXBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  medicine;
    }
    
}
