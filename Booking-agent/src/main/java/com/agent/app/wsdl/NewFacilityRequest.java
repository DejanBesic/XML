//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.23 at 10:56:52 PM CEST 
//


package com.agent.app.wsdl;

import java.util.ArrayList;
import java.util.List;
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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="owner" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="parkingLot" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="wifi" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="breakfast" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="halfBoard" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="fullBoard" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="tv" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="kitchen" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="bathroom" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="numberOfPeople" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element ref="{http://booking.xml.com/backendmain/ws-classes}appointmentWS" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://booking.xml.com/backendmain/ws-classes}imagesWS"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "category",
    "owner",
    "type",
    "description",
    "address",
    "location",
    "parkingLot",
    "wifi",
    "breakfast",
    "halfBoard",
    "fullBoard",
    "tv",
    "kitchen",
    "bathroom",
    "numberOfPeople",
    "appointmentWS",
    "imagesWS"
})
@XmlRootElement(name = "newFacilityRequest")
public class NewFacilityRequest {

    @XmlElement(required = true)
    protected String name;
    protected int category;
    @XmlElement(required = true)
    protected String owner;
    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String address;
    @XmlElement(required = true)
    protected String location;
    protected boolean parkingLot;
    protected boolean wifi;
    protected boolean breakfast;
    protected boolean halfBoard;
    protected boolean fullBoard;
    protected boolean tv;
    protected boolean kitchen;
    protected boolean bathroom;
    protected int numberOfPeople;
    protected List<AppointmentWS> appointmentWS;
    @XmlElement(required = true)
    protected ImagesWS imagesWS;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the category property.
     * 
     */
    public int getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     */
    public void setCategory(int value) {
        this.category = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the parkingLot property.
     * 
     */
    public boolean isParkingLot() {
        return parkingLot;
    }

    /**
     * Sets the value of the parkingLot property.
     * 
     */
    public void setParkingLot(boolean value) {
        this.parkingLot = value;
    }

    /**
     * Gets the value of the wifi property.
     * 
     */
    public boolean isWifi() {
        return wifi;
    }

    /**
     * Sets the value of the wifi property.
     * 
     */
    public void setWifi(boolean value) {
        this.wifi = value;
    }

    /**
     * Gets the value of the breakfast property.
     * 
     */
    public boolean isBreakfast() {
        return breakfast;
    }

    /**
     * Sets the value of the breakfast property.
     * 
     */
    public void setBreakfast(boolean value) {
        this.breakfast = value;
    }

    /**
     * Gets the value of the halfBoard property.
     * 
     */
    public boolean isHalfBoard() {
        return halfBoard;
    }

    /**
     * Sets the value of the halfBoard property.
     * 
     */
    public void setHalfBoard(boolean value) {
        this.halfBoard = value;
    }

    /**
     * Gets the value of the fullBoard property.
     * 
     */
    public boolean isFullBoard() {
        return fullBoard;
    }

    /**
     * Sets the value of the fullBoard property.
     * 
     */
    public void setFullBoard(boolean value) {
        this.fullBoard = value;
    }

    /**
     * Gets the value of the tv property.
     * 
     */
    public boolean isTv() {
        return tv;
    }

    /**
     * Sets the value of the tv property.
     * 
     */
    public void setTv(boolean value) {
        this.tv = value;
    }

    /**
     * Gets the value of the kitchen property.
     * 
     */
    public boolean isKitchen() {
        return kitchen;
    }

    /**
     * Sets the value of the kitchen property.
     * 
     */
    public void setKitchen(boolean value) {
        this.kitchen = value;
    }

    /**
     * Gets the value of the bathroom property.
     * 
     */
    public boolean isBathroom() {
        return bathroom;
    }

    /**
     * Sets the value of the bathroom property.
     * 
     */
    public void setBathroom(boolean value) {
        this.bathroom = value;
    }

    /**
     * Gets the value of the numberOfPeople property.
     * 
     */
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    /**
     * Sets the value of the numberOfPeople property.
     * 
     */
    public void setNumberOfPeople(int value) {
        this.numberOfPeople = value;
    }

    /**
     * Gets the value of the appointmentWS property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the appointmentWS property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAppointmentWS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AppointmentWS }
     * 
     * 
     */
    public List<AppointmentWS> getAppointmentWS() {
        if (appointmentWS == null) {
            appointmentWS = new ArrayList<AppointmentWS>();
        }
        return this.appointmentWS;
    }

    /**
     * Gets the value of the imagesWS property.
     * 
     * @return
     *     possible object is
     *     {@link ImagesWS }
     *     
     */
    public ImagesWS getImagesWS() {
        return imagesWS;
    }

    /**
     * Sets the value of the imagesWS property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImagesWS }
     *     
     */
    public void setImagesWS(ImagesWS value) {
        this.imagesWS = value;
    }

}
