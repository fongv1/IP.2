import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Task Organiser is a simple application which allows users to manage
 * their tasks. It can filter by certain fields associated to the task,
 * for example by due date. This is all done through a text based user
 * interface. Finally, the task organiser allows the end user to save
 * and load their tasks to use at different times.
 *
 * The task orgnaniser class provides the user with functionality to
 * filter and sort tasks to their requirement. It interacts with the
 * task objects and Task date objects in order to achieve this.
 */

public class TaskOrganiser implements Serializable
{
    private ArrayList<Task> tasks;
    private ArrayList<Task> orderByDate;
    private ArrayList<Task> finishedTasks;

    public TaskOrganiser() {
        tasks = new ArrayList<>();
        finishedTasks = new ArrayList<>();
    }

    /**
     * Add a new task to the collection.
     *
     * @param taskTitle The description of your task
     * @param dueDateYear The year the task is due
     * @param dueDateMonth The month the task is due
     * @param dueDateDay The date the task is due
     */
    public void addTask(String taskTitle, int dueDateYear, int dueDateMonth, int dueDateDay)
    {
        Task t = new Task(taskTitle, dueDateYear, dueDateMonth, dueDateDay);
        tasks.add(t);
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
     * using the toString method.
     */
    public void printAllTasks()
    {
            if (tasks.isEmpty())
            {
                System.out.println("There are no tasks added yet.");
            }

            else {
                for (Task item : tasks) {
                    System.out.println(item.toString());
                }
            }
    }

    /**
     * This method will sort the tasks by their due date from
     * task which is due first, to task which is due last.
     *
     * @return a List which is ordered by due date of a task
     */
    public List<Task> orderByDate()
    {
        try
        {
            orderByDate = new ArrayList<>();
            List<Task> orderByDate = new ArrayList<>(tasks);
            Collections.sort(orderByDate, new DateComparator());


            for (Task test : orderByDate) {
                System.out.println(test.toString());
            }
        }

        catch (NullPointerException e)
        {
            System.out.println("Due date input may be incorrect");
        }

        return orderByDate;
    }

    public void orderByDate1()
    {
        try
        {
            ArrayList<Task> orderByDate = new ArrayList<>(tasks);
            Collections.sort(orderByDate, new DateComparator());

            for (Task ordered : orderByDate) {
                System.out.println(ordered.toString());
            }

        }

        catch (NullPointerException e)
        {
            System.out.println("Due date values may be incorrect");
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
                System.out.println("No tasks related to this project");
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
     * which will remove it from the current collection of tasks
     *
     * @param taskId The task ID associated to the task you
     *               would like to mark as done and remove
     */

    public void markAsDone(int taskId)
    {
        ArrayList<Task> finishedTasks = new ArrayList<>();

        if (checkValidId(taskId)) {
            Iterator<Task> it = tasks.iterator();
            boolean found = false;

            while (it.hasNext() && !found) {
                Task search = it.next();

                if (search.getTaskId() == taskId) {
                    finishedTasks.add(search);
                    search.changeStatus();
                    found = true;
                    System.out.println("Task successfully changed");
                }
            }
        }

        else
        {
            System.out.println("No tasks under this ID");
        }

    }

    /**
     * This method allows the user to link a task to a particular
     * project.
     *
     * @param taskId The task and ID used to link with a project
     * @param projectName The name of the project
     */
    public void updateProject(int taskId, String projectName)
    {
        if (checkValidId(taskId)) {
            boolean searching = true;
            int index = 0;

            while (index < tasks.size() && searching) {
                Task search = tasks.get(index);

                if (search.getTaskId() == taskId) {
                    search.setProject(projectName);
                    searching = false;
                }

                else
                    {
                    index++;
                }
            }
        }

        else
        {
            System.out.println("No tasks under this ID");
        }
    }

    /**
     * This will remove a specific task from the orgnaniser,
     * according to the task ID
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
                    System.out.println("Task successfully removed");
                }
            }
        }

        else
        {
            System.out.println("There are no tasks under this ID");
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

    public static void saveFile (Object object, String fileName)
    {
        try
        {
            File file = new File (fileName);

            ObjectOutputStream writer =
                    new ObjectOutputStream(new BufferedOutputStream
                            (new FileOutputStream(fileName)));

            writer.writeObject(object);
            writer.close();
        }

        catch (IOException e)
        {
            System.out.println("Error with file input/output");
        }
    }

    public static Object unpackFile (String fileName) throws IOException, ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);

        Object object = ois.readObject();
        ois.close();
        return object;
    }

    public static TaskOrganiser loadFile(String fileName) throws IOException, ClassNotFoundException
    {
        return (TaskOrganiser) unpackFile(fileName);
    }

}
