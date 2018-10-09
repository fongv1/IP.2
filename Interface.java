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
                           ">> You have X tasks todo and Y tasks are done!" + "\n" +
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
                System.out.println("Goodbye");
                finished = true;
                break;
            }

            default:
                System.out.println("Invalid input");
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
        System.out.println(">> Enter name of Task:");

        String name = command = parser.getInput();

        System.out.println("--------------------------------------------------------------------------");

        taskOrganiser.addTask(name);

        printWelcome();

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
        System.out.println(">> (a) show all tasks" + "\n" +
                           ">> (b) ordered by date" + "\n" +
                           ">> (c) filtered by project" + "\n" +
                           ">> (d) return to main menu");

        command = parser.getInput();

        System.out.println("--------------------------------------------------------------------------");


        switch (command)
        {
            case "a":
            {
                taskOrganiser.printAllTasks();
                printWelcome();
                break;
            }

            case "b":
            {
                taskOrganiser.orderByDate();
                printWelcome();
                break;
            }

            case "c":
            {
                System.out.println(">> Enter project you would like to filter by:");

                command = parser.getInput();

                taskOrganiser.filterByProject(command);
                printWelcome();
                break;
            }

            case "d":
            {
                printWelcome();
                break;
            }

            default:
            {
                System.out.println("Invalid input");
                optionTwo();
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
        System.out.println(">> (a) add due date" + "\n" +
                           ">> (b) add project" + "\n" +
                           ">> (c) mark as done" + "\n" +
                           ">> (d) edit task name" + "\n" +
                           ">> (e) remove" + "\n" +
                           ">> (f) return to main menu");

        command = parser.getInput();

        System.out.println("--------------------------------------------------------------------------");

        switch (command)
        {
            case "a":
            {
                int taskId;
                int dueYear;
                int dueMonth;
                int dueDate;

                taskId = chooseTaskFromList();

                if (parser.getErrorFlag())
                {
                    printWelcome();
                    revertErrorFlag();
                    break;
                }

                if (taskOrganiser.findTask(taskId) != -1) {

                    System.out.println("--------------------------------------------------------------------------");


                    System.out.println(">> Due dates to tasks are set as YYYY - MM - DD" + "\n" +
                            ">> Please enter the year the task is due:");

                    dueYear = parser.getIntInput();
                    if (parser.getErrorFlag()) {
                        printWelcome();
                        revertErrorFlag();
                        break;
                    }

                    System.out.println("--------------------------------------------------------------------------");


                    System.out.println(">> Please enter the month the task is due:");

                    dueMonth = parser.getIntInput();
                    if (parser.getErrorFlag()) {
                        printWelcome();
                        revertErrorFlag();
                        break;
                    }

                    System.out.println("--------------------------------------------------------------------------");


                    System.out.println(">> Please enter the date the task is due:");

                    dueDate = parser.getIntInput();
                    if (parser.getErrorFlag()) {
                        printWelcome();
                        revertErrorFlag();
                        break;
                    }

                    taskOrganiser.setDueDate(taskId, dueYear, dueMonth, dueDate);
                    printWelcome();
                    break;
                }

                else
                {
                    printWelcome();
                    break;
                }

            }

            //!!!! ERROR WITH ADD PROJECT USING GETINTINPUT
            case "b":
            {
                System.out.println(">> Please choose which Task ID of the task you would like" +
                        " to update:");

                taskOrganiser.printAllTasks();
                int taskId = parser.getIntInput();


                System.out.println("--------------------------------------------------------------------------");

                System.out.println(">> Please enter the name of the project you would like" +
                                   " to add:");

                command = parser.getInput();

                taskOrganiser.updateProject(taskId, command);
                break;

            }

            case "c":
            {
                if (taskOrganiser.getTasks().isEmpty())
                {
                    System.out.println("No Tasks to mark");
                    printWelcome();
                    break;
                }

                else
                {
                    int taskId = chooseTaskFromList();
                    taskOrganiser.markAsDone(taskId);
                    printWelcome();
                    break;
                }
            }

            case "d":
            {

                taskOrganiser.printAllTasks();
                System.out.println(">> Please choose which Task ID of the task you " +
                        "would like to update");

                try {
                    int taskId = Integer.parseInt(parser.getInput());


                    System.out.println(">> Enter your new task name:");

                    command = parser.getInput();

                    taskOrganiser.changeTaskTitle(taskId, command);
                }

                catch (NumberFormatException e)
                {
                    System.out.println("Please enter an integer");
                    System.out.println("--------------------------------------------------------------------------");
                    optionThree();
                    break;
                }
            }

            case "e":
            {
                taskOrganiser.removeTask(chooseTaskFromList());
                break;
            }

            case "f":
            {
                printWelcome();
                break;
            }

            default:
            {
                System.out.println("Invalid input");
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

    public void loadFile() throws IOException, ClassNotFoundException
    {
        taskOrganiser = TaskOrganiser.loadFile("sda");
    }

    public int chooseTaskFromList()
    {
        int taskId;

        System.out.println(">> Please choose which Task ID of the task you would like" +
                " to update:");

        taskOrganiser.printAllTasks();

        return taskId = parser.getIntInput();

    }

    public void invalidInputHelp()
    {
        if (invalidInputCount % 4 == 3)
        {
            System.out.println("Try to only type values asked for in the program");
            printWelcome();
        }
    }

    public void revertErrorFlag()
    {
        parser.revertErrorFlag();
    }
}


