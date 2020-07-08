import java.io.IOException;
import java.util.ArrayList;

public class PartTime extends Employee{
    @Override
    public void create(ArrayList<String> personnel, int size, ArrayList<String> weeks) throws IOException {
        int new_size;
        PartTime [] partTimers = new PartTime [size];
        for (int i=0;i<size;i++)
            partTimers[i]= new PartTime();  // create each actual Person
        new_size=super.fillInfo(personnel,partTimers,"P"); //filling information about the person
        if (new_size >0) { //if such person exist
            this.hourWork(weeks,new_size,partTimers);
            super.writeInfo(partTimers, new_size); //writing info out into a file
        }
    }
    public void hourWork(ArrayList<String> week,int newSize, PartTime [] partTimes) //calculating pay per week according to working hours
    {
        for(int j=0;j<newSize;j++) {
            for (String lines : week) {
                if (lines.split("\\t")[0].equals(partTimes[j].regNum)) {
                    for(int k=1;k<5;k++) {
                        if (Integer.parseInt(lines.split("\\t")[k]) >= 10) {
                            if (Integer.parseInt(lines.split("\\t")[k]) >= 20)
                                partTimes[j].totalSalary += 20.0 * 18;
                            else
                                partTimes[j].totalSalary += Integer.parseInt(lines.split("\\t")[k]) * 18.0;
                        }
                    }
                }
            }
        }
    }
}
