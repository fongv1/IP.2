public class Main {

    public static void main(String[] args) {
        TaskOrganiser sda = new TaskOrganiser();

        //Task t1 = new Task("Boil water", 2018, 12, 1);
        //Task t2 = new Task("make pasta", 2018, 11, 2);
        //Task t3 = new Task("make sauce", 2018, 12, 01);
        //Task t4 = new Task("make meatballs", 2019, 12, 2);
        //Task t5 = new Task("serve", 2010, 2,20) ;

        sda.addTask("boil water", 2049, 12, 2);
        sda.addTask("make pasta ", 2049, 11, 2);
        sda.addTask("make sauce ", 2002, 11, 3);
        sda.addTask("cook pasta ", 2002, 11, 2);
        sda.addTask("make pokemon ", 2049, 1, 2);


        //sda.addTask(t3);
        //sda.addTask(t4);
        //sda.addTask(t5  );
        sda.updateProject(1, "sda");
        sda.updateProject(2, "sda");
        sda.updateProject(3, "lol");
        sda.updateProject(4, "sda");
        sda.updateProject(5, "lol");
        //sda.printAllTasks();
        //sda.removeTask(0);
        //sda.printAllTasks();
        //sda.markAsDone(8);
        sda.updateProject(2, "rofls");
        sda.removeTask(4);
        sda.printAllTasks();

        //t2.setProject("lol");

        //t3.setProject("sda");
        //t4.setProject("lol");
        //t5.setProject("lol");


        //sda.orderByDate();
        //sda.markAsDone(5);
        //sda.filterByProject("sda");

        //sda.printAllTasks();
    }
}
