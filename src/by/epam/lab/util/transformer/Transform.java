/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.util.transformer;

import java.util.ResourceBundle;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Soldier
 */
public class Transform {
    
    public  static void transformXml(String input, String output) throws TransformerConfigurationException, TransformerException{
        ResourceBundle resource = ResourceBundle.getBundle("propertie.configuration");
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(resource.getString("xsl")));
        transformer.transform(new StreamSource(input), new StreamResult(output));
    }
}
