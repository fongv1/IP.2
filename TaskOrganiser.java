import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Task Organiser is a simple application which allows users to manage
 * their tasks. It can filter by certain fields associated to the task,
 * for example by due date. This is all done through a text based user
 * interface. Finally, the task organiser allows the end user to save
 * and load their tasks to use at different times.
 *
 * The TaskOrganiser class provides the user with functionality to
 * manipulate the task objects using a collection. A TaskOrganiser
 * object will receive commands from the user through the interface to
 * make the requisite actions
 */

public class TaskOrganiser implements Serializable
{
    private ArrayList<Task> tasks;
    private ArrayList<Task> finishedTasks;

    public TaskOrganiser() {
        tasks = new ArrayList<>();
        finishedTasks = new ArrayList<>();
    }

    /**
     * Add a new task to the collection.
     *
     * @param taskTitle The description of your task
     */

    public void addTask(String taskTitle)
    {
        Task t = new Task(taskTitle);
        tasks.add(t);
        System.out.println(">> Task added");
    }

    /**
     * @return Returns the arraylist of tasks added, in the
     * order they were added
     */

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * This will print out a brief description of each task,
     * using the toString method if there have been tasks
     * added.
     *
     */
    public void printAllTasks()
    {
            if (tasks.isEmpty())
            {
                System.out.println(">> There are no tasks to show.");
            }

            else {
                for (Task item : tasks) {
                    System.out.println(item.toString());
                }
            }
    }

    /**
     * This method will sort the tasks by their due date from
     * task which is due first, to task which is due last (if
     * they have a due date set).
     *
     */

    public void orderByDate()
    {
        try
        {
            ArrayList<Task> orderByDate = new ArrayList<>(tasks);
            Collections.sort(orderByDate, new DateComparator());

            for (Task ordered : orderByDate) {
                System.out.println(ordered.toString());
            }

            if (orderByDate.isEmpty())
            {
                System.out.println(">> No tasks to show");
            }

        }

        catch (NullPointerException e)
        {
            System.out.println(">> Due date values may be incorrect");
        }
    }


    /**
     * This will filter tasks by the project they are related to
     *
     * @param projectSearch The project name you want to filter by
     * @return An ArrayList of Tasks associated to the filtered
     * project
     */

    public ArrayList<Task> filterByProject(String projectSearch)
    {
        ArrayList<Task> filterByProject = new ArrayList<>();

            for (Task items : tasks) {
                if (items.getProject().equals(projectSearch)) {
                    filterByProject.add(items);
                }
            }

            if (filterByProject.isEmpty())
            {
                System.out.println("--------------------------------------------------------------------------");
                System.out.println(">> No tasks related to this project");
            }

            else {

                for (int i = 0; i < filterByProject.size(); i++)
                {
                    Task search = filterByProject.get(i);

                    System.out.println(search);
                }
            }

        return filterByProject;
    }

    /**
     * This method allows users to mark the task as done,
     * which will remove it from the current collection of tasks.
     * It also checks whether the task ID exists, by calling the
     * findTask method in this class. The result of findTask
     * method will always be -1 if the task does not exist.
     *
     * @param taskId The task ID associated to the task you
     *               would like to mark as done and remove
     */

    public void markAsDone(int taskId)
    {
        ArrayList<Task> finishedTasks = new ArrayList<>();
        int noResult = -1;

        if (findTask(taskId) != noResult)
        {
            int index = findTask(taskId);

            tasks.get(index).changeStatus();

            System.out.println(">> Task is now done");
        }

    }


    /**
     * This method allows the user to link a task to a particular
     * project. It also checks whether the task ID exists, by
     * calling the findTask method in this class. The result of
     * findTask method will always be -1 if the task does not exist.
     *
     * @param taskId The task and ID used to link with a project
     * @param projectName The name of the project
     */

    public void updateProject(int taskId, String projectName)
    {
        int noResult = -1;

        if (findTask (taskId) != noResult)
        {
            int index = findTask(taskId);
            tasks.get(index).setProject(projectName);
            System.out.println(">> Project updated");
        }
    }

    /**
     * This will remove a specific task from the orgnaniser,
     * according to the task ID. It also checks if the task
     * exists by calling the checkValidId method.
     *
     * @param taskId The task ID related to the task, that
     *               you would like to remove
     */
    public void removeTask(int taskId)
    {
        if (checkValidId(taskId)) {

            boolean found = false;
            Iterator<Task> it = tasks.iterator();

            while (it.hasNext() && !found) {
                Task search = it.next();

                if (search.getTaskId() == taskId) {
                    finishedTasks.add(search);
                    it.remove();
                    found = true;
                    System.out.println(">> Task successfully removed");
                }
            }
        }

        else
        {
            System.out.println(">> There are no tasks under this ID");
        }
    }

    /**
     * A method to check if the parameters entered for a task ID
     * exist in the task organiser
     *
     * @param taskId Check this Task ID if it is valid
     * @return Does this task ID exist?
     */

    public boolean checkValidId(int taskId)
    {
        boolean found = false;
        Iterator<Task> it = tasks.iterator();

        while (it.hasNext() && !found)
        {
            Task search = it.next();

            if (search.getTaskId() == taskId)
            {
                found = true;
            }

            else
            {
                found = false;
            }
        }

        return found;
    }

    /**
     * This method finds a task in the organiser according
     * to the Task ID. It returns the position of the task
     * in the Array List and helps other functions in this
     * class perform their tasks. If the task does not
     * exist it returns -1, which is used by other methods
     * in this class.
     *
     * @param taskId The Task ID you are searching for
     * @return The position of the task in the Array List
     */

    public int findTask (int taskId)
    {
        if (checkValidId(taskId))
        {
            boolean found = false;
            int index = 0;

            while (!found && index < tasks.size())
            {
                Task search = tasks.get(index);

                if (search.getTaskId() == taskId)
                {
                    found = true;
                }

                else
                {
                    index ++;
                }
            }

            return index;
        }

        else
        {
            System.out.println(">> No task under this ID");

            int noResult = -1;

            return noResult;
        }

    }

    /**
     * This method allows you to update the due date of a task
     * according to its ID. It also checks whether the task ID exists, by
     * calling the findTask method in this class. The result of
     * findTask method will always be -1 if the task does not exist.
     *
     * @param taskId The Task ID of the task to be updated
     * @param dueYear The year the task is due
     * @param dueMonth The month the task is due
     * @param dueDate The date the task is due
     */

    public void setDueDate(int taskId, int dueYear, int dueMonth, int dueDate)
    {
        int noResult = -1;

        if (findTask(taskId) != noResult)
        {
            int index = findTask(taskId);
            Task result = tasks.get(index);

            tasks.get(index).setDueDate(dueYear, dueMonth, dueDate);

            if (!result.getDate().checkIfDefaultDate())
            {
                System.out.println(">> Due date set");
            }
        }

    }

    /**
     * A method used to count how many tasks in the organiser
     * are in progress, this is displayed in the welcome
     * message.
     *
     * @return A count of tasks in progress.
     */

    public int countToDo()
    {
        int result = 0;

        for (Task toDo : tasks) {
            if (toDo.getStatus().equals("In Progress")) {
                result += 1;
            }
        }

        return result;
    }

    /**
     * A method used to count how many tasks in the organiser
     * are finished, this is displayed in the welcome message.
     *
     * @return A count of tasks that are finished.
     */

    public int countDone()
    {
        int result = 0;

        for (Task done : tasks)
        {
            if (done.getStatus().equals("Finished"))
            {
                result += 1;
            }
        }

        return result;
    }

    /**
     * This method allows you to change the title of a task.
     * It also checks whether the task ID exists, by
     * calling the findTask method in this class. The result of
     * findTask method will always be -1 if the task does not exist.
     *
     * @param taskId The Task ID of the task to be changed
     * @param changes The new Task Title
     */
    public void changeTaskTitle (int taskId, String changes)
    {
        int noResult = -1;

        if (findTask(taskId) != noResult)
        {
            int index = findTask(taskId);
            Task result = tasks.get(index);

            result.changeTaskTitle(changes);

            System.out.println(">> Task title changed");
        }
    }

    /**
     * Allows user to save their tasks to a text file which they created
     * to the project's directory.
     *
     * @param object The taskOrganiser object you would like saved
     */

    public static void saveFile (Object object)
    {
        try
        {
            File file = new File ("sda");

            ObjectOutputStream writer =
                    new ObjectOutputStream(new BufferedOutputStream
                            (new FileOutputStream("sda")));

            writer.writeObject(object);
            writer.close();

            System.out.println(">> File Saved");
        }

        catch (IOException e)
        {
            System.out.println(">> Error with file input/output");
        }
    }

    /**
     * A method which reads a text file from the project
     * directory, saved with existing tasks from the file. It
     * should be used in tandem with the loadFile method.
     *
     * @param fileName The file name you would like to unpack
     * @return An object saved from previous use of the app
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static Object unpackFile (String fileName) throws IOException, ClassNotFoundException, InvalidClassException
    {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);

        Object object = ois.readObject();
        ois.close();
        return object;
    }

    /**
     * This prepares the Task Organiser with its tasks from
     * a previous state and readies it for further manipulation
     * or querying. The file loaded, must be saved from the
     * same directory as the project
     *
     * @param fileName The file name you would like to unpack
     * @return A Task Organiser from the particular save file
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static TaskOrganiser loadFile(String fileName) throws IOException, ClassNotFoundException
    {
        unpackFile(fileName);

        return (TaskOrganiser) unpackFile(fileName);
    }

}
