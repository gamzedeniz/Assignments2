import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Controller controller=new Controller(); //controller is the lead to start it all,it is in contact with both DAOs and examinations
        controller.ReadInput(args[0]);
    }
}
