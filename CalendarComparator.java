import java.io.Serializable;
import java.util.Comparator;

/**
 * Task Organiser is a simple application which allows users to manage
 * their tasks. It can filter by certain fields associated to the task,
 * for example by due date. This is all done through a text based user
 * interface. Finally, the task organiser allows the end user to save
 * and load their tasks to use at different times.
 *
 * This interface allows the comparison of two due dates of a task, to
 * facilitate the sorting of a task (by due date).
 */
public class CalendarComparator implements Comparator, Serializable
{
    @Override
    public int compare (Object obj1, Object obj2)
    {
        Task t1 = (Task) obj1;
        Task t2 = (Task) obj2;

        return (t1.getDate().getDueDate().compareTo(t2.getDate().getDueDate()));

    }

}
