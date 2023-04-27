package helper;

import com.example.c868jbruening.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class contains methods that will be used to assist in controller class functionalities. Author: Joseph Bruening
 */
public abstract class ControllerHelper {

    /**
     * Creates an FXMLoader object
     */
    private static FXMLLoader fxmlLoader;

    /**
     * This method will be used to assist controller classes when changing between forms. When this method is called,
     * the arguements passed into the method will be used the load up the correct form.
     *
     * @param actionEvent
     * @param fxmlName    The name of the fxml file which will be opened
     * @param width       The width of the fxml file which will be opened
     * @param height      The height of the fxml file which will be opened
     * @throws IOException
     */
    public static void changeScene(ActionEvent actionEvent, String fxmlName, int width, int height) throws IOException {

        try {
            Stage stage;

            fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlName));
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stage.setTitle("Login Application");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * This method is used to retrieve FXMLLoader object on a given controller. When this method is called the
     * FXMLLoader object will be returned.
     *
     * @return Returns FXMLLoader object
     */
    public static FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    /**
     * This method displays a Confirmation AlertType object. When this method is called a passed String will be used to
     * display the specific message provided in the confirmation alert and returns the result of the action taken by
     * the user.
     *
     * @param message The message the confirmation alert will be providing
     * @return Returns the result of the button selection made
     */
    public static ButtonType confirmationAlert(String message) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.CANCEL);
        confirmation.showAndWait();

        return confirmation.getResult();
    }

    /**
     * This method displays a custom message. When this method is called a customer message is displayed to the user
     * based on a passed String object.
     *
     * @param header  The header of the message to be displayed
     * @param message The message which will be displayed
     */
    public static void messageDisplay(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
