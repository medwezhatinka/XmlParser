/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.parser.stax;

import by.epam.lab.constant.ElementName;
import by.epam.lab.model.Certificate;
import by.epam.lab.model.Dosage;
import by.epam.lab.model.MedGroup;
import by.epam.lab.model.MedVersion;
import by.epam.lab.model.Medicament;
import by.epam.lab.model.PackageType;
import by.epam.lab.model.Producer;
import by.epam.lab.model.Version;
import by.epam.lab.util.Helper;
import java.io.InputStream;
import by.epam.lab.model.Package;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author Alina_Shumel
 */
public class MedicineSTAXParser {

    ArrayList<Medicament> medicine = new ArrayList<>();
    ;
    Medicament currMedicament;
    Version currVersion;
    Producer currProducer;
    Certificate currCertificate;
    Package currPackage;
    Dosage currDosage;
    String currClass;
    List<String> currAnalogs;
    List<Producer> currProducers;
    List<Version> currVersions;
    String prefix = "";

    public void parse(InputStream input) throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
        process(reader);
    }

    public ArrayList<Medicament> process(XMLStreamReader reader) throws XMLStreamException {

        String name = null;
        while (reader.hasNext()) {
            int type = reader.next();

            switch (type) {

                case XMLStreamConstants.START_ELEMENT:


                    if (reader.getNamespaceCount() > 1) {
                        prefix = reader.getNamespacePrefix(1);
                    }
                    name = prefix + ":" + reader.getLocalName();
                    switch (name) {
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
                            currPackage = new by.epam.lab.model.Package();
                            currClass = currPackage.getClass().getSimpleName();
                            break;
                        case ElementName.PRODUCER_DOSAGE:
                            currDosage = new Dosage();
                            break;
                        default:
                            break;

                    }

                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = prefix + ":" + reader.getLocalName();


                    if (name.equals(ElementName.MEDICATION)) {
                        medicine.add(currMedicament);

                    }
                    if (name.equals(ElementName.MEDICAMENT_VERSION)) {
                        currVersions.add(currVersion);

                    }
                    if (name.equals(ElementName.VERSION_PRODUCER)) {
                        currProducer.setCertificate(currCertificate);
                        currProducer.setPackage(currPackage);
                        currProducer.setDosage(currDosage);
                        currProducers.add(currProducer);
                    }

                    name = null;
                    break;

                case XMLStreamConstants.CHARACTERS:
                    String value = reader.getText();

                    if (name != null) {


                        switch (name) {
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
                    break;
            }
        }
        return medicine;
    }
}
