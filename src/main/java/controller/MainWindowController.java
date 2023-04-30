package controller;
import dao.AppointmentDao;
import helper.ControllerHelper;
import helper.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * This class is a controller for the "mainWindow.fxml" form. Author: Joseph Bruening
 */
public class MainWindowController implements Initializable{
    /**
     * Creates a Radio Button object for weekly view of appointments
     */
    public RadioButton weekViewRadio;
    /**
     * Creates a Radio Button object for month view of appointments
     */
    public RadioButton monthViewRadioButton;
    /**
     * Creates a Radio Button Object for all appointments
     */
    public RadioButton allAppointmentsRadioButton;
    /**
     * Creates a Button object for an Add Appointments button
     */
    public Button addButton;
    /**
     * Creates a Button object for an Update Appointments button
     */
    public Button updateButton;
    /**
     * Creates a Button object for a Cancel Appointment button
     */
    public Button cancelButton;
    /**
     * Creates a Button object for a Customers button
     */
    public Button customersButton;
    /**
     * Creates a Button object for a Reports button
     */
    public Button reportsButton;
    /**
     * Creates a Button object for an Exit button
     */
    public Button exitButton;
    /**
     * Creates a Label object to be able to display messages within the program
     */
    public Label messageLabel;

    /**
     * Creates a TableView object for Appointment objects to be displayed in
     */
    public TableView<Appointment> appointmentsTableView;
    /**
     * Creates a TableColumn object for an Appointment object field to be displayed in
     */
    public TableColumn<Appointment, Integer> appIdCol;
    /**
     * Creates a TableColumn object for an Appointment object field to be displayed in
     */
    public TableColumn<Appointment, String> titleCol;
    /**
     * Creates a TableColumn object for an Appointment object field to be displayed in
     */
    public TableColumn<Appointment, String> descCol;
    /**
     * Creates a TableColumn object for an Appointment object field to be displayed in
     */
    public TableColumn<Appointment, String> locCol;
    /**
     * Creates a TableColumn object for an Appointment object field to be displayed in
     */
    public TableColumn<Appointment, String> contCol;
    /**
     * Creates a TableColumn object for an Appointment object field to be displayed in
     */
    public TableColumn<Appointment, String> typeCol;
    /**
     * Creates a TableColumn object for an Appointment object field to be displayed in
     */
    public TableColumn<Appointment, LocalDate> dateCol;
    /**
     * Creates a TableColumn object for an Appointment object field to be displayed in
     */
    public TableColumn<Appointment, LocalTime> startCol;
    /**
     * Creates a TableColumn object for an Appointment object field to be displayed in
     */
    public TableColumn<Appointment, LocalTime> endCol;
    /**
     * Creates a TableColumn object for an Appointment object field to be displayed in
     */
    public TableColumn<Appointment, Integer> clientIdCol;
    /**
     * Creates a TableColumn object for an Appointment object field to be displayed in
     */
    public TableColumn<Appointment, Integer> userIdCol;
    public TextField searchTextField;

    /**
     * This method initializes the "mainWindow.fxml" form. When the form is loaded, the table is populated
     * with all appointments.
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle){

        try {
            appointmentsTableView.setItems(AppointmentDao.getAll());
            appIdCol.setCellValueFactory(new PropertyValueFactory<>("AppointmentId"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
            descCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
            locCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("LocalDate"));
            startCol.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
            endCol.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
            clientIdCol.setCellValueFactory(new PropertyValueFactory<>("ClientId"));
            userIdCol.setCellValueFactory(new PropertyValueFactory<>("UserId"));

        }
        catch(Exception e){
            messageLabel.setText("There are currently no appointments");
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method scans for upcoming appointments. When this method is called, an appointment within the next 15 minutes
     * will display a message to the user.
     * @throws SQLException
     */
    public void previewAppointments() throws SQLException{
        try {
            ObservableList<Appointment> allAppointments = AppointmentDao.getAll();

            LocalDateTime rightNow = LocalDateTime.now();
            for (Appointment appointment : allAppointments){
                if(rightNow.isBefore(appointment.getStartDateTime())
                        && !appointment.getStartDateTime().isAfter(rightNow.plusMinutes(16))){
                    ControllerHelper.messageDisplay("Upcoming Appointment", appointment.getTitle() +
                            " appointment at " + String.valueOf(appointment.getStartDateTime()) + " starts soon!");
                }
                else{
                    messageLabel.setText("There are no upcoming appointments");
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

        /**
         * This method will filter all appointments by week. When the radio button is fired, the tableview will be changed
         * to display all appointments within the next rolling 7 days.
         * @param actionEvent
         * @throws SQLException
         */
    public void onWeekViewRadioButton(ActionEvent actionEvent) throws SQLException {
        messageLabel.setText(null);
        LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.parse("00:00"));
        LocalDateTime end = LocalDateTime.of(LocalDate.now().plusDays(7), LocalTime.parse("23:59"));
        ObservableList<Appointment> weeklyAppointments = Utilities.filterAppointments(start, end);

        if(weeklyAppointments.isEmpty()){
            appointmentsTableView.setItems(null);
            messageLabel.setText("No appointments this week");
        }
        else{
            appointmentsTableView.setItems(weeklyAppointments);
        }

    }

    /**
     * This method will filter all appointments by month. When the radio button is fired, the tableview will be changed
     * to display all appointments within the next rolling 30 days.
     * @param actionEvent
     * @throws SQLException
     */
    public void onMonthViewRadioButton(ActionEvent actionEvent) throws SQLException{
        messageLabel.setText(null);
        LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.parse("00:00"));
        LocalDateTime end = LocalDateTime.of(LocalDate.now().plusDays(30), LocalTime.parse("23:59"));
        ObservableList<Appointment> monthlyAppointments = Utilities.filterAppointments(start, end);

        if(monthlyAppointments.isEmpty()){
            appointmentsTableView.setItems(null);
            messageLabel.setText("No appointments this month");
        }
        else{
            appointmentsTableView.setItems(monthlyAppointments);
        }
    }

    /**
     * This method will display all appointments. When the radio button is fired, the tableview will be changed
     * to display all appointments.
     * @param actionEvent
     * @throws SQLException
     */
    public void onAllAppointmentsRadioButton(ActionEvent actionEvent) throws Exception {
        messageLabel.setText(null);
        appointmentsTableView.setItems(AppointmentDao.getAll());
    }

    /**
     * This method changes the form. When the Add Appointment button is interacted with, the from will be changed to the
     * "addAppointment.fxml" form.
     * @param actionEvent
     * @throws IOException
     */
    public void onAddButtonClick(ActionEvent actionEvent) throws IOException {
        ControllerHelper.changeScene(actionEvent, "addAppointment.fxml", 732, 515);
    }

    /**
     * This method changes the form. When the Button object is interacted with, the from will be changed to the
     * "updateAppointment.fxml" form.
     * @param actionEvent
     * @throws IOException
     */
    public void onUpdateButtonClick(ActionEvent actionEvent) throws Exception {

            Appointment selectedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();
            if(selectedAppointment != null){
                ControllerHelper.changeScene(actionEvent, "updateAppointment.fxml", 732, 515);

                FXMLLoader fxmlLoader = ControllerHelper.getFxmlLoader();
                UpdateAppointmentController UAController = fxmlLoader.getController();
                UAController.sendAppointment(selectedAppointment);
            }
            else{
                messageLabel.setText("Please select an appointment first");
            }
    }

    /**
     * This method will remove a selected appointment. When the Button object is interacted with, A selected
     * appointment from the table will be removed.
     * @param actionEvent
     * @throws SQLException
     */
    public void onCancelButtonClick(ActionEvent actionEvent) throws SQLException {
        Appointment selectedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();

        if(selectedAppointment != null){
            messageLabel.setText(null);
            if(ControllerHelper.confirmationAlert("Are you sure you want to delete appointment?") == ButtonType.YES){
                AppointmentDao.delete(selectedAppointment);
                ControllerHelper.messageDisplay("Appointment Canceled","Appointment with ID, " +
                        selectedAppointment.getAppointmentId() +
                        ", and Type, " + selectedAppointment.getType() + ", has been canceled");
                appointmentsTableView.setItems(AppointmentDao.getAll());
            }
        }
        else {
            messageLabel.setText("Please make a selection");
        }
    }

    /**
     * This method changes the form. When the Button object is interacted with, the from will be changed to the
     * "client.fxml" form.
     * @param actionEvent
     * @throws IOException
     */
    public void onCustomersButtonClick(ActionEvent actionEvent) throws IOException {
        ControllerHelper.changeScene(actionEvent, "client.fxml", 970, 631);
    }

    /**
     * This method closes the program. When Exit button is interacted with, the application will be closed.
     * @param actionEvent
     */
    public void onExitButtonClick(ActionEvent actionEvent) {
        if(ControllerHelper.confirmationAlert("Are you sure you want to exit?") == ButtonType.YES){
            System.exit(0);
        }
    }

    /**
     * This method changes the form. When the Reports button is interacted with, the from will be changed to the
     * "reports.fxml" form.
     * @param actionEvent
     * @throws IOException
     */
    public void onReportsButtonClick(ActionEvent actionEvent) throws IOException {
        ControllerHelper.changeScene(actionEvent, "reports.fxml", 852, 579);
    }

    public void onSearch(ActionEvent actionEvent) throws SQLException {
        String searchItem = searchTextField.getText();

        if(searchItem.isEmpty() || searchItem.isBlank()){
            appointmentsTableView.setItems(AppointmentDao.getAll());
        }
        else{
            ObservableList<Client> foundClients = Client.lookupClient(searchItem);
            ObservableList<User> foundUsers = User.lookupUser(searchItem);
            ObservableList<Appointment> foundAppointments = Appointment.searchAppointments(foundClients, foundUsers);

            if (foundClients.size() == 0 && foundUsers.size() == 0){
                messageLabel.setText("No appointments found");
            }
            else{
               appointmentsTableView.setItems(foundAppointments);

            }
        }
    }
}
