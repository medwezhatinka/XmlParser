/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.util.validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author Soldier
 */
public class XmlValidator {
    
     private static String invalidMessage = null;

    public static String getInvalidMessage() {
        return invalidMessage;
    }

    public static boolean validate(String fileName, String schemaName) throws FileNotFoundException, SAXException, IOException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaLocation = new File(schemaName);
       
            Schema schema = factory.newSchema(schemaLocation);
           Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
            return true;
      
}
}
