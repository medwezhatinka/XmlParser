/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.parser.dom;

import by.epam.lab.constant.ElementName;
import by.epam.lab.model.Certificate;
import by.epam.lab.model.Dosage;
import by.epam.lab.model.MedGroup;
import by.epam.lab.model.MedVersion;
import by.epam.lab.model.Medicament;
import by.epam.lab.model.Producer;
import by.epam.lab.model.Version;
import by.epam.lab.model.Package;
import by.epam.lab.model.PackageType;
import by.epam.lab.util.Helper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.w3c.dom.NodeList;

/**
 *
 * @author Alina_Shumel
 */
public class Analyzer {

    public static ArrayList<Medicament> listBuilder(Element root) {

        Medicament currMedicament;
        List<String> currAnalogs;
       


        ArrayList<Medicament> medicaments = new ArrayList<>();

        NodeList medicineNodes = root.getElementsByTagName(ElementName.MEDICATION);

        for (int i = 0; i < medicineNodes.getLength(); i++) {
            currMedicament = new Medicament();
            Element medicamentElement = (Element) medicineNodes.item(i);
            currMedicament.setName(getBabyValue(medicamentElement, ElementName.MEDICAMENT_NAME));
            currMedicament.setPharm(getBabyValue(medicamentElement, ElementName.MEDICAMENT_PHARM));
            currMedicament.setGroup(MedGroup.fromValue(getBabyValue(medicamentElement, ElementName.MEDICAMENT_GROUP)));
            currAnalogs = currMedicament.getAnalog();
            currAnalogs.addAll(Arrays.asList(getBabyValue(medicamentElement, ElementName.MEDICAMENT_ANALOG).split(" ")));
            
            NodeList versionNodes = medicamentElement.getElementsByTagName(ElementName.MEDICAMENT_VERSION);
            
            initializeVersion(versionNodes, currMedicament.getVersion());
            
            medicaments.add(currMedicament);

        }
        return medicaments;

    }

    private static Element getBaby(Element parent, String childName) {
        NodeList nlist = parent.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }

    private static String getBabyValue(Element parent, String childName) {
        Element child = getBaby(parent, childName);
        Node node = child.getFirstChild();
        String value = node.getNodeValue();
        return value;
    }

    private static void initializeDosage(Element babyElement, Dosage currDosage) {

        currDosage.setDose(Float.valueOf(getBabyValue(babyElement, ElementName.DOSAGE_DOSE)));
        currDosage.setReception(getBabyValue(babyElement, ElementName.DOSAGE_RECEPTION));
    }

    private static void initializePackage(Element babyElement, Package currPackage) {


        currPackage.setType(PackageType.fromValue(getBabyValue(babyElement, ElementName.TYPE)));
        currPackage.setCount(Integer.valueOf(getBabyValue(babyElement, ElementName.PACKAGE_COUNT)));
        currPackage.setPrise(BigDecimal.valueOf(Long.valueOf(getBabyValue(babyElement, ElementName.PACKAGE_PRISE))));
        currPackage.setCountInPack(Integer.valueOf(getBabyValue(babyElement, ElementName.PACKAGE_COUNTINPACK)));

    }

    private static void initializeCertificate(Element babyElement, Certificate currCertificate) {
        currCertificate.setID(getBabyValue(babyElement, ElementName.CERTIFICATE_ID));
        currCertificate.setDateOfIssue(Helper.getDate(getBabyValue(babyElement, ElementName.CERTIFICATE_DATEOFISSUE)));
        currCertificate.setDateOfExpiry(Helper.getDate(getBabyValue(babyElement, ElementName.CERTIFICATE_DATEOFEXPIRY)));
        currCertificate.setAuthority(getBabyValue(babyElement, ElementName.CERTIFICATE_AUTHORITY));


    }
    
    private static void initializeProducers(NodeList producerNodes, List<Producer> currProducers){
        for (int k = 0; k < producerNodes.getLength(); k++) {
                   Producer currProducer = new Producer();
                    Element producerElement = (Element) producerNodes.item(k);
                    Certificate currCertificate = new Certificate();
                    Package currPackage = new Package();
                    Dosage currDosage = new Dosage();

                    initializeCertificate(getBaby(producerElement, ElementName.PRODUCER_CERTIFICATE), currCertificate);
                    initializePackage(getBaby(producerElement, ElementName.PRODUCER_PACKAGE), currPackage);
                    initializeDosage(getBaby(producerElement, ElementName.PRODUCER_DOSAGE), currDosage);

                    currProducer.setCertificate(currCertificate);
                    currProducer.setPackage(currPackage);
                    currProducer.setDosage(currDosage);

                    currProducers.add(currProducer);
                }
        
        
    }
    
    private static  void initializeVersion(NodeList versionNodes, List<Version> currVersions){
        for (int j = 0; j < versionNodes.getLength(); j++) {
                Element versionElement = (Element) versionNodes.item(j);
                Version currVersion = new Version();
                currVersion.setType(MedVersion.fromValue(getBabyValue(versionElement, ElementName.TYPE)));
                NodeList producerNodes = versionElement.getElementsByTagName(ElementName.VERSION_PRODUCER);
                initializeProducers(producerNodes, currVersion.getProducer());
                currVersions.add(currVersion);
            }
    }
    
}
