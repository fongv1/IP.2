import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Interface sda = new Interface();
        sda.start();

        /*TaskOrganiser sda = new TaskOrganiser();
        sda.addTask("lol");
        sda.addTask("lol1");
        TaskOrganiser.saveFile(sda);
        TaskOrganiser t =TaskOrganiser.loadFile("sda");
        t.addTask("no1");*/
    }
}
