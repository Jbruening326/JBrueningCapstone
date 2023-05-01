package model;


import dao.ClientDao;
import dao.CountryDao;
import dao.FirstLevelDivisionDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * This class contains the constructor, getters, setters, and toString() methods of the Client object.
 * Author: Joseph Bruening
 */
public abstract class Client {
    private int clientId;
    private String clientName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionId;


    /**
     * This method is the constructor for the Client object. When this method is called a new Customer object will be created.
     * @param clientId The customerId of the Customer object
     * @param clientName The name of the Customer object
     * @param address The address of the Customer object
     * @param postalCode The postal code of the Customer object
     * @param phone The phone number of the Customer object
     * @param divisionId The divisionId of the Customer object
     */
    public Client(int clientId, String clientName, String address, String postalCode, String phone, int divisionId) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
    }

    /**
     * This is a getter method. This method will get the customerId for the Customer object.
     * @return Returns the customerId for the Customer object.
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * This is a setter method. This method will set the value of the customerId for a Customer object.
     * @param clientId The value of which to set the customerId of the Customer object to.
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * This is a getter method. This method will get the name for the Customer object.
     * @return Returns the name for the Customer object.
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * This is a setter method. This method will set the value of the name for a Customer object.
     * @param clientName The value of which to set the name of the Customer object to.
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * This is a getter method. This method will get the address for the Customer object.
     * @return Returns the address for the Customer object.
     */
    public String getAddress() {
        return address;
    }

    /**
     * This is a setter method. This method will set the value of the address for a Customer object.
     * @param address The value of which to set the address of the Customer object to.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This is a getter method. This method will get the postal code for the Customer object.
     * @return Returns the postal code for the Customer object.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * This is a setter method. This method will set the value of the postal code for a Customer object.
     * @param postalCode The value of which to set the postal code of the Customer object to.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * This is a getter method. This method will get the phone number for the Customer object.
     * @return Returns the phone number for the Customer object.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This is a setter method. This method will set the value of the phone number for a Customer object.
     * @param phone The value of which to set the phone number of the Customer object to.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This is a getter method. This method will get the divisionId for the Customer object.
     * @return Returns the divisionId for the Customer object.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * This is a setter method. This method will set the value of the divisionId for a Customer object.
     * @param divisionId The value of which to set the divisionId of the Customer object to.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * This method gets a Country object from a Customer object. When this method is called a Country object will be
     * returned based on the value of the Customer object.
     * @return Returns the Country object that the Customer object is associated with
     * @throws SQLException
     */
    public Country getCountry() throws SQLException {
        int countryId = FirstLevelDivisionDao.get(divisionId).getCountryId();
        return CountryDao.get(countryId);
    }
    /**
     * This method gets a FirstLevelDivision object from a Customer object. When this method is called
     * a FirstLevelDivision object will be returned based on the value of the Customer object.
     * @return Returns the FirstLevelDivision object that the Customer object is associated with
     * @throws SQLException
     */
    public FirstLevelDivision getDivision() throws SQLException{
        return FirstLevelDivisionDao.get(divisionId);
    }

    /**
     * This method overrides the toString() method. When this method is called a custom toString() of the Customer object
     * will be implemented.
     * @return Returns the custom string of the Customer object
     */
    @Override
    public String toString(){
        return(Integer.toString(clientId) + " " + clientName);
    }



}
