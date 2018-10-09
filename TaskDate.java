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
 * The date class models the due date and time specific details related
 * to a task.
 */

public class TaskDate implements Serializable {

    private Date creationDate;
    private Date dueDate;

    public TaskDate()
    {
        dueDate = setEmptyDate();
    }


    /**
     * @return Return the date of creation of the task
     */
    public Date getCreationDate() {
        return creationDate;
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
        }

        else
        {
            System.out.println("Error, incorrect due date parameters.");
        }
    }

    /**
     * Set the year, month and date to when the task was
     * initialised
     */
    public void setCreationDate() {
        Calendar now = Calendar.getInstance();
        creationDate = now.getTime();
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
        boolean test;

        if (year < 1900 || year > 5000) {
            test = false;
        } else if (month < 1 || month > 12) {
            test = false;
        } else if (date < 1 || date > 31) {
            test = false;
        } else {
            test = true;
        }

        return test;
    }

    public String printDateString()
    {
        if (checkIfDefaultDate())
        {
            String result = "Due date not set";

            return result;
        }

        else
        {
            String result = dueDate.toString();

            return result;
        }

    }

    public Date setEmptyDate()
    {
        Calendar empty = Calendar.getInstance();
        empty.clear();

        return empty.getTime();
    }

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
}