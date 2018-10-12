import java.io.Serializable;
import java.util.Comparator;

/**
 * Task Organiser is a simple application which allows users to manage
 * their tasks. Using a text based interface, it allows the user to
 * manipulate tasks individually or in a collection. The application
 * allows you to explicitly save and implicitly load the state of your
 * task organiser each time you use the system.
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
