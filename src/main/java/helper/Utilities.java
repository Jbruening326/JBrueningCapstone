package helper;


import dao.AppointmentDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import java.io.*;
import java.sql.SQLException;
import java.time.*;
import java.util.Locale;
import java.util.stream.Collectors;


/**
 * This abstract class contains methods to assist with application functionalities. Author: Joseph Bruening.
 * Credits: Mkyong and Juan Ruiz for "filterAppointments" method snippets.
 */
public abstract class Utilities {
    /**
     * This method is used to obtain a ZoneID. When this method is called a ZoneId of the user will be returned.
     * @return Returns the user's ZoneID
     */
    public static ZoneId getZoneId(){
        return ZoneId.systemDefault();
    }//Not used at this time

    /**
     * This method is used to get the Local of the user. When this method is called,
     * the default Local of the user application is returned
     * @return Returns the default Local of the user of the application
     */
    public static Locale getLocale(){

        return Locale.getDefault();
    }


    /**
     * This method is used to filter appointment for a custom display. When this method is called, a passed start and
     * local date time is used to create a customer list of appointments based on the arguments.
     * <p><b>This method uses lambdas. The lambda in this method will place the Observable list in a stream which will and
     * filter out specific appointment that fall within a specified time frame and collects the results back into the Observable list.
     * This lambda allows for a faster approach to filtering appointments by removing everything you don't want to have in the list.
     * The longer approach would include a for loop with a conditional statement that adds appointments to the list based on
     * desired appointment window. Credit snippets of code to MKyong, and Juan Ruiz.</b></p>
     * @param start
     * @param end
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> filterAppointments(LocalDateTime start, LocalDateTime end) throws SQLException{
        ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();

        filteredAppointments = AppointmentDao.getAll().stream()
                .filter(a -> !a.getStartDateTime().isBefore(start)
                        && !a.getStartDateTime().isAfter(end))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        return filteredAppointments;
    }



    /**
     * This method is used to pre-populate approved appointment types. When this method is called the list of pre-approved
     * appointment types will be returned.
     * @return Returns the list of appointment types
     */
    public static ObservableList<String> getAppointmentTypes(){
        ObservableList<String> appointmentTypes = FXCollections.observableArrayList();

        appointmentTypes.add("Planning Session");
        appointmentTypes.add("De-Briefing");
        appointmentTypes.add("Building");
        appointmentTypes.add("Execution");
        appointmentTypes.add("Project Close");

        return appointmentTypes;
    }

    /**
     * This method is used to create a list of appointment times. When this method is called the list of appointment
     * times will be returned.
     * @return Returns the list of appointment times.
     */
    public static ObservableList<LocalTime> getAppointmentTimes(){
        ObservableList<LocalTime> appointmentTimes = FXCollections.observableArrayList();

        LocalTime start = LocalTime.of(0, 00);
        LocalTime end = LocalTime.of(23, 45);

        while(start.isBefore(end)){
            appointmentTimes.add(start);
            start = start.plusMinutes(15);
        }
        appointmentTimes.add(LocalTime.of(23, 45));

        return appointmentTimes;
    }

    /**
     * This method will be used to convert LocalDateTime to Eastern Standard Time(EST). When this method is called,
     * a LocalDateTime object will be converted to the LocalDateTime of EST which will be returned.
     * @param originTime The LocalDateTime object which will be converted
     * @return Returns the LocalDateTime in EST
     */
    public static LocalDateTime toTargetTime(LocalDateTime originTime){

        ZonedDateTime origin = originTime.atZone(ZoneId.systemDefault());
        ZonedDateTime target = origin.withZoneSameInstant(ZoneId.of("America/New_York"));

        return target.toLocalDateTime();

    }

    /**
     * This method will be used to convert LocalDateTime to UTC. When this method is called, a LocalDate Time object
     * will be converted to the LocalDateTime in UTC.
     * @param originTime The LocalDateTime object which will be converted
     * @return Returns the LocalDateTime in UTC
     */
    public static LocalDateTime toUTCTime(LocalDateTime originTime){
        ZonedDateTime origin = originTime.atZone(ZoneId.systemDefault());
        ZonedDateTime utcZone = origin.withZoneSameInstant(ZoneId.of("UTC"));

        return utcZone.toLocalDateTime();

    }

    /**
     * This method is used to assess for overlaps in LocalDateTimeObjects. When this method is called, passed arguments
     * are usd to check if two appointment times are overlapped and will return true or false based on the conditions.
     * @param uId2 The userId of an appointment to be assessed
     * @param aId2 The appointmentId of an appointment to be assessed
     * @param s2 The StartDateTime of an appointment to be assessed
     * @param e2 The EndDateTime of an appointment to be assessed
     * @return Returns false if no overlaps occur. Returns ture is overlaps are present
     * @throws SQLException
     */
    public static boolean isOverlapped(int uId2, int aId2,  LocalDateTime s2, LocalDateTime e2) throws SQLException{
        boolean isOverlapped = false;

        for(Appointment a : AppointmentDao.getAll()){
            int cId1 = a.getClientId();
            int aId1 = a.getAppointmentId();
            LocalDateTime s1 = a.getStartDateTime();
            LocalDateTime e1 = a.getEndDateTime();

            if(aId2 != aId1 ){
                if(uId2 == cId1){
                    if((s2.isAfter(s1) || s2.isEqual(s1)) && (s2.isBefore(e1))){
                        isOverlapped = true;
                    }
                    else if((e2.isAfter(s1)) && (e2.isBefore(e1) || e2.isEqual(e1))){
                        isOverlapped = true;
                    }
                    else if((s2.isBefore(s1) || s2.isEqual(s1))&&(e2.isAfter(e1) || e2.isEqual(e1))){
                        isOverlapped = true;
                    }
                }
            }
        }
        return isOverlapped;
    }


    /**
     * This method is used to append PrintWriter object. When this method is called a passed file name is appended with
     * the message of a passed String object.
     * @param fileName The file name to be appended
     * @param message The message to append the file with
     */
    public static void loginActivity (PrintWriter fileName, String message){


        fileName.println(message);
        fileName.close();
    }
}
