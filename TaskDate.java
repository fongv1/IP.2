import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Task Organiser is a simple application which allows users to manage
 * their tasks. It can filter by certain fields associated to the task,
 * for example by due date. This is all done through a text based user
 * interface. Finally, the task organiser allows the end user to save
 * and load their tasks to use at different times.
 *
 * The TaskDate class models the due date and time specific details related
 * to a task.
 */

public class TaskDate implements Serializable {

    private Date dueDate;

    /**
     * A dueDate of a task will always start at a default state
     * of an instance of the Calendar class. This is done through
     * the constructor and the setEmptyDate method.
     */

    public TaskDate()
    {
        dueDate = setEmptyDate();
    }


    /**
     * @return Return the due date of the task
     */

    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Set the due date of a task relative to the parameters
     *
     * @param year  The year which the task is due
     * @param month The month which the  task is due
     * @param date  The date which the task is due
     */

    public void setDueDate(int year, int month, int date)
    {
        if (testDateParameters(year, month, date)) {
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.set(Calendar.YEAR, year);
            currentCalendar.set(Calendar.MONTH, month - 1);
            currentCalendar.set(Calendar.DATE, date);
            dueDate = currentCalendar.getTime();
            System.out.println(">> Due date set");
        }

        else
        {
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

    public boolean testDateParameters(int year, int month, int date) {
        boolean correct = false;

        if (year < 1900 || year > 5000) {
            correct = false;
        }

        else if (month < 1 || month > 12) {
            correct = false;
        }

        else if (date < 1 || date > 31) {
            correct = false;
        }

        else {
            correct = true;
        }

        return correct;

    }

    /**
     * A method to print out information on the dueDate field
     * related to a task. It works in tandem with the checkIfDefault
     * method to confirm if a dueDate is at its default state, or if
     * it has been set by the user.
     *
     * @return A String based on the dueDate of a task
     */

    public String printDateString()
    {
        if (checkIfDefaultDate())
        {
            String result = "Not set";

            return result;
        }

        else
        {
            String result = dueDate.toString();

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

    public boolean checkIfDefaultDate()
    {
        boolean result = false;

        Calendar empty = Calendar.getInstance();
        empty.clear();
        Date emptyTest = empty.getTime();

        if (dueDate.equals(emptyTest))
        {
            result = true;
            return result;
        }

        else
        {
            return result;
        }
    }

    /**
     * A method to set each new dueDate field of a task,
     * to the default state of an instance in the
     * Calendar class.
     *
     * @return A default state of the Calendar instance
     */
    public Date setEmptyDate()
    {
        Calendar empty = Calendar.getInstance();
        empty.clear();

        return empty.getTime();
    }
}