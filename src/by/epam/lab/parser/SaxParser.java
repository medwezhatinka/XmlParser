/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.parser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.netbeans.xml.schema.medicineschema.Certificate;
import org.netbeans.xml.schema.medicineschema.Dosage;
import org.netbeans.xml.schema.medicineschema.MedGroup;
import org.netbeans.xml.schema.medicineschema.MedVersion;
import org.netbeans.xml.schema.medicineschema.Medicament;
import org.netbeans.xml.schema.medicineschema.Producer;
import org.netbeans.xml.schema.medicineschema.Version;
import org.netbeans.xml.schema.medicineschema.Package;
import org.netbeans.xml.schema.medicineschema.PackageType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Alina_Shumel
 */
public class SaxParser extends DefaultHandler {
    
    ArrayList<Medicament> medicine = new ArrayList<>();
    Medicament currMedicament;
    Version currVersion;
    Producer currProducer;
    Certificate currCertificate;
    Package currPackage;
    Dosage currDosage;
    String currElement;
    String currClass;
    List<String> currAnalogs;
    List<Producer> currProducers;
    List<Version> currVersions;
    
    public ArrayList<Medicament> getMedicine() {
        return medicine;
    }
    
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parsing");
    }
    
    @Override
    public void endDocument() throws SAXException {
        System.out.println("End parsing");
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        switch (localName) {
            case "Medication":
                currMedicament = new Medicament();
                currAnalogs = currMedicament.getAnalog();
                currVersions = currMedicament.getVersion();
                break;
            case "Version":
                currVersion = new Version();
                currProducers = currVersion.getProducer();
                currClass = currVersion.getClass().getSimpleName();
                break;
            case "Producer":
                currProducer = new Producer();
                break;
            case "Certificate":
                currCertificate = new Certificate();
                break;
            case "Package":
                currPackage = new Package();
                currClass = currPackage.getClass().getSimpleName();
                break;
            case "Dosage":
                currDosage = new Dosage();
                break;
            
        }
        currElement = localName;
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("Medication")) {
            medicine.add(currMedicament);
            currElement = null;
        }
        if (localName.equals("Version")) {
            currVersions.add(currVersion);
            
        }
        if (localName.equals("Producer")) {
            currProducer.setCertificate(currCertificate);
            currProducer.setPackage(currPackage);
            currProducer.setDosage(currDosage);
        }
        if (localName.equals("Producer")) {
            currProducers.add(currProducer);
            
        }
        currElement = null;
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length);
        System.out.println("Value " + new String(ch, start, length));
        if (currElement != null) {
            
            
            switch (currElement) {
                case "Name":
                    currMedicament.setName(value);
                    break;
                case "Pharm":
                    currMedicament.setPharm(value);
                    break;
                case "Group":
                    currMedicament.setGroup(MedGroup.fromValue(value));
                    break;
                case "Analog":
                    currAnalogs.addAll(Arrays.asList(value.split(" ")));
                    break;
                case "ID":
                    currCertificate.setID(value);
                    break;
                case "Type":
                    if (currClass.equals("Version")) {
                        currVersion.setType(MedVersion.fromValue(value));
                    } else if (currClass.equals("Package")) {
                        currPackage.setType(PackageType.fromValue(value));
                    }
                    break;
                case "DateOfIssue":
                    GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
                    gc.set(Integer.valueOf(value.substring(0, 4)),
                            Integer.valueOf(value.substring(5, 7)),
                            Integer.valueOf(value.substring(8, 10)));
                    XMLGregorianCalendar xgc = null;
                    try {
                        xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                    } catch (DatatypeConfigurationException ex) {
                        Logger.getLogger(SaxParser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    currCertificate.setDateOfIssue(xgc);
                    break;
                 case "DateOfExpiry":
                    GregorianCalendar gce = (GregorianCalendar) GregorianCalendar.getInstance();
                    gce.set(Integer.valueOf(value.substring(0, 4)),
                            Integer.valueOf(value.substring(5, 7)),
                            Integer.valueOf(value.substring(8, 10)));
                    XMLGregorianCalendar xgce = null;
                    try {
                        xgce = DatatypeFactory.newInstance().newXMLGregorianCalendar(gce);
                    } catch (DatatypeConfigurationException ex) {
                        Logger.getLogger(SaxParser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    currCertificate.setDateOfExpiry(xgce);
                    break;
                case "Authority":
                    currCertificate.setAuthority(value);
                    break;
                case "Count":
                    currPackage.setCount(Integer.valueOf(value));
                    break;
                case "Prise":
                    currPackage.setPrise(BigDecimal.valueOf(Long.valueOf(value)));
                    break;
                case "CountInPack":
                    currPackage.setCountInPack(Integer.valueOf(value));
                    break;
                case "Dose":
                    currDosage.setDose(Float.valueOf(value));
                    break;
                case "Reception":
                    currDosage.setReception(value);
                    break;
               
            }
        }
    }
}
