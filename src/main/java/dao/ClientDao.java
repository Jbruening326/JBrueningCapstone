package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import static helper.JDBC.connection;

/**
 * This abstract class contains methods to Create, Read, Update, and Delete Client objects from the database.
 */
public abstract class ClientDao {

    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    /**
     * This method retrieves a single client record. When this method is called a single record
     * from the clients table will be returned.
     * @param id The Client_ID  value for the chosen client record to return.
     * @return Returns the desired client record.
     * @throws SQLException
     */
    public static Client get(int id) throws SQLException {
        Client client = null;
        String sql = "SELECT Client_ID, Client_Name, Business_Name, Product_Description, Address, Postal_Code, Phone, " +
                "Division_ID FROM appointment_schedule_c868.clients WHERE Client_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int clientId = rs.getInt("Client_ID");
            String clientName = rs.getString("Client_Name");
            String businessName = rs.getString("Business_Name");
            String productDescription = rs.getString("Product_Description");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divisionId = rs.getInt("Division_ID");

            if(businessName != null){
                client = new BusinessClient(clientId, clientName, businessName, address, postalCode, phone, divisionId);
            }
            else {
                client = new ProductClient(clientId, clientName, productDescription, address, postalCode, phone, divisionId);
            }

        }
        return client;
    }


    /**
     * This method retrieves all client records. When this method is called, all records from the clients
     * table will be returned.
     * @return Returns an observable list of all client records.
     * @throws SQLException
     */
    public static ObservableList<Client> getAll() throws SQLException {
        String sql = "SELECT Client_ID, Client_Name, Business_Name, Product_Description, Address, Postal_Code, " +
                "Phone, Division_ID FROM appointment_schedule_c868.clients";
        ObservableList<Client> allClients = FXCollections.observableArrayList();

        PreparedStatement ps = connection.prepareCall(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Client client;

            int clientId = rs.getInt("Client_ID");
            String clientName = rs.getString("Client_Name");
            String businessName = rs. getString("Business_Name");
            String productDescription = rs.getString("Product_Description");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divisionId = rs.getInt("Division_ID");

            if(businessName != null){
                client = new BusinessClient(clientId, clientName, businessName, address, postalCode, phone, divisionId);
            }
            else{
                client = new ProductClient(clientId, clientName, productDescription, address, postalCode, phone, divisionId);
            }



            allClients.add(client);

        }
        return allClients;
    }

    /**
     * This method inserts a client record. When this method is called, a new client record will be added to the
     * clients table.
     * @param client The Client object that will inserted into the clients table.
     * @return Returns the number of rows affected.
     * @throws SQLException
     */
    public static int insert(Client client) throws SQLException {
        if (client instanceof BusinessClient){
            String sql = "INSERT INTO appointment_schedule_c868.clients (Client_Name, Business_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, client.getClientName());
            ps.setString(2, ((BusinessClient) client).getBusinessName());
            ps.setString(3, client.getAddress());
            ps.setString(4, client.getPostalCode());
            ps.setString(5, client.getPhone());
            ps.setInt(6, client.getDivisionId());

            return ps.executeUpdate();
        }
        else{
            String sql = "INSERT INTO appointment_schedule_c868.clients (Client_Name, Product_Description, Address, Postal_Code, Phone, Division_ID) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, client.getClientName());
            ps.setString(2, ((ProductClient) client).getProductDescription());
            ps.setString(3, client.getAddress());
            ps.setString(4, client.getPostalCode());
            ps.setString(5, client.getPhone());
            ps.setInt(6, client.getDivisionId());

            return ps.executeUpdate();
        }

    }

    /**
     * This method will update a client record. When this method is called, a selected client record from the
     * clients table will be updated from a Client object.
     * @param client The Client object that will update the existing client record.
     * @return Returns the number of rows affected.
     * @throws SQLException
     */
    public static int update(Client client) throws SQLException {
        if (client instanceof BusinessClient){
            String sql = "UPDATE appointment_schedule_c868.clients SET Client_Name = ?, Business_Name = ?, Product_Description = ? " +
                    "Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Client_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, client.getClientName());
            ps.setString(2, ((BusinessClient) client).getBusinessName());
            ps.setString(3, null);
            ps.setString(4, client.getAddress());
            ps.setString(5, client.getPostalCode());
            ps.setString(6, client.getPhone());
            ps.setInt(7, client.getDivisionId());
            ps.setInt(8, client.getClientId());

            return ps.executeUpdate();
        }
        else{
            String sql = "UPDATE appointment_schedule_c868.clients SET Client_Name = ?, Business_Name = ?, Product_Description = ?, " +
                    "Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Client_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, client.getClientName());
            ps.setString(2, null);
            ps.setString(3, ((ProductClient) client).getProductDescription());
            ps.setString(4, client.getAddress());
            ps.setString(5, client.getPostalCode());
            ps.setString(6, client.getPhone());
            ps.setInt(7, client.getDivisionId());
            ps.setInt(8, client.getClientId());

            return ps.executeUpdate();
        }

    }

    /**
     * This method will delete client record. When this method is called, a selected client record will
     * be removed from the clients table.
     * @param client The Client object that will be deleted from the clients table.
     * @return Returns the number of rows affected.
     * @throws SQLException
     */
    public static int delete(Client client) throws SQLException {
        String sql = "DELETE FROM appointment_schedule_c868.clients WHERE Client_ID = ?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, client.getClientId());

        return ps.executeUpdate();
    }

    /**
     * This method will retrieve the number of clients that belong to a selected country.
     * When this method is called, the total number of clients that belong to a selected country will be returned
     * from a query.
     * @param country The Country object that will be used in the query
     * @return Returns the size of the list of clients from the results of the query
     * @throws SQLException
     */
    public static int getClientsByCountry(Country country) throws SQLException {
        String sql = "SELECT * FROM appointment_schedule_c868.clients " +
                "INNER JOIN first_level_divisions " +
                "ON clients.Division_ID = first_level_divisions.Division_ID " +
                "WHERE Country_ID =  ?";

       ObservableList<Client> allClients = FXCollections.observableArrayList();

       PreparedStatement ps = connection.prepareCall(sql);
       ps.setInt(1, country.getCountryId());
       ResultSet rs = ps.executeQuery();

       while(rs.next()){
           int clientId = rs.getInt("Client_ID");
           String clientName = rs.getString("Client_Name");
           String businessName = "Name";
           String address = rs.getString("Address");
           String postalCode = rs.getString("Postal_Code");
           String phone = rs.getString("Phone");
           int divisionId = rs.getInt("Division_ID");

           Client client = new BusinessClient(clientId, clientName, businessName, address, postalCode, phone, divisionId);

           allClients.add(client);

       }
       return allClients.size();
   }
}
