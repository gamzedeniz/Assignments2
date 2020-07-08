import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class PatientDAO implements DAO{
    private final String file="patient.txt";

    @Override
    public Patient getByID(int ID) throws FileNotFoundException { //get a patient object by its patient id
        for(Patient patient :this.getALL()){
            if(patient.getId()==ID)
                return patient;
        }
        return null;
    }

    @Override
    public void deleteByID(int ID) throws IOException {
        ArrayList<Patient> patients=this.getALL();
        patients.removeIf(patient -> patient.getId() == ID); //removing that patient and updating the list
        FileWriter myWriterteam = new FileWriter(file);
        for(Patient patient : patients) //writing onto patient.txt
            myWriterteam.write(String.valueOf(patient.getId())+"\t"+patient.getName()+" "+patient.getSurname()+"\t"+patient.getPhone()+"\t"+patient.getAddress()+"\n");
        myWriterteam.close();
    }


    @Override
    public void add(String object) throws IOException { //adding a new patient to the list
        String address;
        address="Address: ";
        ArrayList<Patient> patients=this.getALL();
        for(int i=5;i<object.split("\\s+").length;i++)
            address=address.concat(object.split("\\s+")[i]);
        patients.add(new Patient(Integer.parseInt(object.split("\\s+")[1]),object.split("\\s+")[2],object.split("\\s+")[3],object.split("\\s+")[4],address));
        for (int i = 0; i < patients.size(); i++) //sorting it in ascending order
        {
            for (int j = i + 1; j < patients.size(); j++) {
                if (patients.get(i).getId() > patients.get(j).getId())
                    Collections.swap(patients, i, j);
            }
        }
        FileWriter myWriterteam2 = new FileWriter(file);
        for(Patient patient : patients) //writing onto patient.txt
            myWriterteam2.write(String.valueOf(patient.getId())+"\t"+patient.getName()+" "+patient.getSurname()+"\t"+patient.getPhone()+"\t"+patient.getAddress()+"\n");
        myWriterteam2.close();
    }

    @Override
    public ArrayList<Patient> getALL() throws FileNotFoundException { //reading from patient file storing it into an array list to store patients
        File myfile = new File(file);
        Scanner filereader = new Scanner(myfile);
        ArrayList<Patient> mylist = new ArrayList<Patient>();
        ArrayList<String> mylist2 = new ArrayList<String>();
        while (filereader.hasNextLine())
            mylist2.add(filereader.nextLine());
        filereader.close();
        for(String line : mylist2) //creating patient list
            mylist.add(new Patient(Integer.parseInt(line.split("\\t")[0]),line.split("\\t")[1].split("\\s+")[0],line.split("\\t")[1].split("\\s+")[1],line.split("\\t")[2],line.split("\\t")[3]));

        return mylist;
    }
}
