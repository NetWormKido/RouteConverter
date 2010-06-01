//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.05.25 at 07:35:43 AM MESZ 
//


package slash.navigation.earthtools.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="location">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="meters" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="feet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "version",
    "location",
    "meters",
    "feet"
})
@XmlRootElement(name = "height")
public class Height {

    @XmlElement(required = true)
    protected String version;
    @XmlElement(required = true)
    protected Height.Location location;
    @XmlElement(required = true)
    protected String meters;
    @XmlElement(required = true)
    protected String feet;

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link Height.Location }
     *     
     */
    public Height.Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Height.Location }
     *     
     */
    public void setLocation(Height.Location value) {
        this.location = value;
    }

    /**
     * Gets the value of the meters property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeters() {
        return meters;
    }

    /**
     * Sets the value of the meters property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeters(String value) {
        this.meters = value;
    }

    /**
     * Gets the value of the feet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeet() {
        return feet;
    }

    /**
     * Sets the value of the feet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeet(String value) {
        this.feet = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "latitude",
        "longitude"
    })
    public static class Location {

        @XmlElement(required = true)
        protected String latitude;
        @XmlElement(required = true)
        protected String longitude;

        /**
         * Gets the value of the latitude property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLatitude() {
            return latitude;
        }

        /**
         * Sets the value of the latitude property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLatitude(String value) {
            this.latitude = value;
        }

        /**
         * Gets the value of the longitude property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLongitude() {
            return longitude;
        }

        /**
         * Sets the value of the longitude property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLongitude(String value) {
            this.longitude = value;
        }

    }

}