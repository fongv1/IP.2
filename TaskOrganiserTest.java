import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.After;
import org.junit.Before;


import org.junit.Test;

import java.io.IOException;

public class TaskOrganiserTest {

    private TaskOrganiser taskOrg;
    private TaskOrganiser emptyTaskOrg;

    @Before
    public void setup() {
        taskOrg = new TaskOrganiser();
        taskOrg.addTask("This");
        taskOrg.addTask("t.o.");
        taskOrg.addTask("has 3 tasks");

        emptyTaskOrg = new TaskOrganiser();
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

        boolean testOrderByDate = emptyTaskOrg.orderByDate();
        assertEquals("Return false if empty", false, testOrderByDate);

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


    /*This test will only pass if system is at its default state. This is
      because task organiser will save and load the same file named "sda".
     */

    @Test
    public void emptyLoadFileTest() throws IOException, ClassNotFoundException {
        boolean loadFileTest = TaskOrganiser.loadFile("sda").isEmpty();
        assertEquals("true as task organiser is empty", true, loadFileTest);
    }

    @Test
    public void addTaskTest(){

    }
}