package model;

/**
 * This class contains the constructor, getters, setters, and toString() methods of the FirstLevelDivision object.
 * Author: Joseph Bruening
 */
public class FirstLevelDivision {
    private int divisionId;
    private String division;
    private int countryId;

    /**
     * This method is the constructor for the FirstLevelDivision object. When this method is called
     * a new FirstLevelDivision object will be created.
     * @param divisionId The divisionId of the FirstLevelDivision object
     * @param division The division name of the FirstLevelDivision object
     * @param countryId The countryId of the FirstLevelDivision object
     */
    public FirstLevelDivision(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     * This is a getter method. This method will get the divisionId for the FirstLevelDivision object.
     * @return Returns the divisionId for the FirstLevelDivision object.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * This is a setter method. This method will set the value of the divisionId for a FirstLevelDivision object.
     * @param divisionId The value of which to set the divisionId of the FirstLevelDivision object to.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * This is a getter method. This method will get the division name for the FirstLevelDivision object.
     * @return Returns the division name for the FirstLevelDivision object.
     */
    public String getDivision() {
        return division;
    }

    /**
     * This is a setter method. This method will set the value of the division name for a FirstLevelDivision object.
     * @param division The value of which to set the division name of the FirstLevelDivision object to.
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * This is a getter method. This method will get the countryId for the FirstLevelDivision object.
     * @return Returns the countryId for the FirstLevelDivision object.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * This is a setter method. This method will set the value of the countryId for a FirstLevelDivision object.
     * @param countryId The value of which to set the countryId of the FirstLevelDivision object to.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * This method overrides the toString() method. When this method is called a custom toString() of the
     * FirstLevelDivision object will be implemented.
     * @return Returns the custom string of the FirstLevelDivision object
     */
    @Override
    public String toString() {
        return (division);
    }
}
