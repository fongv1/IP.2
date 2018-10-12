import java.io.IOException;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        Calendar test = Calendar.getInstance();
        test.set(Calendar.MONTH, 4);
        System.out.println(test.get(Calendar.MONTH));

    }
}
