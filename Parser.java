import java.util.Scanner;

/**
 * Task Organiser is a simple application which allows users to manage
 * their tasks. Using a text based interface, it allows the user to
 * manipulate tasks individually or in a collection. The application
 * allows you to explicitly save and implicitly load the state of your
 * task organiser each time you use the system.
 *
 * The Parser class reads user input and converts it into information
 * passed on to the Interface.
 */

public class Parser {

    private Scanner reader;

    public Parser()
    {
        reader = new Scanner(System.in);
    }

    /**
     * This takes user input and converts it to a String. This
     * is used in tandem with the Interface class and task
     * organiser class to match user input with menu options,
     * or to store values to mutate or manipulate tasks.
     *
     * @return A String of input from the user.
     */

    public String getInput()
    {

        System.out.println(">> ");

        String input = reader.nextLine().trim().toLowerCase();

        return input;

    }

    /**
     * This takes user input and converts it to an integer. This
     * is used in tandem with the Interface class and task
     * organiser class to match user input with menu options,
     * or to store values to mutate or manipulate tasks.
     *
     * @return An integer the user has typed in the interface.
     */

    public int convertToInt()
    {
        System.out.println(">> ");

            String input = reader.nextLine().trim();

            int result = Integer.parseInt(input);

            return result;
    }

}
