import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.Serializable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;

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
    private int nextId = 0;


    public TaskOrganiser() {
        tasks = new ArrayList<>();
        finishedTasks = new ArrayList<>();
    }

    /**
     * This method creates a task object from the Task class
     * and stores the object in the taskOrganiser ArrayList.
     * The nextId field helps assign each task a unique ID
     * each time this method is called.
     *
     * @param taskTitle The description of the task.
     * @return returns the TaskID, mainly used to validate
     * unit testing class.
     */

    public int addTask(String taskTitle)
    {
        Task t = new Task(taskTitle, nextId + 1);
        tasks.add(t);
        nextId += 1;
        System.out.println(">> Task added");
        return t.getTaskId();
    }

    /**
     * A method that returns a task
     * @param index The index of the task to be returned
     * @return A task object
     */

    public Task getTask(int index)
    {
        return tasks.get(index);
    }

    /**
     * An empty ArrayList will affect what is displayed
     * on the interface and will commonly stop functions
     * to continue, considering it is empty.
     *
     * @return A boolean to check if ArrayList is empty
     */

    public boolean isEmpty()
    {
        return tasks.isEmpty();
    }

    /**
     * This will print out a brief description of each task,
     * using the toString method if there have been tasks
     * added.
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
     * This method aims to sort a task by its due date in an ascending
     * order. It will not display anything to the user if no tasks have
     * been added, or if tasks have been added and all of them do not
     * have a due date.
     *
     * Task organisers that have some tasks with due dates and others
     * not, will be sorted and displayed to the user through the interface.
     * Users are only allowed to set due dates after 1st of Jan 1970.
     *
     * However, because a default date (a task with no due date set) will
     * have a Calendar objects cleared state (1st Jan 1970), a sorted
     * task organiser will order these tasks first, then tasks which
     * have their due date set by the user.
     *
     * In these scenarios this method will add extra logic to display
     * tasks in the correct order by date.
     *
     * @return A sorted list of tasks by date.
     */

    public ArrayList<Task> orderByDate() {
        ArrayList<Task> orderByDate = new ArrayList<>(tasks);

        int testIfNoDueDates = 0;

        for (Task tests : orderByDate)
        {
            if (tests.getDate().checkIfDefaultDate())
            {
                testIfNoDueDates += 1;
            }
        }

        if (testIfNoDueDates == orderByDate.size())
        {
            System.out.println(">> No due dates set for all tasks. Can not sort tasks");
        }

        else if (orderByDate.isEmpty())
        {
            System.out.println(">> No tasks to order");
        }

        else
            {
            try
            {
                orderByDate.sort(new CalendarComparator());

                for (Task hasDueDates : orderByDate)
                {
                    if (!hasDueDates.getDate().checkIfDefaultDate())
                    {
                        System.out.println(hasDueDates.toString());
                    }
                }

                for (Task noDueDates : orderByDate)
                {
                    if (noDueDates.getDate().checkIfDefaultDate())
                    {
                        System.out.println(noDueDates);
                    }
                }
            } catch (NullPointerException e)

            {
                System.out.println(">> Due date values may be incorrect");
            }
        }

        return orderByDate;
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

                for (Task filtered : filterByProject)
                {
                    System.out.println(filtered);
                }
            }

        return filterByProject;
    }

    /**
     * This method allows users to mark the task as done,
     * which will remove it from the current collection of tasks.
     * It also checks whether the task ID exists, by calling the
     * findTask method in this class. The result of findTask
     * will always be -1 if the task does not exist.
     *
     * @param taskId The task ID related to task to be updated.
     * @return True if task marked as done successfully
     */

    public boolean markAsDone(int taskId)
    {
        boolean functionSuccessful = false;

        int noResult = -1;

        if (findTaskIndex(taskId) != noResult)
        {
            int index = findTaskIndex(taskId);

            tasks.get(index).changeStatus();

            System.out.println(">> Task is now done");

            functionSuccessful = true;
        }

        else
        {
            functionSuccessful = false;
        }

        return functionSuccessful;
    }

    /**
     * This method allows the user to link a task to a particular
     * project. It also checks whether the task ID exists, by
     * comparing the noResult local variable through the
     * findTaskIndex method. No result will always equal -1
     * if taskId is invalid (from the findTaskIndex method).
     *
     * @param taskId The task ID of the task to update.
     * @param projectName The name of the project to update.
     * @return True if updating project was successful.
     */

    public boolean updateProject(int taskId, String projectName)
    {
        boolean functionSuccessful = false;

        int noResult = -1;

        if (findTaskIndex(taskId) != noResult)
        {
            int index = findTaskIndex(taskId);
            tasks.get(index).setProject(projectName);
            System.out.println(">> Project updated");

            functionSuccessful = true;
        }

        else
        {
            functionSuccessful = false;
        }

        return functionSuccessful;
    }

    /**
     * This will remove a specific task from the organiser,
     * according to the task ID. It also check if the task
     * exists by calling the checkValidId method.
     *
     * @param taskId The task ID related to the task, that
     * you would like to remove.
     * @return True if task removed.
     */

    public boolean removeTask(int taskId)
    {
        boolean functionSuccessful = false;

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

                    functionSuccessful = true;
                }
            }
        }

        else
        {
            System.out.println(">> There are no tasks under this ID");
        }

        return functionSuccessful;
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

    public int findTaskIndex (int taskId)
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
     * A method to print all projects if they have been set.
     * This uses a HashSet to enforce one project to be printed.
     * The boolean return value is used, to print the correct
     * information to the user in the interface class.
     *
     * @return Are there no projects set to the tasks
     */

    public boolean printAllProjects()
    {
        boolean noProjects = true;
        HashSet<String> uniqueProjects = new HashSet<String>();

        for (Task task : tasks)
        {
            if (!task.getProject().equals(""))
            {
                uniqueProjects.add(task.getProject());
                noProjects = false;
            }
        }

        if (uniqueProjects.isEmpty())
        {
            System.out.println(">> No projects to filter by");
        }

        else
        {
            for (String projects : uniqueProjects)
            {
                System.out.println(">> " + projects);
            }
        }

        return noProjects;
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
     * @return True if task title changed.
     */
    public boolean changeTaskTitle (int taskId, String changes)
    {
        boolean functionSuccessful = false;

        int noResult = -1;

        if (findTaskIndex(taskId) != noResult)
        {
            int index = findTaskIndex(taskId);
            Task result = tasks.get(index);

            result.changeTaskTitle(changes);

            System.out.println(">> Task title changed");

            functionSuccessful = true;
        }

        return functionSuccessful;
    }

    /**
     * Allows user to save their tasks and the state of their
     * tasks through an object stream. There is only one file
     * saved and loaded for the application.
     *
     * @param object The taskOrganiser object you would like saved.
     * @return True if the taskOrganiser object saved correctly.
     */

    public static boolean saveFile (Object object)
    {
        boolean successful = false;

        try
        {
            File file = new File ("sda");

            ObjectOutputStream writer =
                    new ObjectOutputStream(new BufferedOutputStream
                            (new FileOutputStream("sda")));

            writer.writeObject(object);
            writer.close();

            System.out.println(">> File Saved");
            successful = true;
        }

        catch (IOException e)
        {
            System.out.println(">> Error with file input/output");
            successful = false;
        }

        return successful;
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

    public static Object unpackFile (String fileName) throws IOException, ClassNotFoundException
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
