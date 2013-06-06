/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.builder;

import by.epam.lab.model.Medicament;
import by.epam.lab.model.Medicine;
import by.epam.lab.parser.stax.MedicineSTAXParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author Soldier
 */
public class STAXBuilder extends Builder {

    public STAXBuilder(File fileXML) {
        super(fileXML);
    }

    @Override
    public Medicine getMedicine() {
        Medicine medicine = new Medicine();
        InputStream input = null;
        try {

            input = new FileInputStream(getFileXML());
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLStreamReader reader1 = inputFactory.createXMLStreamReader(input);
            MedicineSTAXParser parser = new MedicineSTAXParser(reader1);
            List<Medicament> medicaments = medicine.getMedication();
            medicaments.addAll(parser.getMedicine());
        } catch (XMLStreamException ex) {
            Logger.getLogger(STAXBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(STAXBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(STAXBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return medicine;
    }
}
