package controller;
import dao.AppointmentDao;
import dao.ClientDao;
import dao.UserDao;
import helper.ControllerHelper;
import helper.Utilities;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Appointment;
import model.Client;
import model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * This class contains methods to interact with "addAppointment.fxml" form. Author: Joseph Bruening
 */
public class AddAppointmentController implements Initializable {
    /**
     * Creates a Label object for displaying messages
     */
    public Label messageLabel;
    /**
     * Creates a save Button object
     */
    public Button saveButton;
    /**
     * Creates a cancel Button object
     */
    public Button cancelButton;
    /**
     * Creates a ComboBox object of Strings
     */
    public ComboBox<String> typeComboBox;
    /**
     * Creates a ComboBox object  of LocalTime objects
     */
    public ComboBox<LocalTime> startTimeComboBox;
    /**
     * Creates a ComboBox object of LocalTime objects
     */
    public ComboBox<LocalTime> endTimeComboBox;
    /**
     * Creates a ComboBox object of Customer objects
     */
    public ComboBox<Client> customerComboBox;
    /**
     * Creates a ComboBox object of User objects
     */
    public ComboBox<User> userComboBox;
    /**
     * Creates a ComboBox object of Strings
     */
    public ComboBox<String> locationComboBox;
    /**
     * Creates a TextField object
     */
    public TextField appIdTextField;
    /**
     * Creates a TextField object
     */
    public TextField titleTextField;
    /**
     * Creates a TextField object
     */
    public TextField descTextField;
    /**
     * Creates a DatePicker object
     */
    public DatePicker datePicker;


    /**
     * This method initializes the "addAppointments.fxml" form. When the form is loaded,
     * ComboBoxes will be loaded with preselected data.
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        //Prefill location combobox list
        ObservableList<String> locations = locationComboBox.getItems();
        locations.add("Coffee Shop");
        locations.add("Main Office");
        locations.add("Video Call");
        locations.add("Project site");

        //Prefill type combobox list
        typeComboBox.getItems();
        typeComboBox.setItems(Utilities.getAppointmentTypes());


        //Prefill Customer ComboBox
        customerComboBox.getItems();
        try {
            customerComboBox.setItems(ClientDao.getAll());
        }
        catch (SQLException e) {
            messageLabel.setText("There are currently no customers or something else went wrong");
            throw new RuntimeException(e);
        }

        //Prefill User ComboBox
        userComboBox.getItems();
        try {
            userComboBox.setItems(UserDao.getAll());
        }
        catch (SQLException e) {
            messageLabel.setText("something else went wrong");
            throw new RuntimeException(e);
        }

        //Prefill startTimeComboBox
        startTimeComboBox.getItems();
        startTimeComboBox.setItems(Utilities.getAppointmentTimes());
        //Prefill endTimeComboBox
        endTimeComboBox.getItems();
        endTimeComboBox.setItems(Utilities.getAppointmentTimes());

    }

    /**
     * This method will save changes. When the user interacts with the Button object,
     * a new Appointment object will be created and the database will be updated with the new object.
     * @param actionEvent
     * @throws Exception
     */
    public void onSaveButtonClick(ActionEvent actionEvent) throws Exception {


       try {
           String title = titleTextField.getText();
           String description = descTextField.getText();
           String location = String.valueOf(locationComboBox.getValue());
           String type = String.valueOf(typeComboBox.getValue());
           LocalDate date = datePicker.getValue();
           LocalTime start = startTimeComboBox.getValue();
           LocalTime end = endTimeComboBox.getValue();
           int customer = customerComboBox.getValue().getClientId();
           int user = userComboBox.getValue().getUserId();

           LocalDateTime startDateTime = LocalDateTime.of(date, start);
           LocalDateTime endDateTime = LocalDateTime.of(date, end);



           System.out.println(title + "|" + description + "|" + location + "|" + "|" + type + "|" + date + "|" + start + "|" + end + "|" + customer + "|" + user);

           if (title.isEmpty() || title.isBlank() || description.isBlank() || description.isEmpty() ||
                   locationComboBox.getValue() == null || typeComboBox.getValue() == null ||
                   startTimeComboBox.getValue() == null || endTimeComboBox.getValue() == null){
               messageLabel.setText("Please make sure all field are completed");
           }
           else if(startDateTime.isAfter(endDateTime)){
               messageLabel.setText("'End Time' cannot be before 'Start Time'");
           }
           else if(date.isBefore(LocalDate.now())){
               messageLabel.setText("Cannot schedule appointment in the past");
           }
            else if(Utilities.toTargetTime(startDateTime).isBefore(LocalDateTime.of(date, LocalTime.of(8, 00)))){
                messageLabel.setText("Start time not within business hours of 8 am - 10 pm EST");
           }
            else if (Utilities.toTargetTime(endDateTime).isAfter(LocalDateTime.of(date, LocalTime.of(22, 00)))){
                messageLabel.setText(("End time not within business hours of 8 am - 10 pm EST"));
           }
            else if (Utilities.isOverlapped(customer, 0, startDateTime, endDateTime)){
                messageLabel.setText("Customer with ID: " + customer + " has a scheduling conflict. Please fix times");
           }
           else {
               Appointment appointment = new Appointment(0, title, description, location, type, startDateTime, endDateTime, customer, user);
               AppointmentDao.insert(appointment);
               ControllerHelper.changeScene(actionEvent, "mainWindow.fxml", 964, 570);
           }
       }
       catch(Exception e){
           messageLabel.setText("All fields must be completed and text fields cannot exceed 50 characters");
       }



    }

    /**
     * This method will cancel changes. When the user interacts with the Button object, all changes on the form will
     * be canceled and the user will return to the "mainWindow.fxml" form.
     * @param actionEvent
     * @throws IOException
     */
    public void onCancelButtonClick(ActionEvent actionEvent) throws IOException {
        ControllerHelper.changeScene(actionEvent, "mainWindow.fxml", 964, 570);
    }

}
