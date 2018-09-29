public class Main {

    public static void main(String[] args) {
        TaskOrganiser sda = new TaskOrganiser();

        Task t1 = new Task("Boil water", 2018, 12, 1);
        //t1.setDueDate(2018, 12, 23);
        //System.out.println(t1.getDate().getDueDate());
        //t1.getDate().printCreationDate();
        //t1.getDate().setCreationDate();
        Task t2 = new Task("make pasta", 2018, 11, 2);
        Task t3 = new Task("make sauce", 2018, 11, 01);
        Task t4 = new Task("make meatballs", 2019, 12, 2);
        Task t5 = new Task("serve", 2010, 2,20) ;
        sda.addTask(t1);
        sda.addTask(t2);
        sda.addTask(t3);
        sda.addTask(t4);
        sda.addTask(t5);
        t1.setProject("sda");
        t2.setProject("sda");
        t3.setProject("IP");
        t4.setProject("IP");
        t5.setProject("Sda");
        //sda.filterByProject("sda");
        //sda.printAllTasks();
        //sda.orderByDate();
        //t1.getDate().compareTo(t1.getDate().getDueDate());
    }
}
