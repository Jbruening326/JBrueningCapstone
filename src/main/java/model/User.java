package model;

import dao.ClientDao;
import dao.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * This class contains the constructor, getters, setters, and toString() methods of the User object.
 * Author: Joseph Bruening
 */
public class User {
    private int userId;
    private String userName;
    private String password;
    private String name;

    /**
     * This method is the constructor for the User object. When this method is called a new User object will be created.
     * @param userId The userId of the User object
     * @param userName The username of the User object
     * @param password The password for the User object
     * @param name The name for the User object
     */
    public User(int userId, String userName, String password, String name) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.name = name;
    }

    /**
     * This is a getter method. This method will get the userId for the User object.
     * @return Returns the userId for the User object.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * This is a setter method. This method will set the value of the userId for a User object.
     * @param userId The value of which to set the userId of the User object to.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * This is a getter method. This method will get the user name for the User object.
     * @return Returns the user name for the User object.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This is a setter method. This method will set the value of the user name for a User object.
     * @param userName The value of which to set the user name of the User object to.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This is a getter method. This method will get the password for the User object.
     * @return Returns the password for the User object.
     */
    public String getPassword() {
        return password;
    }

    /**
     * This is a setter method. This method will set the value of the password for a User object.
     * @param password The value of which to set the password of the User object to.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method overrides the toString() method. When this method is called a custom toString() of the User object
     * will be implemented.
     * @return Returns the custom string of the User object
     */
    @Override
    public String toString() {
        return name;
    }

}
