/**
 * Task Organiser is a simple application which allows users to manage
 * their tasks. It can filter by certain fields associated to the task,
 * for example by due date. This is all done through a text based user
 * interface. Finally, the task organiser allows the end user to save
 * and load their tasks to use at different times.
 *
 * A task represents the backbone of the application, it will contain
 * certain fields which you can interact with in the Task Organiser
 * class.
 */

public class Task
{
    private static int nextId = 0;
    private static String[] statusOptions = {"In Progress", "Finished"};

    private int taskId;
    private String taskTitle;
    private String project = null;
    private TaskDate date;
    private String status;


    /**
     * Create a task object, which will automatically assign it an ID.
     * You are also able to assign a project to this task manually.
     * The constructor will also set up the next ID for the next object
     * created.
     * @param taskTitle What the task is.
     */
    public Task (String taskTitle, int dueDateYear, int dueDateMonth, int dueDateDay)
    {
        this.taskTitle = taskTitle;
        taskId = nextId + 1;
        nextId += 1;
        setDueDate(dueDateYear, dueDateMonth, dueDateDay);
        status = statusOptions[0];
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
    public void setTaskId(String changes)
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
     *
     * @return Returns the date object related to the task
     *
     */
    public TaskDate getDate()
    {
        return date;
    }

    /**
     * This method allows the user to define when a task is due,
     * relative to the parameters.
     * @param dueYear The year a task is due
     * @param dueMonth The month a task is due
     * @param dueDate The date a task is due
     */
    public void setDueDate (int dueYear, int dueMonth, int dueDate)
    {

        date = new TaskDate(dueYear, dueMonth, dueDate);
    }


    public String toString()
    {

        String result =     "Task id: " + taskId + "\n" +
                "Task title: " + taskTitle + "\n" +
                "Project: " + project + "\n" +
                "Due date: " + getDate().getDueDate() + "\n";



        return result;
    }

    /**
     * A task will start at "In Progress" at creation, calling this
     * method will complete the task, changing the status to "Finished"
     */

    public void taskFinsihed()
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

