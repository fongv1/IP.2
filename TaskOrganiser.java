import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
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

public class TaskOrganiser {
    private ArrayList<Task> tasks;
    private ArrayList<Task> orderByDate;
    private ArrayList<Task> filterByProject;


    public TaskOrganiser() {
        tasks = new ArrayList<>();
    }

    /**
     * This method will add your task object to the organiser
     *
     * @param task The task you would like to add
     */
    public void addTask(Task task) {
        tasks.add(task);
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

    /**
     * This will filter tasks by the project they are related to
     *
     * @param projectSearch The project name you want to filter by
     * @return An ArrayList of Tasks associated to the filtered
     * project
     */
    public ArrayList<Task> filterByProject(String projectSearch) {
        filterByProject = new ArrayList<>();

        try {

            for (Task items : tasks) {
                if (items.getProject().equals(projectSearch)) {
                    filterByProject.add(items);
                }
            }

            for (Task a : filterByProject) {
                System.out.println(a);
            }

        }

        catch (NullPointerException e)
        {
            System.out.println("No tasks under this project");
        }

        return filterByProject;
    }

    /**
     * A task will start at "In Progress" at creation, calling this
     * method will complete the task, changing the status to "Finished"
     *
     * @param taskId The Task Id you want to search for to mark as
     *               finshed
     */

    public void markAsDone(int taskId)
    {
        boolean searching = true;
        int index = 0;

        while (index < tasks.size() && searching)
        {
            Task search = tasks.get(index);

            if (search.getTaskId() == taskId)
            {
                search.changeStatus();
                searching = false;
            }

            else
            {
                index++;
            }
        }
    }

}
