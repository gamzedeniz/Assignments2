import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Personnel {
    public String name;
    public String regNum;
    public String position;
    public int startYear;
    public double totalSalary=0.00;

    public void create(ArrayList<String> personnel, int size,ArrayList<String> weeks) throws IOException {}

    public int fillInfo (ArrayList<String> list, Personnel[] person, String position) //finding matching positions and filling the info
    {
        int k =0;
        for (String line : list)
        {
            if(Character.toString(line.split("\\t")[1].charAt(0)).equals(position))
            {
                person[k].name=line.split("\\t")[0];
                person[k].regNum=line.split("\\t")[1];
                person[k].position=line.split("\\t")[2];
                person[k].startYear=Integer.parseInt(line.split("\\t")[3]);
                person[k].totalSalary+=(2020-person[k].startYear)*20*0.8;   //adding severance pay to total salary
                k++;
            }
        }
        return k; //returning total number of that type of personnel
    }

    public void writeInfo (Personnel[] personel,int size) throws IOException
    {
        for (int p=0;p<size;p++) //writing into that file
        {
            FileWriter myWriterteam = new FileWriter(personel[p].regNum+".txt");
            myWriterteam.write("Name : "+personel[p].name.split(" ")[0]+"\n"+"Surname : "+personel[p].name.split(" ")[1]+"\n"+"Registration Number : "+personel[p].regNum+"\n"+"Position : "+personel[p].position+"\n"+"Year of Start : "+String.valueOf(personel[p].startYear)+"\n"+"Total Salary : "+String.format(java.util.Locale.US,"%.2f", personel[p].totalSalary)+" TL");
            myWriterteam.close();
        }

    }

}
