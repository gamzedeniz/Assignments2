import java.io.IOException;
import java.util.ArrayList;

public class Officer extends Personnel
{
    @Override
    public void create(ArrayList<String> personnel, int size,ArrayList<String> weeks) throws IOException {
        int new_size;
        Officer [] officers = new Officer [size];
        for (int i=0;i<size;i++)
            officers[i]= new Officer();  // create each actual Person
        new_size=super.fillInfo(personnel,officers,"O"); //filling information about the person
        if (new_size >0) { //if such person exist
            this.overWorkSalary(weeks, new_size, officers); //calculating over work pay and adding base salary, ssbenefits
            super.writeInfo(officers, new_size); //writing info out into a file
        }

    }
    public void overWorkSalary(ArrayList<String> week,int newSize, Officer [] officer)
    {
        for(int j=0;j<newSize;j++) {
            for(String lines : week) {
                if(lines.split("\\t")[0].equals(officer[j].regNum))
                {
                    officer[j].totalSalary+=2600+(2600*65/100.0); //adding base salary and ssbenefits
                    for(int m=1;m<5;m++) {
                        if(Integer.parseInt(lines.split("\\t")[m])-40>0)
                        {
                            if(Integer.parseInt(lines.split("\\t")[m])-40>=10)
                                officer[j].totalSalary+=10*20.0;
                            else
                                officer[j].totalSalary+=(Integer.parseInt(lines.split("\\t")[m])-40)*20.0;
                        }
                    }

                }
            }
        }
    }
}
