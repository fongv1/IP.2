import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Interface sda = new Interface();
        //sda.start();

        TaskOrganiser a = new TaskOrganiser();
        a.addTask("lol");
        a.addTask("lols");
        a.addTask("laosdlf");

        a.getTask(0).getDate().setDueDate(2018, 1, 31);
        a.getTask(1).getDate().setDueDate(2018, 1, 2);
        a.getTask(2).getDate().setDueDate(2018, 1, 5);


        Calendar s = a.getTask(1).getDate().getDueDate1();
        System.out.println(s.get(Calendar.DATE));
        System.out.println(s.get(Calendar.MONTH));
        System.out.println(s.get(Calendar.YEAR));


        /*TaskOrganiser sda =  new TaskOrganiser();
        sda.addTask("lol");
        sda.addTask("wow");
        sda.addTask("afd");

        sda.updateProject(1, "ip");
        sda.updateProject(2, "gp");

        sda.printAllProjects1();*/

    }
}
