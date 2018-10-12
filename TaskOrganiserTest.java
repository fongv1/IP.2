import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


import org.junit.After;
import org.junit.Before;


import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class TaskOrganiserTest {

    private TaskOrganiser taskOrg;
    private TaskOrganiser emptyTaskOrg;
    private TaskOrganiser largerTo;

    @Before
    public void setup() {
        //set up small taskOrg
        taskOrg = new TaskOrganiser();
        taskOrg.addTask("This");
        taskOrg.addTask("t.o.");
        taskOrg.addTask("has 3 tasks");

        //set up empty taskOrg
        emptyTaskOrg = new TaskOrganiser();

        //set up large taskOrg
        largerTo = new TaskOrganiser();
        largerTo.addTask("make pasta");
        largerTo.addTask("boil water");
        largerTo.addTask("chop veggies");
        largerTo.addTask("boil broth");
        largerTo.addTask("call mum");
        largerTo.addTask("pick up da kids");
        largerTo.addTask("take a snapchat");

        largerTo.getTask(0).getDate().setDueDate(2000, 1, 1);
        largerTo.getTask(1).getDate().setDueDate(1999, 12, 31);
        largerTo.getTask(2).getDate().setDueDate(1998, 1, 1);
        largerTo.getTask(3).getDate().setDueDate(1999, 11, 1);
        largerTo.getTask(4).getDate().setDueDate(2001, 1, 5);
        //largerTo.getTask(5).getDate().setDueDate(2001, 1, 1);
        //largerTo.getTask(6).getDate().setDueDate(1998, 11, 1);

        largerTo.updateProject(0, "lol");
        largerTo.updateProject(1, "lol");
        largerTo.updateProject(2, "lol");
        largerTo.updateProject(3, "sda");
        largerTo.updateProject(5, "lol");
        largerTo.updateProject(4, "sda");
    }

    @Test
    public void emptyAddTaskTest() {

        int id = emptyTaskOrg.addTask("Hello");
        assertEquals("Task ID's should start at 1", 1, id);

        int id1 = emptyTaskOrg.addTask("Hello");
        assertEquals("Next ID should increment by 1", 2, id1);

    }

    @Test
    public void emptyPrintAllProjectsTest() {

        boolean testPrintAllTasks = emptyTaskOrg.printAllProjects();
        assertEquals("Return true if task organiser is empty", true, testPrintAllTasks);


    }


    @Test
    public void emptyOrderByDateTest(){

        ArrayList<Task> emptyOrderbyDate = emptyTaskOrg.orderByDate();
        assertEquals("true if empty task is empty", true, emptyTaskOrg.isEmpty() );

    }

    @Test
    public void emptyFilterByProjectTest(){

        boolean testFilterByProject = emptyTaskOrg.filterByProject("test").isEmpty();
        assertEquals("Return true if empty", true, testFilterByProject);

    }

    @Test
    public void emptyMarkAsDoneTest(){

        boolean testMarkAsDone = emptyTaskOrg.markAsDone(0);
        assertEquals("Return false if no tasks to mark as done", false, testMarkAsDone);

        boolean testMarkAsDone1 = emptyTaskOrg.markAsDone(-1);
        assertEquals("Return false as out of bounds", false, testMarkAsDone1);

        boolean testMarkAsDone2 = emptyTaskOrg.markAsDone(100);
        assertEquals("Return false as out of bounds", false, testMarkAsDone2);
    }

    @Test
    public void emptyUpdateProjectTest() {
        boolean testUpdateProject = emptyTaskOrg.updateProject(0, "test");
        assertEquals("Return false if empty", false, testUpdateProject);

        boolean testUpdateProject1 = emptyTaskOrg.updateProject(-1, "test");
        assertEquals("Return false if empty", false, testUpdateProject1);

        boolean testUpdateProject2 = emptyTaskOrg.updateProject(100, "test");
        assertEquals("Return false if empty", false, testUpdateProject2);
    }

    @Test
    public void emptyRemoveTaskTest () {
        boolean removeTaskTest = emptyTaskOrg.removeTask(0);
        assertEquals("Return false if empty", false, removeTaskTest);

        boolean removeTaskTest1 = emptyTaskOrg.removeTask(-1);
        assertEquals("Return false if empty", false, removeTaskTest1);

        boolean removeTaskTest2 = emptyTaskOrg.removeTask(100);
        assertEquals("Return false if empty", false, removeTaskTest2);
    }

    @Test
    public void emptyCheckValidIdTest() {
        boolean checkValidIdTest = emptyTaskOrg.checkValidId(0);
        assertEquals("Return false as no task found", false, checkValidIdTest);

        boolean checkValidIdTest1 = emptyTaskOrg.checkValidId(-1);
        assertEquals("Return false as no task found", false, checkValidIdTest1);

        boolean checkValidIdTest2 = emptyTaskOrg.checkValidId(100);
        assertEquals("Return false as no task found", false, checkValidIdTest2);
    }

    @Test
    public void emptyFindTaskIdTest(){
        int findTaskId = emptyTaskOrg.findTaskIndex(0);
        assertEquals("-1 Means no task found", -1, findTaskId);

        int findTaskId1 = emptyTaskOrg.findTaskIndex(-1);
        assertEquals("-1 Means no task found", -1, findTaskId1);

        int findTaskId2 = emptyTaskOrg.findTaskIndex(100);
        assertEquals("-1 Means no task found", -1, findTaskId2);
    }

    @Test
    public void emptyCountToDoTest(){
        int countToDo = emptyTaskOrg.countToDo();
        assertEquals("0 as no tasks", 0, countToDo);

    }

    @Test
    public void emptyCountDoneTest() {
        int countDone = emptyTaskOrg.countDone();
        assertEquals("0 as no tasks", 0, countDone);

    }

    @Test
    public void emptyChangeTaskTitleTest() {
        boolean changeTaskTitle = emptyTaskOrg.changeTaskTitle(0, "lol");
        assertEquals("false as no tasks", false, changeTaskTitle);

        boolean changeTaskTitle1 = emptyTaskOrg.changeTaskTitle(-1, "lol");
        assertEquals("false as no tasks", false, changeTaskTitle1);

        boolean changeTaskTitle2 = emptyTaskOrg.changeTaskTitle(100, "lol");
        assertEquals("false as no tasks", false, changeTaskTitle2);
    }

    @Test
    public void emptySaveFileTest(){
        assertEquals("true if file saved", true, TaskOrganiser.saveFile(emptyTaskOrg));
    }

    @Test
    public void emptyLoadFileTest() throws IOException, ClassNotFoundException {
        boolean loadFileTest = TaskOrganiser.loadFile("sda").isEmpty();
        assertEquals("true as task organiser is empty", true, loadFileTest);
    }

    @Test
    public void addTaskTest(){
        int id = taskOrg.addTask("lol");
        assertEquals("since there are 3 tasks, id should be 4", 4, id);

        int id1 = taskOrg.addTask("lols");
        assertEquals("since there are 4 tasks, this should be 5", 5, id1);
    }

    @Test
    public void printAllProjects() {
        boolean printAll = taskOrg.printAllProjects();
        assertEquals("true if taskOrg is not empty but no project set", true, printAll);

        taskOrg.getTask(0).setProject("lol");
        boolean printAll1 = taskOrg.printAllProjects();
        assertEquals("false if taskOrg has a task with project", false, printAll1);

    }

    /**
     * The test on taskOrg is a shorter test to prove that tasks will be sorted
     * correctly by date.
     *
     * The test on largerTaskOrg, tests the behaviour of a
     * sorted task list, where there are some tasks with no due date set and
     * others with a due date. The default date or cleared Calendar object
     * which tasks start off with, have a date of Jan 1st 1970 and users
     * are only permitted to set due dates after the default date.
     *
     * Therefore, a sorted task list by date, will have the tasks with no
     * due date come first. OrderByDate method adds extra logic,
     * so the interface displays these tasks (with no due date), last and
     * sorts the remaining tasks by due date.
     *
     * The largerTaskOrg ArrayList has a size of 7, with the last two tasks
     * with no due date. The expected behaviour would be for the first
     * two in this collection (index 0 and 1) to have a default due or
     * cleared due date.
     *
     * N.B. The checkIfDefaultDate method returns true, if the due date
     * is at its default state.
     */
    @Test
    public void orderByDate(){

        //Testing small taskOrg, all tasks have a due date

        // this should be the last task in a sorted list
        Task t1 = taskOrg.getTask(0);
        t1.getDate().setDueDate(2018, 1, 1);

        //this should be the second task in a sorted list
        Task t2 = taskOrg.getTask(1);
        t2.getDate().setDueDate(2017,12,31);

        //this should be the first task that falls due (1999)
        Task t3 = taskOrg.getTask(2);
        t3.getDate().setDueDate(1999, 1,1);


        ArrayList<Task> sorted = taskOrg.orderByDate();
        assertEquals("task due in 1999 should be index 0 in sorted list", t3, sorted.get(0));
        assertEquals("task due in 2017 should be index 1 in sorted list", t2, sorted.get(1));
        assertEquals("task due in 2018 should be index 2 in sorted list", t1, sorted.get(2));



        ArrayList<Task> sortedLarge = largerTo.orderByDate();
        Boolean lastTask = sortedLarge.get(0).getDate().checkIfDefaultDate();
        Boolean penultimateTask = sortedLarge.get(1).getDate().checkIfDefaultDate();

        assertEquals("last task should have default date", true, lastTask);
        assertEquals("penultimate task should have default date", true, penultimateTask);

    }
}