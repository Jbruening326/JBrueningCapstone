package controller;

import dao.AppointmentDao;
import dao.CountryDao;
import dao.ClientDao;
import dao.FirstLevelDivisionDao;
import helper.ControllerHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Client;
import model.Country;
import model.FirstLevelDivision;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This class contains methods to interact with the "customers.fxml" form. Author: Joseph Bruening
 */
public class ClientController implements Initializable {
    /**
     * Creates a Label object
     */
    public Label messageLabel;
    /**
     * Creates a save Button object
     */
    public Button saveButton;
    /**
     * Creates a back Button object
     */
    public Button backButton;
    /**
     * Creates a delete Button object
     */
    public Button deleteButton;
    /**
     * Creates a RadioButton object belonging to a toggle group
     */
    public RadioButton addRadioButton;
    /**
     * Creates a RadioButton object belonging to a toggle group
     */
    public RadioButton updateRadioButton;
    /**
     * Creates a TextField object
     */
    public TextField customerIdTextField;
    /**
     * Creates a TextField object
     */
    public TextField nameTextField;
    /**
     * Creates a TextField object
     */
    public TextField addressTextField;
    /**
     * Creates a TextField object
     */
    public TextField postTextField;
    /**
     * Creates a TextField object
     */
    public TextField phoneTextField;
    /**
     * Creates a ComboBox object of Country objects
     */
    public ComboBox<Country> countryComboBox;
    /**
     * Creates a ComboBox object of FirstLevelDivision objects
     */
    public ComboBox<FirstLevelDivision> firstLevelComboBox;
    /**
     * Creates a TableView object of Customer objects
     */
    public TableView<Client> customerTableView;
    /**
     * Creates a TableColumn object
     */
    public TableColumn<Client, Integer> customerIdCol;
    /**
     * Creates a TableColumn object
     */
    public TableColumn<Client, String> nameCol;
    /**
     * Creates a TableColumn object
     */
    public TableColumn<Client, String> addressCol;
    /**
     * Creates a TableColumn object
     */
    public TableColumn<Client, String> postalCol;
    /**
     * Creates a TableColumn object
     */
    public TableColumn<Client, String> phoneCol;
    /**
     * Creates a TableColumn object
     */
    public TableColumn<Client, String> countryCol;
    /**
     * Creates a TableColumn object
     */
    public TableColumn<Client, String> firstLevelCol;


    /**
     * This method initializes the "customer.fxml" form. When the form is loaded,
     * the TableView object will be pre-populated with Customer objects.
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customerTableView.setItems(ClientDao.getAll());
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
            postalCol.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            countryCol.setCellValueFactory(new PropertyValueFactory<>("Country"));
            firstLevelCol.setCellValueFactory(new PropertyValueFactory<>("Division"));

            countryComboBox.getItems();
            countryComboBox.setItems(CountryDao.getAll());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            messageLabel.setText("There was a problem loading data");
        }
    }


    /**
     * This method will perform an action when a selection is made on the countryComboBox. When a selection is made
     * to the countryComboBox, FirstLevelDivision objects will be populated in the firstLevelComboBox based on
     * the Country object selected.
     * @param actionEvent
     * @throws SQLException
     */
    public void onCountryComboSelection(ActionEvent actionEvent) throws SQLException{
        firstLevelComboBox.setValue(null);
        Country country = countryComboBox.getValue();

        ObservableList<FirstLevelDivision> firstLevelDivision = FXCollections.observableArrayList();
        for(FirstLevelDivision fLD : FirstLevelDivisionDao.getAll()){
            if(country.getCountryId() == fLD.getCountryId()){
                firstLevelDivision.add(fLD);
            }
        }
        firstLevelComboBox.setItems(firstLevelDivision);
    }

    /**
     * This method will delete a selected Customer object. When the delete Button object is interacted with,
     * the selected customer will be removed along with any associated appointments.
     * @param actionEvent
     * @throws SQLException
     */
    public void onDeleteButtonClick(ActionEvent actionEvent) throws SQLException{
        Client selectedClient = customerTableView.getSelectionModel().getSelectedItem();

        if(selectedClient != null){
            messageLabel.setText(null);

            if(ControllerHelper.confirmationAlert("Are you sure? " +
                    "Deleting customer will also remove associated appointments!") == ButtonType.YES){
                int customerId = selectedClient.getClientId();
                for(Appointment appointment : AppointmentDao.getAll()){
                    if(appointment.getClientId() == customerId){
                        AppointmentDao.delete(appointment);
                    }
                }
                ClientDao.delete(selectedClient);
                
                ControllerHelper.messageDisplay("Customer Deleted","Customer with ID, " +
                        selectedClient.getClientId() +
                        ", and name, " + selectedClient.getClientName() + ", has been deleted");
                customerTableView.setItems(ClientDao.getAll());
            }
        }
        else {
            messageLabel.setText("Please make a selection");
        }
    }

    /**
     * This method will clear data in the form. When the RadioButton object is selected, form data will be cleared.
     * @param actionEvent
     */
    public void onAddRadioButtonClick(ActionEvent actionEvent) {
        customerIdTextField.clear();
        nameTextField.clear();
        addressTextField.clear();
        postTextField.clear();
        phoneTextField.clear();

        saveButton.setText("Save");
    }

    /**
     * This method will populate Customer object data into the form. When the RadioButton object is selected,
     * a selected Customer object data will be populated into the form fields and ComboBox object.
     * @param actionEvent
     * @throws Exception
     */
    public void onUpdateRadioButtonClick(ActionEvent actionEvent) throws Exception {
        messageLabel.setText(null);


        try {
            customerIdTextField.setText(String.valueOf(customerTableView.getSelectionModel().getSelectedItem().getClientId()));
            nameTextField.setText(customerTableView.getSelectionModel().getSelectedItem().getClientName());
            addressTextField.setText(customerTableView.getSelectionModel().getSelectedItem().getAddress());
            postTextField.setText(customerTableView.getSelectionModel().getSelectedItem().getPostalCode());
            phoneTextField.setText(customerTableView.getSelectionModel().getSelectedItem().getPhone());
            countryComboBox.setValue(customerTableView.getSelectionModel().getSelectedItem().getCountry());
            firstLevelComboBox.setValue(customerTableView.getSelectionModel().getSelectedItem().getDivision());

            saveButton.setText("Update");
        }
        catch(Exception e){
            messageLabel.setText("Please select a customer first");
            addRadioButton.fire();
        }



    }

    /**
     * This method will create or update Customer objects. When the save/update Button object is interacted with.,
     * information from the field and ComboBox object will be used to create or update Customer objects as well
     * as make appropriate changes to the database.
     * @param actionEvent
     * @throws SQLException
     */
    public void onSaveButtonClick(ActionEvent actionEvent) throws SQLException {
        try {
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            String postal = postTextField.getText();
            String phone = phoneTextField.getText();
            Country country = countryComboBox.getValue();
            FirstLevelDivision firstLevel = firstLevelComboBox.getValue();

            if (name.isEmpty() || name.isBlank() || address.isEmpty() || address.isBlank() || postal.isEmpty()
                    || postal.isBlank() || phone.isEmpty() || phone.isBlank()
                    || countryComboBox.getValue() == null || firstLevelComboBox.getValue() == null) {
                messageLabel.setText("Please make sure all fields are completed");
            } else {
                if (customerIdTextField.getText().isBlank() || customerIdTextField.getText().isEmpty()) {
                    Client client = new Client(0, name, address, postal, phone, firstLevel.getDivisionId());
                    ClientDao.insert(client);
                } else {
                    int customerId = Integer.parseInt(customerIdTextField.getText());
                    Client client = new Client(customerId, name, address, postal, phone, firstLevel.getDivisionId());
                    ClientDao.update(client);
                }
                customerIdTextField.clear();
                nameTextField.clear();
                addressTextField.clear();
                postTextField.clear();
                phoneTextField.clear();
                addRadioButton.fire();
            }
            customerTableView.setItems(ClientDao.getAll());
        }
        catch(SQLException e){
            messageLabel.setText("All fields must be completed and text fields have character limits");
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
}
