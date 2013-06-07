/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.builder;

import by.epam.lab.model.Medicament;
import by.epam.lab.model.Medicine;
import by.epam.lab.parser.sax.MedicineHandler;
import by.epam.lab.util.validator.XmlValidator;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Soldier
 */
public class SAXBuilder extends Builder{

    public SAXBuilder(String filename) {
        super(filename);
    }

    

    
    @Override
    public Medicine getMedicine() {
      
        try {
             ResourceBundle resource = ResourceBundle.getBundle("propertie.configuration");
            
            if (XmlValidator.validate(getFileName(), resource.getString("xsd")))
                {
                   Medicine medicine = new Medicine();
              XMLReader  reader = XMLReaderFactory.createXMLReader();
            MedicineHandler  medicineHandler = new MedicineHandler();
            reader.setContentHandler(medicineHandler);
             reader.parse(getFileName());
              List<Medicament> medicaments= medicine.getMedication();
              medicaments.addAll(medicineHandler.getMedicine());
               return  medicine;
                }
                      
        } catch (IOException | SAXException ex) {
            Logger.getLogger(SAXBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
       return  null;
    }
    
}
