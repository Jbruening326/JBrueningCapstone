package helper;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This abstract class contains methods to open and close the connection to the database.
 */
public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "appointment_schedule_c868";
    private static final String jdbURL = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "sqlUser";
    private static final String password = "Passw0rd!";
    public static Connection connection;

    /**
     * This method connects to the database. When this method is called, the database connection will be established.
     */
    public static void openConnection(){
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbURL, userName, password);
            System.out.println("Connection Successful!");
        }
        catch(Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }

    /**
     * This method disconnects the database. When this method is called, the database connection will be terminated.
     */
    public static void closeConnection(){
        try{
            connection.close();
            System.out.println("Connection Closed!");
        }
        catch(Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }




}
