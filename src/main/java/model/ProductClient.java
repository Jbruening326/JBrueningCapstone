package model;

public class ProductClient extends Client{

    private String productDescription;

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
    public ProductClient(int clientId, String clientName, String productDescription, String address, String postalCode, String phone, int divisionId) {
        super(clientId, clientName, address, postalCode, phone, divisionId);
        this.productDescription = productDescription;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

}
