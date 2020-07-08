import java.io.IOException;
import java.util.ArrayList;

public class Chief extends FullTime{
    @Override
    public void create(ArrayList<String> personnel, int size, ArrayList<String> weeks) throws IOException {
        int new_size;
        Chief [] chiefs = new Chief [size];
        for (int i=0;i<size;i++)
            chiefs[i]= new Chief();  // create each actual Person
        new_size=super.fillInfo(personnel,chiefs,"C"); //filling information about the person
        if (new_size >0) { //if such person exist
            super.daysOfWork(chiefs,125); // calculating pay based on the days of work and wage
            super.overWork(weeks,new_size,chiefs,8,15); //calculating over work pay
            super.writeInfo(chiefs, new_size); //writing info out into a file
        }
    }
}
