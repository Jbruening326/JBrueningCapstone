Title: Appointment Scheduling Application
Purpose: The purpose of this application is to allow users to schedule appointments with their clients.

Author Information
Name: Joseph Bruening
Contact: Email jbruen2@wgu.edu  StudentID 00168932
Version: QAM2  Date: 02/05/2023

IDE Information
IDE: Intellij IDEA 2022.2.3 Community Edition
JDK: Java SE 17.0.5
JavaFX: JavaFX-base 17.0.2
MySQL Connector: mysql-connector-j-8.0.31

How to run the program


Addition Report Information

The application opens with the login form. This form will be displayed in English or French
based on the user's ZoneID. The ZoneID is displayed in English only on the bottom left of the form.
Use the username "test" and password "test" to gain access to the application. The exit button closes
the application.

The next form is the main window or appointment dashboard. This form provides a table display of
all appointments. Use the radio buttons to filter appointment time windows. The month view is
filtered by a rolling 30 days from the current day and the week view is a rolling 7 days from the
current day. The "reports", "add appointment", "update appointment", and "customers" buttons will take the
user to the respected form. To cancel an appointment, the user must first select an appointment from
the table. If an appointment is taking place within 15 minutes of the user's current time, a notification
will appear from this form. Errors are displayed in red text at the bottom of this form.

The "add appointment" form is fairly intuitive. All fields must be complete for the creation
to be successful. The naming convention for location and types has been standardized. Users can only make
selections based on the options provided. Users cannot pick days in the past. The end time cannot come before
the start time and the selected times must be within business hours which are 8:00 am - 10:00 pm 7 days
a week under Eastern Standard Time. An appointment will not be created if the selected customer has an
overlapping appointment already. The cancel button takes the user back to the "appointment dashboard"
without making any changes. Errors are displayed in red text at the bottom of this form.

The "update appointment" form is the same as the "add appointment form." The selected appointment information
from the "appointment dashboard" will be populated into the respective fields.

The "customers" form is where users can add, update, and remove customers all from one form. The user must
complete all fields for an add or update to be successful. To update a customer,
a selection must be made from the table before using the "update customer" radio button.
The country must be selected first before a state can be selected as this field is populated based on the
selection of the country. A customer must be selected before the delete button can be used.
The back button returns the user to the "appointment dashboard" form. Errors are displayed in
red text at the bottom of this form.

The "reports" form has 3 separate reports that can be run. For the "appointments by month and type report," a month
has to be selected first to be able to select a type. Once the type is selected, the count of appointments
by the selected month and type will be displayed. For the "contact schedule" report, simply select a contact from
the dropdown. The corresponding table will display all appointments for the selected contact. If no appointment exists,
a message will be displayed. The last report is the "customer by country" report. When the user selects a country,
the number of customers that belong to the selected country will be displayed. The back button will navigate
the user back to the "appointment dashboard" form.