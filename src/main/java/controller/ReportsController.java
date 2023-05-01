package controller;

import dao.AppointmentDao;
import dao.ClientDao;
import dao.CountryDao;
import dao.UserDao;
import helper.ControllerHelper;
import helper.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Country;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * This class contains methods to interact with the "reports.fxml" form. Author: Joseph Bruening
 */
public class ReportsController implements Initializable {

    /**
     * Creates a ComboBox object to display Integers
     */
    public ComboBox<Integer> monthComboBox;
    /**
     * Creates a ComboBox object to display Strings
     */
    public ComboBox<String> typeComboBox;
    /**
     * Creates a ComboBox object to display Country objects
     */
    public ComboBox<Country> countryComboBox;
    /**
     * Creates a Label object
     */
    public Label messageLabel;
    /**
     * Creates a Label object for displaying messages to the user if needed
     */
    public Label apptResultLabel;
    /**
     * Creates a Label object for displaying results of a query
     */
    public Label countryResultLabel;
    /**
     * Creates a  back Button object
     */
    public Button backButton;
    /**
     * Creates a TableView object of Appointment objects
     */
    public TableView<Appointment> appointmentTableView;
    /**
     * Creates a TableColumn object
     */
    public TableColumn<Appointment, Integer> apptIdColumn;
    /**
     * Creates a TableColumn object
     */
    public TableColumn<Appointment, String> titleColumn;
    /**
     * Creates a TableColumn object
     */
    public TableColumn<Appointment, String> typeColumn;
    /**
     * Creates a TableColumn object
     */
    public TableColumn<Appointment, String> descriptionColumn;
    /**
     * Creates a TableColumn object
     */
    public TableColumn<Appointment, LocalDateTime> startColumn;
    /**
     * Creates a TableColumn object
     */
    public TableColumn<Appointment, LocalDateTime> endColumn;
    /**
     * Creates a TableColumn object
     */
    public TableColumn<Appointment, Integer >clientIdColumn;
    public ComboBox<User> userComboBox;
    public Label userMessageLabel;


    /**
     * This method initializes the "reports.fxml" form. When the form is loaded, ComboBox objects will be pre-populated.
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Prefill monthComboBox
        ObservableList<Integer> months = FXCollections.observableArrayList();
        for(int i = 1; i <=12 ; i++){
            months.add(i);
        }
        monthComboBox.setItems(months);

        //Prefill typeComboBox
        typeComboBox.getItems();
        typeComboBox.setItems(Utilities.getAppointmentTypes());

        //Prefill userComboBox
        //Change to userComboBox
        userComboBox.getItems();
        try {
            userComboBox.setItems(UserDao.getAll());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        //Prefill countyComboBox
        countryComboBox.getItems();
        try {
            countryComboBox.setItems(CountryDao.getAll());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * This method launch a another form. When the user interacts with the Button object,
     * the user will return to the "mainWindow.fxml" form.
     * @param actionEvent
     * @throws IOException
     */
    public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
        ControllerHelper.changeScene(actionEvent, "mainWindow.fxml", 964, 570);
    }

    /**
     * This method will perform a query when an item is select. When a selection is made on the countryComboBox,
     * a query will be executed with the result being displayed on the countryResultLabel.
     * @param actionEvent
     * @throws SQLException
     */
    public void onCountrySelect(ActionEvent actionEvent) throws SQLException {
        Country country = countryComboBox.getValue();
        countryResultLabel.setText(String.valueOf(ClientDao.getClientsByCountry(country)));

    }

    /**
     * This method will perform an action based on the selection made. When a selection is made on the monthComboBox,
     * the typComboBox will be enabled.
     * @param actionEvent
     */
    public void onMonthSelect(ActionEvent actionEvent) {
        typeComboBox.setDisable(false);
        apptResultLabel.setText(null);
    }

    /**
     * This method will perform a query when an item is selected. When a selection is made on the typeComboBox,
     * a query will be executed with the result being displayed on the appResultLabel.
     * @param actionEvent
     * @throws SQLException
     */
    public void onTypeSelect(ActionEvent actionEvent) throws SQLException {
        int month = monthComboBox.getValue();
        String t = typeComboBox.getValue();
        apptResultLabel.setText(String.valueOf(AppointmentDao.getAppointmentByMonthType(month, t)));
    }


    /**
     * This method will filter appointments using a <b>lambda</b> on a ComboBox object selection. When the ComboBox object is
     * selected, the lambda expression will be used to filter through all appointments and collect only appointments
     * matching a given contactId. This lambda reduced the need to write another sql statement to query the data and return
     * all appointments based on contactId.
     * @param actionEvent
     * @throws SQLException
     */
    public void onUserSelect(ActionEvent actionEvent) throws SQLException {
        userMessageLabel.setText(null);
        int userId = userComboBox.getValue().getUserId();
        ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();

        filteredAppointments = AppointmentDao.getAll().stream()
                .filter(a -> a.getUserId() == userId)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        if(filteredAppointments.isEmpty()){
            userMessageLabel.setText("No appointments for selected contact");
        }
        else {
            appointmentTableView.setItems(filteredAppointments);
            apptIdColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentId"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
            startColumn.setCellValueFactory(new PropertyValueFactory<>("StartDateTime"));
            endColumn.setCellValueFactory(new PropertyValueFactory<>("EndDateTime"));
            clientIdColumn.setCellValueFactory(new PropertyValueFactory<>("ClientId"));
        }
    }
}
