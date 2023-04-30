package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static helper.JDBC.connection;

/**
 * This abstract class contains methods to Create, Read, Update, and Delete User objects from the database.
 */
public abstract class UserDao {


    public static boolean login(String name, String password) throws SQLException{
        boolean success = false;
        String sql = "select * FROM appointment_schedule_c868.users where User_Name = ? AND Password = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            success = true;
        }
        return success;
    }
    /**
     * This method retrieves a single user record. When this method is called a single record
     * from the users table will be returned.
     * @param name The userName value for the chosen user record to return.
     * @return Returns the desired user record or null if no user is found.
     * @throws SQLException
     */
    public static User get(String name) throws SQLException {
        String sql = "SELECT User_ID, User_Name, Name FROM appointment_schedule_c868.users  WHERE User_Name = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            //String password = rs.getString("Password");
            String password = "******";
            String uName = rs.getString("Name");

            User user = new User(userId, userName, password, uName);
            return user;
        }
        return null;
    }
    //Overloaded method
    /**
     * This method retrieves a single user record. When this method is called a single record
     * from the users table will be returned.
     * @param id The User_ID value for the chosen user record to return.
     * @return Returns the desired user record or null if no record is found
     * @throws SQLException
     */
    public static User get(int id) throws SQLException {
        String sql = "SELECT User_ID, User_Name, Name FROM appointment_schedule_c868.users  WHERE User_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            //String password = rs.getString("Password");
            String password = "******";
            String name = rs.getString("Name");


            User user = new User(userId, userName, password, name);

            return user;

        }
        return null;
    }


    /**
     * This method retrieves all user records. When this method is called, all records from the users
     * table will be returned.
     * @return Returns an observable list of all user records.
     * @throws SQLException
     */
    public static ObservableList<User> getAll() throws SQLException {
        String sql = "SELECT User_ID, User_Name, Name FROM appointment_schedule_c868.users";
        ObservableList<User> allUsers = FXCollections.observableArrayList();

        PreparedStatement ps = connection.prepareCall(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = "********";
            String name = rs.getString("Name");

            User user = new User(userId, userName, password, name);

            allUsers.add(user);

        }
        return allUsers;
    }

    /**
     * Method not implemented. At this time, project specifications restrict User objects to read only.
     * @param user The User object to be inserted.
     * @return Returns 0 at this time.
     * @throws SQLException
     */
    public static int insert(User user) throws SQLException {
        return 0;
    }

    /**
     * Method not implemented. At this time, project specifications restrict User objects to read only.
     * @param user The User object to be updated.
     * @return Returns 0 at this time.
     * @throws SQLException
     */
    public static int update(User user) throws SQLException {
        return 0;
    }

    /**
     * Method not implemented. At this time, project specifications restrict User objects to read only.
     * @param user The User object to be deleted.
     * @return Returns 0 at this time.
     * @throws SQLException
     */
    public static int delete(User user) throws SQLException {
        return 0;
    }



}
