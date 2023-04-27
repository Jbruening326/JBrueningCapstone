package model;

import dao.CustomerDao;
import dao.UserDao;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This class contains contractor, getters and setters for an Appointment object
 */
public class Appointment {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int customerId;
    private int userId;

    /**
     * This is a contructor method for the Appointment object. When this method is called a new Appointment object is created.
     * @param appointmentId The appointmentId for the object
     * @param title The title for the object
     * @param description The description for the object
     * @param location The location for the object
     * @param type The type for the object
     * @param startDateTime The start time for the object
     * @param endDateTime The end time for the object
     * @param customerId The customerId for the object
     * @param userId The userId for the object
     */
    public Appointment(int appointmentId, String title, String description, String location, String type,
                       LocalDateTime startDateTime, LocalDateTime endDateTime, int customerId,
                       int userId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.customerId = customerId;
        this.userId = userId;
    }

    /**
     * This is a getter method. This method returns appointmentId.
     * @return Returns appointmentID
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * This is a setter method. This method will set the appointmentId when called.
     * @param appointmentId The value to set the appointmentId to.
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * This is a getter method. This method will get the title for the Appointment object.
     * @return Returns the title for the Appointment object.
     */
    public String getTitle() {
        return title;
    }

    /**
     * This is a setter method. This method will set the value of the title for an Appointment object.
     * @param title The value of which to set the title of the Appointment object to.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This is a getter method. This method will return the value of the description of an Appointment object.
     * @return Returns the description of the Appointment object.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This is a setter method. This method will set the value of the description of an Appointment object.
     * @param description The value of which to set the description of the Appointment object to.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This is a getter method. This method will get the value of the location of an Appointment object.
     * @return Returns the location of the Appointment object.
     */
    public String getLocation() {
        return location;
    }

    /**
     * This is a setter method. This method will set the value of the location of an Appointment object.
     * @param location The value of which to set the location of the Appointment object to.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This is a getter method. This method will get the value of the type of an Appointment object.
     * @return Returns the type of the Appointment object.
     */
    public String getType() {
        return type;
    }

    /**
     * This is a setter method. This method will set the value of the type of an Appointment object.
     * @param type The value of which to set the type of the Appointment object to.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This is a getter method. This method will get the value of the LocalDateTime of an Appointment object.
     * @return Returns the LocalDateTime of the Appointment object.
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * This is a setter method. This method will set the value of the LocalDateTime of an Appointment object.
     * @param startDateTime The value of which to set the LocalDateTime of the Appointment object to.
     */
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * This is a getter method. This method will get the value of the LocalDateTime of an Appointment object.
     * @return Returns the LocalDateTime of the Appointment object.
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * This is a setter method. This method will set the value of the LocalDateTime of an Appointment object.
     * @param endDateTime The value of which to set the LocalDateTime of the Appointment object to.
     */
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * This is a getter method. This method will get the value of the customerId of an Appointment object.
     * @return Returns the customerId of the Appointment object.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * This is a setter method. This method will set the value of the customerId of an Appointment object.
     * @param customerId The value of which to set the customerId of the Appointment object to.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * This is a getter method. This method will get the value of the userId of an Appointment object.
     * @return Returns the userId of the Appointment object.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * This is a setter method. This method will set the value of the userId of an Appointment object.
     * @param userId The value of which to set the userId of the Appointment object to.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }


    /**
     * This method gets a Customer object from an Appointment object. When this method is called a Customer object will be
     * returned based on the value of the Appointment object.
     * @return Returns the Customer object that the Appointment object is associated with
     * @throws SQLException
     */
    public Customer getCustomer() throws SQLException {
        return CustomerDao.get(customerId);
    }

    /**
     * This method gets a User object from an Appointment object. When this method is called a User object will be
     * returned based on the value of the Appointment object.
     * @return Returns the User object that the Appointment object is associated with
     * @throws SQLException
     */
    public User getUser() throws SQLException{
        return UserDao.get(userId);
    }

    /**
     * This method gets LocalDate of an Appointment object. When this method is called a LocalDate object is returned
     * @return The LocalDate for the Appointment object
     */
    public LocalDate getLocalDate() {
        return startDateTime.toLocalDate();
    }

    /**
     * This method gets LocalTime of an Appointment object. When this method is called a LocalTime object is returned
     * @return The LocalTime for the Appointment object
     */
    public LocalTime getStartTime() {
        return startDateTime.toLocalTime();
    }

    /**
     * This method gets LocalTime of an Appointment object. When this method is called a LocalTime object is returned
     * @return The LocalTime for the Appointment object
     */
    public LocalTime getEndTime() {
        return endDateTime.toLocalTime();
    }


}
