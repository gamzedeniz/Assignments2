import java.io.IOException;
import java.util.ArrayList;

public class FacultyMember extends Academician{
    @Override
    public void create(ArrayList<String> personnel, int size, ArrayList<String> weeks) throws IOException {
        int new_size;
        FacultyMember [] faculty = new FacultyMember [size];
        for (int i=0;i<size;i++)
            faculty[i]= new FacultyMember();  // create each actual Person
        new_size=super.fillInfo(personnel,faculty,"F"); //filling information about the person
        if (new_size >0) { //if such person exist
            super.totalPay(faculty,135); //for base salary and ssbenefits
            this.addCourseFee(weeks,new_size,faculty);
            super.writeInfo(faculty, new_size); //writing info out into a file
        }

    }
    public void addCourseFee (ArrayList<String> week,int newSize, FacultyMember [] facultyMembers) //calculating additional course fee if there is any
    {
        for(int j=0;j<newSize;j++) {
            for (String lines : week) {
                if (lines.split("\\t")[0].equals(facultyMembers[j].regNum)) {
                    for(int m=1;m<5;m++) {
                        if (Integer.parseInt(lines.split("\\t")[m]) -40> 0)
                        {       if(Integer.parseInt(lines.split("\\t")[m])-40>=8)
                                    facultyMembers[j].totalSalary+=8*20.0;
                                else
                                    facultyMembers[j].totalSalary+=(Integer.parseInt(lines.split("\\t")[m])-40)*20.0;

                        }
                    }
                }
            }
        }
    }
}
