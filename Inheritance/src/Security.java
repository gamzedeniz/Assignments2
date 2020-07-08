import java.io.IOException;
import java.util.ArrayList;

public class Security extends Personnel {
    @Override
    public void create(ArrayList<String> personnel, int size, ArrayList<String> weeks) throws IOException {
        int new_size;
        Security [] securities = new Security [size];
        for (int i=0;i<size;i++)
            securities[i]= new Security();  // create each actual Person
        new_size=super.fillInfo(personnel,securities,"S"); //filling information about the person
        if (new_size >0) { //if such person exist
            this.hourofWork(weeks, new_size, securities);
            super.writeInfo(securities, new_size); //writing info out into a file
        }
    }

    public void hourofWork(ArrayList<String> week,int newSize,Security [] security)  //calculating pay based on working hours
    {
        for(int j=0;j<newSize;j++) {
            for(String lines : week) {
                if(lines.split("\\t")[0].equals(security[j].regNum))
                {
                    for(int m=1;m<5;m++) {
                        if(Integer.parseInt(lines.split("\\t")[m])>=30)
                        {
                            security[j].totalSalary+=(6*15); //transportation, food money
                            if(Integer.parseInt(lines.split("\\t")[m])>=54) //working hour money
                                security[j].totalSalary+=54.0*10;
                            else
                                security[j].totalSalary+=Integer.parseInt(lines.split("\\t")[m])*10.0;
                        }
                    }
                }
            }
        }
    }
}
