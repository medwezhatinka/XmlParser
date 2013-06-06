/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.parser.sax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import by.epam.lab.constant.ElementName;
import by.epam.lab.util.Helper;
import by.epam.lab.model.Certificate;
import by.epam.lab.model.Dosage;
import by.epam.lab.model.MedGroup;
import by.epam.lab.model.MedVersion;
import by.epam.lab.model.Medicament;
import by.epam.lab.model.Producer;
import by.epam.lab.model.Version;
import by.epam.lab.model.Package;
import by.epam.lab.model.PackageType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Alina_Shumel
 */
public class MedicineHandler extends DefaultHandler {

    private ArrayList<Medicament> medicine;
    private Medicament currMedicament;
    private Version currVersion;
    private Producer currProducer;
    private Certificate currCertificate;
    private Package currPackage;
    private Dosage currDosage;
    private String currElement;
    private String currClass;
    private List<String> currAnalogs;
    private List<Producer> currProducers;
    private List<Version> currVersions;

    public ArrayList<Medicament> getMedicine() {
        return medicine;
    }

    @Override
    public void startDocument() throws SAXException {
        medicine = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Hurray!");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

        switch (qName) {
            case ElementName.MEDICATION:
                currMedicament = new Medicament();
                currAnalogs = currMedicament.getAnalog();
                currVersions = currMedicament.getVersion();
                break;
            case ElementName.MEDICAMENT_VERSION:
                currVersion = new Version();
                currProducers = currVersion.getProducer();
                currClass = currVersion.getClass().getSimpleName();
                break;
            case ElementName.VERSION_PRODUCER:
                currProducer = new Producer();
                break;
            case ElementName.PRODUCER_CERTIFICATE:
                currCertificate = new Certificate();
                break;
            case ElementName.PRODUCER_PACKAGE:
                currPackage = new Package();
                currClass = currPackage.getClass().getSimpleName();
                break;
            case ElementName.PRODUCER_DOSAGE:
                currDosage = new Dosage();
                break;

        }
        currElement = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(ElementName.MEDICATION)) {
            medicine.add(currMedicament);
            currElement = null;
        }
        if (qName.equals(ElementName.MEDICAMENT_VERSION)) {
            currVersions.add(currVersion);

        }
        if (qName.equals(ElementName.VERSION_PRODUCER)) {
            currProducer.setCertificate(currCertificate);
            currProducer.setPackage(currPackage);
            currProducer.setDosage(currDosage);
            currProducers.add(currProducer);
        }
        currElement = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String value = new String(ch, start, length);

        if (currElement != null) {


            switch (currElement) {
                case ElementName.MEDICAMENT_NAME:
                    currMedicament.setName(value);
                    break;
                case ElementName.MEDICAMENT_PHARM:
                    currMedicament.setPharm(value);
                    break;
                case ElementName.MEDICAMENT_GROUP:
                    currMedicament.setGroup(MedGroup.fromValue(value));
                    break;
                case ElementName.MEDICAMENT_ANALOG:
                    currAnalogs.addAll(Arrays.asList(value.split(" ")));
                    break;
                case ElementName.CERTIFICATE_ID:
                    currCertificate.setID(value);
                    break;
                case ElementName.TYPE:
                    if (currClass.equals(ElementName.VERSION_CLASSNAME)) {
                        currVersion.setType(MedVersion.fromValue(value));
                    } else if (currClass.equals(ElementName.PACKAGE_CLASSNAME)) {
                        currPackage.setType(PackageType.fromValue(value));
                    }
                    break;
                case ElementName.CERTIFICATE_DATEOFISSUE:

                    currCertificate.setDateOfIssue(Helper.getDate(value));
                    break;
                case ElementName.CERTIFICATE_DATEOFEXPIRY:
                    currCertificate.setDateOfExpiry(Helper.getDate(value));
                    break;
                case ElementName.CERTIFICATE_AUTHORITY:
                    currCertificate.setAuthority(value);
                    break;
                case ElementName.PACKAGE_COUNT:
                    currPackage.setCount(Integer.valueOf(value));
                    break;
                case ElementName.PACKAGE_PRISE:
                    currPackage.setPrise(BigDecimal.valueOf(Long.valueOf(value)));
                    break;
                case ElementName.PACKAGE_COUNTINPACK:
                    currPackage.setCountInPack(Integer.valueOf(value));
                    break;
                case ElementName.DOSAGE_DOSE:
                    currDosage.setDose(Float.valueOf(value));
                    break;
                case ElementName.DOSAGE_RECEPTION:
                    currDosage.setReception(value);
                    break;

            }
        }
    }
}
