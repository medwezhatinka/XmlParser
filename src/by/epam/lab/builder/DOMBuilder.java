/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.builder;

import by.epam.lab.model.Medicament;
import by.epam.lab.model.Medicine;
import by.epam.lab.parser.dom.Analyzer;
import java.io.File;
import java.io.IOException;
import java.util.List;
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

    public DOMBuilder(File fileXML) {
        super(fileXML);
    }

    @Override
    public Medicine getMedicine() {
        Medicine  medicine = new Medicine();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(getFileXML());
            Element root = document.getDocumentElement();
            List<Medicament> medicaments = medicine.getMedication();
            medicaments.addAll(Analyzer.listBuilder(root));
           
        } catch (SAXException ex) {
            Logger.getLogger(DOMBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DOMBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOMBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
 return medicine;

    }
}

