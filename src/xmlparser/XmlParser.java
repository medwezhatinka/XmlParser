/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlparser;

import by.epam.lab.parser.MedicineHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.spi.XmlReader;
import org.netbeans.xml.schema.medicineschema.Medicament;
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
    public static void main(String[] args) {
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
    }
}
