import java.io.Serializable;
import java.util.Calendar;

/**
 * Task Organiser is a simple application which allows users to manage
 * their tasks. Using a text based interface, it allows the user to
 * manipulate tasks individually or in a collection. The application
 * allows you to explicitly save and implicitly load the state of your
 * task organiser each time you use the system.
 *
 * The TaskDate class models the due date of a task. It will prepare
 * a due date for a task to have a default state. And enforce
 * expected due date values to ensure the task organiser can perform
 * its functions.
 */

public class TaskDate implements Serializable {

    private Calendar dueDate = Calendar.getInstance();

    /**
     * A dueDate of a task will always start at a default state
     * of an instance of the Calendar class. This is so we can
     * compare dates that haven't been set, to ones that have.
     */

    public TaskDate() {
        dueDate.clear();
    }


    /**
     * @return Return the due date of the task
     */


    public Calendar getDueDate() {
        return dueDate;
    }

    /**
     * Set the due date of a task relative to the parameters,
     * provided the parameters are valid. Month value has one
     * taken away from the users' input to fall in line with a
     * 0 start to months (i.e. 0 is January).
     *
     * @param year  The year which the task is due
     * @param month The month which the  task is due
     * @param date  The date which the task is due
     */

    public void setDueDate(int year, int month, int date) {
        if (testDateParameters(year, month, date)) {
            dueDate.set(Calendar.YEAR, year);
            dueDate.set(Calendar.MONTH, month - 1);
            dueDate.set(Calendar.DATE, date);
            System.out.println(">> Due date set");

        } else {
            System.out.println(">> Error, incorrect due date parameters.");
        }
    }


    /**
     * This tests if the user's calendar input is valid for the
     * task's due date. Users will only be able to set tasks due
     * from 1971 to 5000 (an arbitrary figure), because the date
     * of a default/cleared Calendar object has a year of 1970.
     *
     * @param year  Test the year due date of the task
     * @param month Test the month due date of the task
     * @param date  Test the date of the due date of the task
     * @return Will return true, if parameters are valid
     */

    private boolean testDateParameters(int year, int month, int date) {
        boolean correct;

        if (year < 1971 || year > 5000) {
            correct = false;
        } else if (month < 1 || month > 12) {
            correct = false;
        } else if (date < 1 || date > 31) {
            correct = false;
        } else {
            correct = true;
        }

        return correct;

    }

    /**
     * A method to print out information on the dueDate field
     * related to a task. It works in tandem with the checkIfDefault
     * method to confirm if a dueDate is at its default state, or if
     * it has been set by the user. It will shift the month by 1 when
     * printed to the user, as the Calendar class has a field for
     * month which starts at 0. It will also add a 0 to the date/month
     * , if the date is less than 10, such that the String fits a
     * YYYY-MM-DD format.
     *
     * @return A String based on the dueDate of a task
     */

    public String printDateString() {

        String dateString;
        String monthString;
        String result;

        if (checkIfDefaultDate()) {

            result = "Not set";


        } else {

            int date = dueDate.get(Calendar.DATE);

            if (date < 10) {
                dateString = "0" + date;
            } else {
                dateString = date + "";
            }

            int month = dueDate.get(Calendar.MONTH) + 1;

            if (month < 10){
                monthString = "0" + month ;
            }

            else {
                monthString = month + "";
            }

            result = dueDate.get(Calendar.YEAR) + "-" +
                            monthString + "-" + dateString;

        }

        return result;
    }

    /**
     * A method which checks if the dueDate field is at its
     * default beginning state, or if it has been set by the
     * user. The default date of a Calendar object is 1st Jan
     * 1970. Users can only set due dates after this date.
     *
     * @return Is the due date default?
     */

    public boolean checkIfDefaultDate() {
        boolean result = false;

        Calendar empty = Calendar.getInstance();
        empty.clear();


        if (dueDate.equals(empty)) {

            result = true;

        }

        return result;
    }

    public void handleLoopYears()
    {

    }

    public void handleShortMonths()
    {
        
    }
}