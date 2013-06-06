package by.epam.lab.constant.elementname;

import org.netbeans.xml.schema.medicineschema.Version;
import org.netbeans.xml.schema.medicineschema.Package;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alina_Shumel
 */
public interface ElementName {
    //Root
    String ROOT = "ns0:Medicine";
    
    //Medicine element
    String MEDICATION = "ns0:Medication";
    
    //Medicament elements
    String MEDICAMENT_NAME = "ns0:Name";
    String MEDICAMENT_PHARM ="ns0:Pharm";
    String MEDICAMENT_GROUP = "ns0:Group";
    String MEDICAMENT_ANALOG = "ns0:Analog";
    String MEDICAMENT_VERSION = "ns0:Version";
    
    //Version elements
    String VERSION_CLASSNAME = Version.class.getSimpleName();
    String TYPE = "ns0:Type";
    String VERSION_PRODUCER = "ns0:Producer";
    
    //Producer elements
    String PRODUCER_CERTIFICATE = "ns0:Certificate";
    String PRODUCER_PACKAGE = "ns0:Package";
    String PRODUCER_DOSAGE = "ns0:Dosage";
    
    //Certificate elements
    String CERTIFICATE_ID = "ns0:ID";
    String CERTIFICATE_DATEOFISSUE = "ns0:DateOfIssue";
    String CERTIFICATE_DATEOFEXPIRY = "ns0:DateOfExpiry";
    String CERTIFICATE_AUTHORITY = "ns0:Authority";
    
    //Package elements
    String PACKAGE_CLASSNAME = Package.class.getSimpleName();
    String PACKAGE_COUNT = "ns0:Count";
    String PACKAGE_PRISE = "ns0:Prise";
    String PACKAGE_COUNTINPACK = "ns0:CountInPack";
    
    //Dosage elements
    String DOSAGE_DOSE = "ns0:Dose";
    String DOSAGE_RECEPTION = "ns0:Reception";
    
    
}
