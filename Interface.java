import java.io.IOException;
import java.io.Reader;
import java.util.InputMismatchException;

/**
 * Task Organiser is a simple application which allows users to manage
 * their tasks. It can filter by certain fields associated to the task,
 * for example by due date. This is all done through a text based user
 * interface. Finally, the task organiser allows the end user to save
 * and load their tasks to use at different times.
 *
 * The Interface class is the main kick off point for the application.
 * Taking values its parser object from the Parser class, it interprets
 * information from the user into commands. These commands are used on
 * the taskOrganiser object to manipulate tasks. This class also has
 * all the interface menu options to allow the user to logically
 * move through the system.
 */

public class Interface {

    /**
     * The command field is regularly updated in this class, and is
     * used to action commmands from the user. This is done by
     * collecting information from the parser object.
     */

    private Parser parser;
    private TaskOrganiser taskOrganiser;
    private String command;
    private int invalidInputCount = 0;

    /**
     * The constructor will use the loadFile method to stage the
     * previous state of the taskOrganiser object for further
     * manipulation.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Interface() throws IOException, ClassNotFoundException
    {
        parser = new Parser();
        command = "";
        taskOrganiser = new TaskOrganiser();
        loadFile();
        printWelcome();
    }

    /**
     * A welcome message, which acts as the home page for
     * the application.
     */

    public void printWelcome() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(">> Welcome to Task Organiser" + "\n" +
                           ">> You have " + taskOrganiser.countToDo() + " tasks to do and "
                               + taskOrganiser.countDone() + " tasks are done!" + "\n" +
                           ">> Pick an option:" + "\n" +
                           ">> (1) Add New Task" + "\n" +
                           ">> (2) Show Task List" + "\n" +
                           ">> (3) Edit Task" + "\n" +
                           ">> (4) Save and Quit ");
        System.out.println("--------------------------------------------------------------------------");

    }

    /**
     * The kickoff point of the application, this uses a loop
     * to keep the application running. It will action commands
     * by interating with the processInput method, which also
     * contains a scenario where the user can quit the system.
     */

    public void start() {
        boolean finished = false;

        while (!finished) {

            command = parser.getInput();
            invalidInputHelp();
            finished = processInPut(command);
        }

    }

    /**
     * This is the first branch from the home page, each case
     * will branch further into functions related to it. The
     * method will return true, which will stop the program
     * through the start method.
     *
     * @param command Stores a command from the user
     * @return False if the program is to continue running,
     * true, if the program is to stop.
     *
     */

    public boolean processInPut (String command) {

        boolean finished = false;

        switch (command) {
            case "1": {
                optionOne();
                break;
            }

            case "2": {
                optionTwo();
                break;
            }

            case "3": {
                optionThree();
                break;
            }

            case "4": {
                optionFour();
                System.out.println(">> Goodbye");
                System.out.println("--------------------------------------------------------------------------");
                finished = true;
                break;
            }

            default:
                System.out.println(">> Invalid input");
                invalidInputCount += 1;

        }
        return finished;
    }

    /**
     * The First branch from option one on the home page:
     * (1) Add New Task
     *
     */

    public void optionOne()
    {
        System.out.println("--------------------------------------------------------------------------");

        System.out.println(">> Enter name of Task:");

        String name = command = parser.getInput();

        System.out.println("--------------------------------------------------------------------------");

        taskOrganiser.addTask(name);

        resetInterface();

    }

    /**
     * The second branch from option two on the home page:
     * (2) Show Task List
     *
     * It allows the user to further specify their needs
     * by creating more branches.
     */

    public void optionTwo()
    {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(">> (a) show all tasks" + "\n" +
                           ">> (b) ordered by date" + "\n" +
                           ">> (c) filtered by project" + "\n" +
                           ">> (d) return to main menu");
        System.out.println("--------------------------------------------------------------------------");

        command = parser.getInput();

        System.out.println("--------------------------------------------------------------------------");


        switch (command)
        {
            case "a":
            {
                taskOrganiser.printAllTasks();
                resetInterface();
                break;
            }

            case "b":
            {
                taskOrganiser.orderByDate();
                resetInterface();
                break;
            }

            case "c":
            {
                System.out.println(">> Enter project you would like to filter by:");

                command = parser.getInput();
                System.out.println("--------------------------------------------------------------------------");

                taskOrganiser.filterByProject(command);
                resetInterface();
                break;
            }

            case "d":
            {
                resetInterface();
                break;
            }

            default:
            {
                System.out.println(">> Invalid input");
                invalidInputCount += 1;
                resetInterface();
                break;
            }
        }
    }

    /**
     * The third branch from option two on the home page:
     * (3) Edit Task
     *
     * It allows the user to further specify their needs
     * by creating more branches.
     */

    public void optionThree()
    {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(">> (a) add due date" + "\n" +
                           ">> (b) add project" + "\n" +
                           ">> (c) mark as done" + "\n" +
                           ">> (d) edit task name" + "\n" +
                           ">> (e) remove" + "\n" +
                           ">> (f) return to main menu");
        System.out.println("--------------------------------------------------------------------------");

        command = parser.getInput();

        System.out.println("--------------------------------------------------------------------------");

        switch (command)
        {
            case "a": {
                int taskId;
                int dueYear;
                int dueMonth;
                int dueDate;

                taskId = chooseTaskFromList();

                int noResult = -1;

                if (taskOrganiser.findTask(taskId) != noResult) {

                    System.out.println("--------------------------------------------------------------------------");


                    System.out.println(">> Due dates to tasks are set as YYYY - MM - DD" + "\n" +
                            ">> Please enter the year the task is due:");
                    try {
                        dueYear = parser.convertToInt();


                        System.out.println("--------------------------------------------------------------------------");


                        System.out.println(">> Please enter the month the task is due:");

                        dueMonth = parser.convertToInt();


                        System.out.println("--------------------------------------------------------------------------");

                        System.out.println(">> Please enter the date the task is due:");

                        dueDate = parser.convertToInt();


                        taskOrganiser.setDueDate(taskId, dueYear, dueMonth, dueDate);
                    }

                    catch (NumberFormatException e) {
                        System.out.println(">> Please enter integers");
                        resetInterface();
                    }
                }
            }

            case "b":
            {

                try {
                    int taskId = chooseTaskFromList();

                    System.out.println("--------------------------------------------------------------------------");

                    System.out.println(">> Please enter the name of the project you would like" +
                                       " to add:");

                    command = parser.getInput();
                    System.out.println("--------------------------------------------------------------------------");


                    taskOrganiser.updateProject(taskId, command);
                    resetInterface();
                    break;
                }

                catch (NumberFormatException e){
                    System.out.println(">> Please enter an integer");
                    System.out.println("--------------------------------------------------------------------------");
                    resetInterface();
                    break;
                }

            }

            case "c":
            {
                if (taskOrganiser.getTasks().isEmpty())
                {
                    System.out.println(">> No Tasks to update");
                    resetInterface();
                    break;
                }

                else
                {
                    int taskId = chooseTaskFromList();
                    System.out.println("--------------------------------------------------------------------------");
                    taskOrganiser.markAsDone(taskId);
                    resetInterface();
                    break;
                }
            }

            case "d":
            {


                try {

                    int taskId = chooseTaskFromList();
                    System.out.println("--------------------------------------------------------------------------");


                    System.out.println(">> Enter your new task name:");

                    command = parser.getInput();
                    System.out.println("--------------------------------------------------------------------------");


                    taskOrganiser.changeTaskTitle(taskId, command);
                }

                catch (NumberFormatException e)
                {
                    System.out.println(">> Please enter an integer");
                    System.out.println("--------------------------------------------------------------------------");
                    resetInterface();
                    break;
                }
            }

            case "e": {

                taskOrganiser.printAllTasks();
                System.out.println("--------------------------------------------------------------------------");
                System.out.println(">> Please choose which Task ID of the task you " +
                                   "would like to remove");

                try {
                    int remove = parser.convertToInt();
                    System.out.println("--------------------------------------------------------------------------");

                    taskOrganiser.removeTask(remove);
                    resetInterface();
                    break;
                }

                catch (NumberFormatException e){
                    System.out.println(">> Please enter an integer");
                    System.out.println("--------------------------------------------------------------------------");
                    resetInterface();
                    break;
                }
            }

            case "f":
            {
                resetInterface();
                break;
            }

            default:
            {
                System.out.println(">> Invalid input");
                invalidInputCount += 1;
                resetInterface();
                break;
            }
        }
    }

    /**
     * The fourth branch from option four on the home page:
     * (4) Save and Quit
     *
     * This will load the task organiser object from a file
     * in the same directory as this project
     */

    public void optionFour()
    {
        taskOrganiser.saveFile(taskOrganiser);
    }

    /**
     * This is used to load the previous state of the taskOrganiser
     * object and is done automatically in this class's constructor
     * every time the application is run.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public void loadFile() throws IOException, ClassNotFoundException
    {
        taskOrganiser = TaskOrganiser.loadFile("sda");
    }

    /**
     * A method which is reused by several functions to help the user
     * pick a task from the organiser for further manipulation.
     *
     * @return The task Id of the task for manipulation
     */

    public int chooseTaskFromList()
    {
        System.out.println(">> Please choose which Task ID of the task you would like" +
                           " to update:");

        System.out.println("--------------------------------------------------------------------------");
        taskOrganiser.printAllTasks();
        System.out.println("--------------------------------------------------------------------------");

        return parser.convertToInt();

    }

    /**
     * A method to check the count on how many times the user has
     * entered an unexpected value in the program and print text
     * to help them navigate the program.
     */

    public void invalidInputHelp()
    {
        if (invalidInputCount % 3 == 2)
        {
            System.out.println("--------------------------------------------------------------------------");
            System.out.println(">> Try to only type expected values in the program. " + "\n" +
                               ">> For example, to add a new task press: 1. " + "\n" +
                               ">> Then press enter.");
            resetInterface();

        }
    }

    /**
     * A method to reset the interface, commonly used when an
     * unexpected value/action has occurred or an exception has
     * been caught.
     */

    public void resetInterface()
    {
        printWelcome();
        parser.getInput();
    }
}


