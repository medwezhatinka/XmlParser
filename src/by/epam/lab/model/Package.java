//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.05 at 02:05:01 PM FET 
//


package by.epam.lab.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Package complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Package">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Type" type="{http://xml.netbeans.org/schema/MedicineSchema}PackageType"/>
 *         &lt;element name="Count" type="{http://xml.netbeans.org/schema/MedicineSchema}count"/>
 *         &lt;element name="Prise" type="{http://xml.netbeans.org/schema/MedicineSchema}prise"/>
 *         &lt;element name="CountInPack" type="{http://xml.netbeans.org/schema/MedicineSchema}count"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Package", propOrder = {
    "type",
    "count",
    "prise",
    "countInPack"
})
public class Package {

    @XmlElement(name = "Type", required = true)
    protected PackageType type;
    @XmlElement(name = "Count")
    protected int count;
    @XmlElement(name = "Prise", required = true)
    protected BigDecimal prise;
    @XmlElement(name = "CountInPack")
    protected int countInPack;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link PackageType }
     *     
     */
    public PackageType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageType }
     *     
     */
    public void setType(PackageType value) {
        this.type = value;
    }

    /**
     * Gets the value of the count property.
     * 
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     */
    public void setCount(int value) {
        this.count = value;
    }

    /**
     * Gets the value of the prise property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrise() {
        return prise;
    }

    /**
     * Sets the value of the prise property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrise(BigDecimal value) {
        this.prise = value;
    }

    /**
     * Gets the value of the countInPack property.
     * 
     */
    public int getCountInPack() {
        return countInPack;
    }

    /**
     * Sets the value of the countInPack property.
     * 
     */
    public void setCountInPack(int value) {
        this.countInPack = value;
    }

    public String showPackage(){
        return "Type:  "+ type +"\n"+ "Count: " + count + "\n" +  "Prise: " + prise+ "\n";
    }
}
