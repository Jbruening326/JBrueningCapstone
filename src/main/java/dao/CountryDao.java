package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static helper.JDBC.connection;

/**
 * This abstract class contains methods to Create, Read, Update, and Delete Country objects from the database.
 */
public abstract class CountryDao {

    /**
     * This method retrieves a single country record. When this method is called a single record
     * from the countries table will be returned.
     * @param id The Country_ID  value for the chosen country record to return.
     * @return Returns the desired country record.
     * @throws SQLException
     */
    public static Country get(int id) throws SQLException {
        Country country = null;
        String sql = "SELECT Country_ID, Country FROM countries  WHERE Country_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryId = rs.getInt("Country_ID");
            String name = rs.getString("Country");


            country = new Country(countryId, name);
        }
        return country;
    }

    /**
     * This method retrieves all country records. When this method is called, all records from the countries
     * table will be returned.
     * @return Returns an observable list of all country records.
     * @throws SQLException
     */
    public static ObservableList<Country> getAll() throws SQLException {
        String sql = "SELECT Country_ID, Country FROM countries";
        ObservableList<Country> allCountries = FXCollections.observableArrayList();

        PreparedStatement ps = connection.prepareCall(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int countryId = rs.getInt("Country_ID");
            String name = rs.getString("Country");

            Country country = new Country(countryId, name);

            allCountries.add(country);

        }
        return allCountries;
    }

    /**
     * Method not implemented. At this time, project specifications restrict Country objects to read only.
     * @param country The Country object to be inserted.
     * @return Returns 0 at this time.
     * @throws SQLException
     */
    public static int insert(Country country) throws SQLException {
        return 0;
    }

    /**
     * Method not implemented. At this time, project specifications restrict Country objects to read only.
     * @param country The Country object to be updated.
     * @return Returns 0 at this time.
     * @throws SQLException
     */
    public static int update(Country country) throws SQLException {
        return 0;
    }

    /**
     * Method not implemented. At this time, project specifications restrict Country objects to read only.
     * @param country The Country object to be deleted.
     * @return Returns 0 at this time.
     * @throws SQLException
     */
    public static int delete(Country country) throws SQLException {
        return 0;
    }
}
