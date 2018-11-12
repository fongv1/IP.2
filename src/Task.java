import java.io.Serializable;

/**
 * Task Organiser is a simple application which allows users to manage
 * their tasks. Using a text based interface, it allows the user to
 * manipulate tasks individually or in a collection. The application
 * allows you to explicitly save and implicitly load the state of your
 * task organiser each time you use the system.
 *
 * A task represents the backbone of the application, it will contain
 * certain fields which you can interact with in the Task Organiser
 * class.
 */

public class Task implements Serializable
{
    private static final String[] statusOptions = {"In Progress", "Finished"};

    private int taskId;
    private String taskTitle;
    private String project = "";
    private TaskDate date;
    private String status;


    /**
     * Create a task object, which will automatically assign it an ID.
     * Using the methods in this class will allow you to update
     * the state of a task object.
     *
     * @param taskTitle What the task is.
     */

    public Task (String taskTitle, int taskId)
    {
        this.taskTitle = taskTitle;
        this.taskId = taskId;
        status = statusOptions[0];
        date = new TaskDate();
    }

    /**
     *
     * @return Returns the description of the task
     */

    public String getTaskTitle ()
    {
        return taskTitle;
    }

    /**
     *
     * @param changes Changes you want to make to the task title
     */

    public void changeTaskTitle (String changes)
    {
        taskTitle = changes;
    }

    /**
     *
     * @return Returns the task's ID
     */

    public int getTaskId()
    {
        return taskId;
    }

    /**
     *
     * @return Returns the project associated with the task.
     */

    public String getProject()
    {
        return project;
    }

    /**
     *
     * @param projectName The name of the project assigned to the task
     */

    public void setProject(String projectName)
    {
        project = projectName;
    }

    /**
     * This returns information on the task related to date and time.
     * Task objects is composed of a TaskDate object which helps set
     * a due date.
     *
     * @return Returns the date object related to the task
     */

    public TaskDate getDate()
    {
        return date;
    }

    /**
     *
     * @return A string representation of the Task object
     */

    public String toString()
    {
            String result = "Task id: " + taskId + "\n" +
                    "Task Title: " + taskTitle + "\n" +
                    "Project: " + project + "\n" +
                    "Due Date: " + date.printDateString() + "\n" +
                    "Status: " + getStatus() + "\n";

            return result;
    }

    /**
     * This changes the status of the task from in progress to
     * finished, which are the only two statuses stored in this
     * class.
     */

    public void changeStatus()
    {
        status = statusOptions[1];
    }

    /**
     *
     * @return Returns the status of the task.
     */

    public String getStatus()
    {
        return status;
    }
}

