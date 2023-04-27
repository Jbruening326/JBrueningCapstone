package dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import static helper.JDBC.connection;


/**
 * This abstract class contains methods to Create, Read, Update, and Delete Appointment objects from the database. Author: Joseph Bruening
 */
public abstract class AppointmentDao {

    /**
     * This method retrieves a single appointment record. When this method is called a single record
     * from the appointments table will be returned.
     * @param id The appointment_ID for the chosen appointment record to return.
     * @return Returns the desired appointment record.
     * @throws SQLException
     */
    public static Appointment get(int id) throws SQLException {
        Appointment appointment = null;
        String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID " +
                "FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");

             appointment = new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId);
        }
        return appointment;

    }

    /**
     * This method retrieves all appointment records. When this method is called, all records from the appointments
     * table will be returned.
     * @return Returns an observable list of all appointment records.
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAll() throws SQLException {
        String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID FROM appointments";
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        PreparedStatement ps = connection.prepareCall(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");

            Appointment appointment = new Appointment(appointmentId, title, description, location, type,
                    start, end, customerId, userId);

            allAppointments.add(appointment);

        }

        return allAppointments;
    }


    /**
     * This method inserts an appointment record. When this method is called, a new appointment record will be added to the
     * appointments table.
     * @param appointment The Appointment object that will inserted into the appointments table.
     * @return Returns the number of rows affected.
     * @throws SQLException
     */
    public static int insert(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointments " +
                "(Title, Description, Location, Type, Start, End, Customer_ID, User_ID) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());
        ps.setTimestamp(5, Timestamp.valueOf(appointment.getStartDateTime()));
        ps.setTimestamp(6, Timestamp.valueOf(appointment.getEndDateTime()));
        ps.setInt(7, appointment.getCustomerId());
        ps.setInt(8, appointment.getUserId());

        return ps.executeUpdate();
    }


    /**
     * This method will update an appointment record. When this method is called, a selected appointment record from the
     * appointments table will be updated from an Appointment object.
     * @param appointment The Appointment object that will update the existing appointment record.
     * @return Returns the number of rows affected.
     * @throws SQLException
     */
    public static int update(Appointment appointment) throws SQLException {

        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, " +
                "Start = ?, End = ?, Customer_ID = ?, User_ID = ? WHERE Appointment_ID = ? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());
        ps.setTimestamp(5, Timestamp.valueOf(appointment.getStartDateTime()));
        ps.setTimestamp(6, Timestamp.valueOf(appointment.getEndDateTime()));
        ps.setInt(7, appointment.getCustomerId());
        ps.setInt(8, appointment.getUserId());
        ps.setInt(9, appointment.getAppointmentId());


        return ps.executeUpdate();
    }

    /**
     * This method will delete an appointment record. When this method is called, a selected appointment record will
     * be removed from the appointments table.
     * @param appointment The Appointment object that will be deleted from the appointments table.
     * @return Returns the number of rows affected.
     * @throws SQLException
     */
    public static int delete(Appointment appointment) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, appointment.getAppointmentId());

        return ps.executeUpdate();
    }

    /**
     * This method will retrieve the number of appointments by a given month and appointment type.
     * When this method is called, the number of appointments is a given month with a certain type will be return.
     * @param month The month which will be queried for a selection
     * @param t the type which will be queried for a selection
     * @return Returns the size of the list from the results of the query
     * @throws SQLException
     */
    public static int getAppointmentByMonthType (int month, String t) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        Appointment appointment = null;

        String sql = "SELECT * FROM client_schedule.appointments WHERE MONTH(Start) = ? AND Type = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, month);
        ps.setString(2, t);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");

            appointment = new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId);
            appointments.add(appointment);
        }
        return appointments.size();
    }


}
