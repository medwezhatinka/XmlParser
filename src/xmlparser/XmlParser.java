/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlparser;

import by.epam.lab.parser.sax.MedicineHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import by.epam.lab.model.Medicament;
import by.epam.lab.parser.dom.Analyzer;
import by.epam.lab.parser.stax.MedicineSTAXParser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Alina_Shumel
 */
public class XmlParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        // TODO code application logic here
        XMLReader reader;
        MedicineHandler sp = null;
        try {
            reader = XMLReaderFactory.createXMLReader();
        
        sp = new MedicineHandler();
        reader.setContentHandler(sp);
            try {
                reader.parse("src\\MedicineSchema.xml");
            } catch (IOException ex) {
                Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SAXException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
       ArrayList<Medicament> l = sp.getMedicine();
        ArrayList<Medicament> m;
       try {
       
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
            DocumentBuilder db = dbf.newDocumentBuilder();
            
            Document document = db.parse("src\\MedicineSchema.xml");
            
             Element root = document.getDocumentElement();
      m = Analyzer.listBuilder(root);
             
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
        MedicineSTAXParser parser = new MedicineSTAXParser();
        InputStream input = new FileInputStream("src\\MedicineSchema.xml");
        parser.parse(input);
    }
}
