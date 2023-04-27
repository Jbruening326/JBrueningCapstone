package com.example.c868jbruening;


import helper.JDBC;
import helper.Utilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



/**
 * This is the Main class. This method provides additional methods for the initial setup of the application. Author: Joseph Bruening.
 */
public class Main extends Application implements Initializable {

    /**
     * This method starts the application. When the application is opened, the Login form will be displayed.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 300);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method load initial information to the application. When the login form is loaded, the users location is retrieved.
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle){
        Utilities.getLocale().getLanguage();
    }


    public static void main(String[] args) {

        JDBC.openConnection();



        launch();



        JDBC.closeConnection();
    }
}