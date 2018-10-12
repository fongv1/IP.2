import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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

    private static ArrayList<Integer> monthsWithThirtyDays;
    private Calendar dueDate = Calendar.getInstance();
    private ArrayList<Integer> leapYears;


    /**
     * A dueDate of a task will always start at a default state
     * of an instance of the Calendar class. This is so we can
     * compare dates that haven't been set, to ones that have.
     */

    public TaskDate() {
        dueDate.clear();
        populateLeapYears();
        populateThirtyDayMonths();
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
     * 0 start to months (i.e. 0 is January). It will check
     * whether the parameters are valid through testFullDate
     * method before setting the values.
     *
     * @param year  The year which the task is due
     * @param month The month which the  task is due
     * @param date  The date which the task is due
     */

    public void setDueDate(int year, int month, int date) {
        if (testFullDate(year, month - 1, date)) {
            dueDate.set(Calendar.YEAR, year);
            dueDate.set(Calendar.MONTH, month - 1);
            dueDate.set(Calendar.DATE, date);
            System.out.println(">> Due date set");

        } else {
            System.out.println(">> Error, incorrect due date parameters.");
        }
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


    /**
     * This tests if the user's calendar input is valid for the
     * task's due date. Users will only be able to set tasks due
     * from 2000 to 2100 (an arbitrary figure). It only tests the
     * boundaries of a user's due date values. It is used together
     * with fullDateTest method.
     *
     * @param year  Test the year due date of the task
     * @param month Test the month due date of the task
     * @param date  Test the date of the due date of the task
     * @return Will return true, if parameters are valid
     */

    private boolean testBorders (int year, int month, int date) {
        boolean correct;

        if (year < 2000 || year > 2100) {
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
     * This method is the comprehensive test for a users's input for a
     * due date. It will cover leap years/ordinary years, days of the
     * month in February and the rest of months of a year. It works in
     * conjunction with a number of other methods int his class.
     *
     * @param year Test the year due date of the task
     * @param month Test the month due date of the task
     * @param date Test the date of the due date of the task
     * @return Will return true, if parameters are valid
     */
    public boolean testFullDate(int year, int month, int date) {
        boolean correct = false;
        int february = 1;


        if (testBorders(year, month, date)) {

            if (leapYears.contains(year)) {

                if (month == february) {
                    correct = handlesLeapFebruary(year, date);
                }

                else {
                    correct = handlesMonthsAndDays(month, date);
                }
            }

            // Not a leap year
            else {

                if (month == february) {
                    correct = handlesFebruary(year, date);
                }

                else {
                    correct = handlesMonthsAndDays(month, date);
                }
            }
        }

        // Due dates are not within limits of a due date
        else {
            System.out.println(">> Years can only be between 2000 and 2100 (inclusive)");
            System.out.println(">> Months can only be between 1 and 12 (inclusive)");
            System.out.println(">> Dates can only be between 1 and 31 (inclusive)");
        }

        return correct;
    }

    /**
     * This method handles if the date a user has given is correct
     * to the amount of days in a normal 28 day February. It is
     * used as a part of the testFullDate method.
     *
     * @param year Test the year due date of the task
     * @param date Test the date of the due date of the task
     * @return Will return true, if parameters are valid
     */

    public boolean handlesFebruary (int year, int date) {
        boolean correct = false;

            if (date <= 28) {
                correct = true;
            }

            else {
                System.out.println(">> " + year + " is a leap year, February can only have 28 days");
            }

        return correct;
    }

    /**
     * This method handles if the date a user has given is correct
     * to the amount of days in a leap year 29 day February. It is
     * used as a part of the testFullDate method.
     *
     * @param year Test the year due date of the task
     * @param date Test the date of the due date of the task
     * @return Will return true, if parameters are valid
     */

    public boolean handlesLeapFebruary (int year, int date) {
        boolean correct = false;

        if (date <= 29) {
            correct = true;
        }

        else {
            System.out.println(">> " + year + " is a leap year, February can only have 29 days");
        }

        return correct;
    }

    /**
     * This method handles if the date a user has given is correct
     * to the amount of days in the months of a year (excluding
     * February). It is used as a part of the testFullDate method.
     *
     * @param month Test the month due date of the task
     * @param date Test the date of the due date of the task
     * @return Will return true, if parameters are valid
     */

    public boolean handlesMonthsAndDays (int month, int date)
        {
            boolean correct = false;
            Calendar tempCal = Calendar.getInstance();

            if (monthsWithThirtyDays.contains(month)) {

                if (date < 31) {
                    correct = true;
                }

                else {
                    tempCal.set(Calendar.MONTH, month);

                    System.out.println(">> There are only 30 days in " +
                            tempCal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
                }
            }
            // Month contains 31 days
            else {

                if (date <= 31) {
                    correct = true;
                }

                else {
                    tempCal.set(Calendar.MONTH, month);

                    System.out.println(">> There are only 31 days in " +
                            tempCal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
                }
            }
            return correct;
        }

    /**
     * A method to populate an ArrayList of the leap years
     * within the bounds of acceptable due date years. Some
     * methods in this class will check this collection to
     * perform their function.
     */

    public void populateLeapYears ()
        {
            leapYears = new ArrayList<>();

            for (int i = 2000; i < 2100; i += 4) {
                leapYears.add(i);
            }
        }

    /**
     * A method to populate an ArrayList of the months with
     * thirty days in them. Some methods in this class will
     * check this collection to perform their function.
     */

    public void populateThirtyDayMonths ()
        {
            monthsWithThirtyDays = new ArrayList<>();
            monthsWithThirtyDays.add(3);
            monthsWithThirtyDays.add(5);
            monthsWithThirtyDays.add(8);
            monthsWithThirtyDays.add(10);
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
        }

        else {

            int date = dueDate.get(Calendar.DATE);

            if (date < 10) {
                dateString = "0" + date;
            }

            else {
                dateString = date + "";
            }

            int month = dueDate.get(Calendar.MONTH) + 1;

            if (month < 10) {
                monthString = "0" + month;
            }

            else {
                monthString = month + "";
            }

            result = dueDate.get(Calendar.YEAR) + "-" +
                    monthString + "-" + dateString;

        }

        return result;
    }

}
