package controller;

import dao.UserDao;
import helper.ControllerHelper;
import helper.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class is a controller for the "logIn.fxml" form. Author: Joseph Bruening
 */
public class LogInController implements Initializable {
    /**
     * Creates a TextField object
     */
    public TextField userNameTextField;
    /**
     * Creates a TextField object
     */
    public TextField passwordTextField;
    /**
     * Creates a login Button object
     */
    public Button loginButton;
    /**
     * Creates an exit Button object
     */
    public Button exitButton;
    /**
     * Creates a Label object to display the ZoneId
     */
    public Label zoneIdLabel;
    /**
     * Creates a Label object for messages to be displayed within the form
     */
    public Label errorLabel;
    /**
     * Creates a Label object for a title that may be changed based on the language
     */
    public Label titleLabel;
    /**
     * Creates a Label object for Login that may be changed based on the language
     */
    public Label loginLabel;
    /**
     * Creates a Label object for username that may be changed based on the language
     */
    public Label userNameLabel;
    /**
     * Creates a Label object for password that may be changed based on the language
     */
    public Label passwordLabel;

    //Will be used on errorLabel based on user inputs.

    private String wrongPassword = "Please enter a valid password";
    private String wrongLogin = "Please enter a valid user name and password";

    /**
     * This method initializes the "logIn.fxml: form. When the login form is loaded,
     * text values are changed to French based on the users Locale.
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle){
        Locale locale = Utilities.getLocale();
        if(locale.getLanguage().equals("fr")){
            ResourceBundle rb = ResourceBundle.getBundle("Languages", locale);
            titleLabel.setText(rb.getString("Appointment") + " " + rb.getString("System"));
            loginLabel.setText(rb.getString("Login"));
            userNameLabel.setText(rb.getString("User") + " " + rb.getString("Name"));
            passwordLabel.setText(rb.getString("Password"));
            loginButton.setText(rb.getString("Login"));
            exitButton.setText(rb.getString("Exit"));

            wrongPassword = rb.getString("Please") + " " + rb.getString("enter") + " " + rb.getString("a")
            + " " + rb.getString("valid") + " " + rb.getString("password");

            wrongLogin = rb.getString("Please") + " " + rb.getString("enter") + " " + rb.getString("a")
                    + " " + rb.getString("valid") + " " + rb.getString("user") + " " + rb.getString("name")
                    + " " + rb.getString("and") + " " + rb.getString("password");
        }

        zoneIdLabel.setText(String.valueOf(ZoneId.systemDefault()));
    }

    /**
     * This method logs a user into the system. When the user interacts with the login button, the user will gain access
     * to the system if certain fields are correct.
     * @param actionEvent
     * @throws Exception
     */
    public void onLoginClick(ActionEvent actionEvent) throws Exception {
        String userName = userNameTextField.getText();
        String password = passwordTextField.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime loginTimeUTC = Utilities.toUTCTime(LocalDateTime.now());
        String loginTime = loginTimeUTC.format(formatter);
        String fileName = "login_activity.txt";

        FileWriter fw = new FileWriter(fileName, true);
        PrintWriter outputFile = new PrintWriter(fw);

        if (UserDao.get(userName) != null) {
            if (password.equals(UserDao.get(userName).getPassword())){
                String message = "User, " + userName + ", successfully logged in at " + loginTime + " UTC";
                Utilities.loginActivity(outputFile, message);

                ControllerHelper.changeScene(actionEvent, "mainWindow.fxml", 964, 570);

                FXMLLoader fxmlLoader = ControllerHelper.getFxmlLoader();
                MainWindowController MWController = fxmlLoader.getController();
                MWController.previewAppointments();
            }
            else {
                String message2 = "User, " + userName + ", unsuccessfully logged in at " + loginTime + " UTC";
                Utilities.loginActivity(outputFile, message2);
                errorLabel.setText(wrongPassword);
            }

        } else {
            String message3 = "User, " + userName + ", unsuccessfully logged in at  " + loginTime + " UTC";
            Utilities.loginActivity(outputFile, message3);
            errorLabel.setText(wrongLogin);
        }
    }

    /**
     * This method closes the application. When the user interacts with the exit button, the application will be closed.
     * @param actionEvent
     */
    public void onExitButtonClick(ActionEvent actionEvent) {
        Locale locale = Utilities.getLocale();
        String alert = "Are you sure you want to exit?";

        if(locale.getLanguage().equals("fr")){
            ResourceBundle rb = ResourceBundle.getBundle("Languages", locale);
            alert = rb.getString("Are") + " " + rb.getString("you") + " " + rb.getString("sure")
                    + " " + rb.getString("you") + " " + rb.getString("want") + " " + rb.getString("to")
                    + " " + rb.getString("exit") + "?";
        }
        if(ControllerHelper.confirmationAlert(alert) == ButtonType.YES){
            System.exit(0);
        }
    }
}