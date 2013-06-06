/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.util;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Alina_Shumel
 */
public class Helper {

    public static XMLGregorianCalendar getDate(String value) {
        
        int year = Integer.valueOf(value.substring(0, 4));
        int month = Integer.valueOf(value.substring(5, 7));
        int dayOfMonth = Integer.valueOf(value.substring(8, 10));
       
        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, dayOfMonth);
        XMLGregorianCalendar xMLGregorianCalendar = null;
        try {
            xMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return xMLGregorianCalendar;
    }
}
