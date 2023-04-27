package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static helper.JDBC.connection;

/**
 * This abstract class contains methods to Create, Read, Update, and Delete FirstLevelDivision objects from the database.
 */
public abstract class FirstLevelDivisionDao {

    /**
     * This method retrieves a single firstLevelDivision record. When this method is called a single record
     * from the first_level_divisions table will be returned.
     * @param id The Division_ID  value for the chosen firstLevelDivision record to return.
     * @return Returns the desired firstLevelDivision record.
     * @throws SQLException
     */
    public static FirstLevelDivision get(int id) throws SQLException {
        FirstLevelDivision firstDivision = null;
        String sql = "SELECT Division_ID, Division, Country_ID FROM first_level_divisions  WHERE Division_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");

            firstDivision = new FirstLevelDivision(divisionId, division, countryId);
        }
        return firstDivision;
    }

    /**
     * This method retrieves all firstLevelDivision records. When this method is called, all records from the
     * first_level_divisions table will be returned.
     * @return Returns an observable list of all firstLevelDivision records.
     * @throws SQLException
     */
    public static ObservableList<FirstLevelDivision> getAll() throws SQLException {
        String sql = "SELECT Division_ID, Division, Country_ID FROM first_level_divisions";
        ObservableList<FirstLevelDivision> allDivisions = FXCollections.observableArrayList();

        PreparedStatement ps = connection.prepareCall(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");

            FirstLevelDivision firstDivision = new FirstLevelDivision(divisionId, division, countryId);

            allDivisions.add(firstDivision);

        }
        return allDivisions;
    }

    /**
     * Method not implemented. At this time, project specifications restrict FirstLevelDivision objects to read only.
     * @param firstDivision The FirstLevelDivision object to be inserted.
     * @return Returns 0 at this time.
     * @throws SQLException
     */
    public static int insert(FirstLevelDivision firstDivision) throws SQLException {
        return 0;
    }

    /**
     * Method not implemented. At this time, project specifications restrict FirstLevelDivision objects to read only.
     * @param firstDivision The FirstLevelDivision object to be updated.
     * @return Returns 0 at this time.
     * @throws SQLException
     */
    public static int update(FirstLevelDivision firstDivision) throws SQLException {
        return 0;
    }

    /**
     * Method not implemented. At this time, project specifications restrict FirstLevelDivision objects to read only.
     * @param firstDivision The FirstLevelDivision object to be deleted.
     * @return Returns 0 at this time.
     * @throws SQLException
     */
    public static int delete(FirstLevelDivision firstDivision) throws SQLException {
        return 0;
    }
}
