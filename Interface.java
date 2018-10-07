import java.io.Reader;
import java.util.InputMismatchException;

public class Interface {

    private Parser parser;
    private TaskOrganiser taskOrganiser;
    private String[] userOptions = {"1a", "1b", "2", "3", "4"};

    public Interface() {
        printWelcome();
        parser = new Parser();
    }

    public void printWelcome() {
        System.out.println(">> Welcome to Task Organiser" + "\n" +
                           ">> You have X tasks todo and Y tasks are done!" + "\n" +
                           ">> Pick an option:" + "\n" +
                           ">> (1) Show Task List" + "\n" +
                           "\t" + " (a) by date" + "\n" +
                           "\t" + " (b) by project" + "\n" +
                           ">> (2) Add New Task" + "\n" +
                           ">> (3) Edit Task (update, mark as done, remove)" + "\n" +
                           ">> (4) Save and Quit");
    }

    public void start() {
        boolean finished = false;

        while (!finished) {

            String command = parser.getInput();
            finished = processInPut(command);
        }

    }

    public boolean processInPut (String command) {

        boolean finished = false;

        switch (command) {
            case "1a": {
                System.out.println("1a");
                break;
            }

            case "1b":
            {
                System.out.println("1b");
                break;
            }

            case "2": {
                optionTwo();
                break;
            }

            case "3": {
                System.out.println("test 3");
                break;
            }

            case "4": {
                System.out.println("Bye");
                finished = true;
                break;
            }

            default:
                System.out.println("Invalid input");

        }
        return finished;
    }

    public void optionTwo()
    {
        System.out.println(">> Input needed for new task" + "\n" +
                           ">> Enter Task Name and Due Date in the format below:" + "\n" +
                           ">> [Enter Task], [YYYY], [MM], [DD]");

    }
}


