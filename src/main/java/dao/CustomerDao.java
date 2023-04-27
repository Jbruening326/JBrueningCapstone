package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Country;
import model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import static helper.JDBC.connection;

/**
 * This abstract class contains methods to Create, Read, Update, and Delete Customer objects from the database.
 */
public abstract class CustomerDao {

    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    /**
     * This method retrieves a single customer record. When this method is called a single record
     * from the customers table will be returned.
     * @param id The Customer_ID  value for the chosen customer record to return.
     * @return Returns the desired customer record.
     * @throws SQLException
     */
    public static Customer get(int id) throws SQLException {
        Customer customer = null;
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, " +
                "Division_ID FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divisionId = rs.getInt("Division_ID");

            customer = new Customer(customerId, customerName, address, postalCode, phone, divisionId);
        }
        return customer;
    }


    /**
     * This method retrieves all customer records. When this method is called, all records from the customers
     * table will be returned.
     * @return Returns an observable list of all customer records.
     * @throws SQLException
     */
    public static ObservableList<Customer> getAll() throws SQLException {
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID FROM customers";
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

        PreparedStatement ps = connection.prepareCall(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divisionId = rs.getInt("Division_ID");

            Customer customer = new Customer(customerId, customerName, address, postalCode, phone, divisionId);

            allCustomers.add(customer);

        }
        return allCustomers;
    }

    /**
     * This method inserts a customer record. When this method is called, a new customer record will be added to the
     * customers table.
     * @param customer The Customer object that will inserted into the customers table.
     * @return Returns the number of rows affected.
     * @throws SQLException
     */
    public static int insert(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers " +
                "(Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setInt(5, customer.getDivisionId());

        return ps.executeUpdate();
    }

    /**
     * This method will update a customer record. When this method is called, a selected customer record from the
     * customers table will be updated from a Customer object.
     * @param customer The Customer object that will update the existing customer record.
     * @return Returns the number of rows affected.
     * @throws SQLException
     */
    public static int update(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, " +
                "Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setInt(5, customer.getDivisionId());
        ps.setInt(6, customer.getCustomerId());

        return ps.executeUpdate();
    }

    /**
     * This method will delete customer record. When this method is called, a selected customer record will
     * be removed from the customers table.
     * @param customer The Customer object that will be deleted from the customers table.
     * @return Returns the number of rows affected.
     * @throws SQLException
     */
    public static int delete(Customer customer) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, customer.getCustomerId());

        return ps.executeUpdate();
    }

    /**
     * This method will retrieve the number of customers that belong to a selected country.
     * When this method is called, the total number of customers that belong to a selected country will be returned
     * from a query.
     * @param country The Country object that will be used in the query
     * @return Returns the size of the list of customers from the results of the query
     * @throws SQLException
     */
    public static int getCustomersByCountry(Country country) throws SQLException {
        String sql = "SELECT * FROM customers " +
                "INNER JOIN first_level_divisions " +
                "ON customers.Division_ID = first_level_divisions.Division_ID " +
                "WHERE Country_ID =  ?";

       ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

       PreparedStatement ps = connection.prepareCall(sql);
       ps.setInt(1, country.getCountryId());
       ResultSet rs = ps.executeQuery();

       while(rs.next()){
           int customerId = rs.getInt("Customer_ID");
           String customerName = rs.getString("Customer_Name");
           String address = rs.getString("Address");
           String postalCode = rs.getString("Postal_Code");
           String phone = rs.getString("Phone");
           int divisionId = rs.getInt("Division_ID");

           Customer customer = new Customer(customerId, customerName, address, postalCode, phone, divisionId);

           allCustomers.add(customer);

       }
       return allCustomers.size();
   }
}
