package model;

/**
 * This class contains a constructor, getters, setters, and customer toString() methods for the the Country object.
 * Author: Joseph Bruening
 */
public class Country {
    private int countryId;
    private String name;

    /**
     * This method is the constructor for the Country object. When this method is called a new Country object will be created.
     * @param countryId The countryId for the Country object
     * @param name The name for the Country object
     */
    public Country(int countryId, String name) {
        this.countryId = countryId;
        this.name = name;
    }

    /**
     * This is a getter method. This method will get the countryId for the Country object.
     * @return Returns the countryId for the Country object.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * This is a setter method. This method will set the value of the countryId for a Country object.
     * @param countryId The value of which to set the countryId of the Country object to.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * This is a getter method. This method will get the name for the Country object.
     * @return Returns the name for the Country object.
     */
    public String getName() {
        return name;
    }

    /**
     * This is a setter method. This method will set the value of the name for a Country object.
     * @param name The value of which to set the name of the Country object to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method overrides the toString() method. When this method is called a custom toString() of the Country object
     * will be implemented.
     * @return Returns the custom string of the Country object
     */
    @Override
    public String toString() {
        return (name);
    }
}
