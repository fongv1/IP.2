import java.io.Serializable;
import java.util.Calendar;

/**
 * Task Organiser is a simple application which allows users to manage
 * their tasks. It can filter by certain fields associated to the task,
 * for example by due date. This is all done through a text based user
 * interface. Finally, the task organiser allows the end user to save
 * and load their tasks to use at different times.
 *
 * The TaskDate class models the due date of a task.
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
     * taken away from the users input to fall in line with a
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
     * This tests if the user's calendar input is a valid for the
     * task's due date. Users will only be able to set tasks due
     * up till the year 5000.
     *
     * @param year  Test the year due date of the task
     * @param month Test the month due date of the task
     * @param date  Test the date of the due date of the task
     * @return Will return true, if parameters are valid
     */

    private boolean testDateParameters(int year, int month, int date) {
        boolean correct;

        if (year < 1900 || year > 5000) {
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
     * month which starts at 0. It will also add a 0 to the date, if
     * the date is less than 10, such that the String fits a
     * YYYY-MM-DD format.
     *
     * @return A String based on the dueDate of a task
     */

    public String printDateString() {

        String dateString;
        String monthString;

        if (checkIfDefaultDate()) {

            String result = "Not set";

            return result;

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

            String result = dueDate.get(Calendar.YEAR) + "-" +
                            monthString + "-" + dateString;

            return result;
        }

    }

    /**
     * A method which checks if the dueDate field is at its
     * default beginning state, or if it has been set by the
     * user.
     *
     * @return Is the due date default?
     */

    public boolean checkIfDefaultDate() {
        boolean result = false;

        Calendar empty = Calendar.getInstance();
        empty.clear();


        if (dueDate.equals(empty)) {

            return result = true;

        } else {
            return result;
        }
    }
}