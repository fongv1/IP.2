import java.io.Reader;
import java.util.InputMismatchException;

public class Interface {

    private Parser parser;
    private TaskOrganiser taskOrganiser;
    private String command;

    public Interface() {
        parser = new Parser();
        command = "";
        taskOrganiser = new TaskOrganiser();
        printWelcome();
    }

    public void printWelcome() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(">> Welcome to Task Organiser" + "\n" +
                           ">> You have X tasks todo and Y tasks are done!" + "\n" +
                           ">> Pick an option:" + "\n" +
                           ">> (1) Add New Task" + "\n" +
                           ">> (2) Show Task List" + "\n" +
                           ">> (3) Edit Task" + "\n" +
                           ">> (4) Save and Quit");
        System.out.println("--------------------------------------------------------------------------");

    }

    public void start() {
        boolean finished = false;

        while (!finished) {

            command = parser.getInput();
            finished = processInPut(command);
        }

    }

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
                System.out.println("Bye");
                finished = true;
                break;
            }

            default:
                System.out.println("Invalid input");

        }
        return finished;
    }


    public void optionOne()
    {
        System.out.println(">> Enter name of Task:");

        String name = command = parser.getInput();

        System.out.println("--------------------------------------------------------------------------");

        taskOrganiser.addTask(name);

        printWelcome();

    }

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

    public int chooseTaskFromList()
    {
        int taskId;

        System.out.println(">> Please choose which Task ID of the task you would like" +
                " to update:");

        taskOrganiser.printAllTasks();

        return taskId = parser.getIntInput();

    }

    public void revertErrorFlag()
    {
        parser.revertErrorFlag();
    }
}


