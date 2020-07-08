import java.io.IOException;
import java.util.ArrayList;

public class Worker extends FullTime{
    @Override
    public void create(ArrayList<String> personnel, int size, ArrayList<String> weeks) throws IOException {
        int new_size;
        Worker [] workers = new Worker [size];
        for (int i=0;i<size;i++)
            workers[i]= new Worker();  // create each actual Person
        new_size=super.fillInfo(personnel,workers,"W"); //filling information about the person
        if (new_size >0) { //if such person exist
            super.daysOfWork(workers,105); // calculating pay based on the days of work and wage
            super.overWork(weeks,new_size,workers,10,11); //calculating over work pay
            super.writeInfo(workers, new_size); //writing info out into a file
        }
    }


}