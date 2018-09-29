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

public class TaskDate {

    private Date creationDate;
    private Date dueDate = null;

    public TaskDate(int year, int month, int date)
    {

        if(testDateParameters(year, month, date))
        {
            setDueDate(year, month, date);
            setCreationDate();
        }

        else
        {
            System.out.println("Error, please input a valid year, month or date");
        }


    }

    /**
     * @return Return the date of creation of the task
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @return Return the due date of the task
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Set the due date of a task relative to the parameters
     *
     * @param year The year which the task is due
     * @param month The month which the  task is due
     * @param date The date which the task is due
     */
    public void setDueDate(int year, int month, int date)
    {
        Calendar currentCalendar = Calendar.getInstance();
        //currentCalendar.clear();
        currentCalendar.set(Calendar.YEAR, year);
        currentCalendar.set(Calendar.MONTH, month - 1);
        currentCalendar.set(Calendar.DATE, date);
        dueDate = currentCalendar.getTime();
    }

    /**
     * Set the year, month and date to when the task was
     * initialised
     */
    public void setCreationDate()
    {
        Calendar now = Calendar.getInstance();
        creationDate = now.getTime();
    }

    /**
     * This tests if the user's calendar input is a valid for the
     * task's due date. Users will only be able to set tasks due
     * up till the year 5000.
     *
     * @param year Test the year due date of the task
     * @param month Test the month due date of the task
     * @param date Test the date of the due date of the task
     * @return Will return true, if parameters are valid
     */
    public boolean testDateParameters(int year, int month, int date)
    {
        boolean test;

        if (year < 1900 || year > 5000)
        {
            test = false;
        }

        else if (month < 1 || month > 12)
        {
            test = false;
        }

        else if (date < 1 || date > 31)
        {
            test = false;
        }

        else
        {
            test = true;
        }

        return test;
    }
}