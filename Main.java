import java.io.IOException;
import java.io.*;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TaskOrganiser sda = new TaskOrganiser();
        sda.addTask("lol");
        sda.addTask("12");
        sda.addTask("dicks");
        //sda.printAllTasks();
        sda.addTask("lolls");
        sda.setDueDate(2, 2401, 3,4);
        sda.setDueDate(1, 2020, 4, 2);
        sda.setDueDate(3, 2002, 4,2);
        sda.updateProject(4, "sda");
        sda.markAsDone(4);
        sda.filterByProject("sda");
        sda.removeTask(4);
        sda.filterByProject("sda");
        sda.printAllTasks();
        System.out.println("-------");
        sda.addTask("this should be 4");
        sda.printAllTasks();
    }
}
