import java.io.Reader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Task Organiser is a simple application which allows users to manage
 * their tasks. It can filter by certain fields associated to the task,
 * for example by due date. This is all done through a text based user
 * interface. Finally, the task organiser allows the end user to save
 * and load their tasks to use at different times.
 *
 * The Parser class reads user input and tries to interpret it as a
 * command to use Task Organiser's functions. The parser uses a simple
 * set of known numbers as controls to direct the application.
 */

public class Parser {

    private Scanner reader;
    private boolean errorFlag;

    public Parser()
    {
        reader = new Scanner(System.in);
        errorFlag = false;
    }

    /**
     * This takes the user input which should be an integer, which
     * in eventually would be used to allow the user to choose an
     * option from the interface
     *
     * @return The integer or option the user has input
     */
    public String getInput() {

        System.out.println(">> ");

        String input = reader.nextLine().trim().toLowerCase();

        return input;

    }

    public int getIntInput()
    {
        System.out.println(">> ");

        try
        {
            int input = reader.nextInt();
            return input;
        }

        catch (NumberFormatException e)
        {
            System.out.println("Please enter an integer");
            errorFlag = true;
            return -1;
        }

        catch (InputMismatchException e)
        {
            System.out.println("Please enter an integer");
            errorFlag = true;
            return -1;
        }
    }

    public Scanner getReader()
    {
        return reader;
    }

    public boolean getErrorFlag()
    {
        return errorFlag;
    }

    public void revertErrorFlag()
    {
        errorFlag = false;
    }

}
