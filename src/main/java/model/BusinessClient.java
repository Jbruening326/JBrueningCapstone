package model;

public class BusinessClient extends Client{

    private String businessName;
    /**
     * This method is the constructor for the Client object. When this method is called a new Customer object will be created.
     *
     * @param clientId     The customerId of the Customer object
     * @param clientName The name of the Customer object
     * @param address      The address of the Customer object
     * @param postalCode   The postal code of the Customer object
     * @param phone        The phone number of the Customer object
     * @param divisionId   The divisionId of the Customer object
     */
    public BusinessClient(int clientId, String clientName, String businessName, String address, String postalCode, String phone, int divisionId) {
        super(clientId, clientName, address, postalCode, phone, divisionId);
        this.businessName = businessName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

}
