import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AdmissionDAO implements DAO{
    private final String file="admission.txt";

    @Override
    public Patient getByID(int ID) throws FileNotFoundException { //getting the patient by admission id
        for(Patient patient :this.getALL())
        {
            if(patient.getAdmissionId()==ID)
                return patient;
        }
        return null;
    }

    @Override
    public void deleteByID(int ID) throws IOException { //removing the patient
        ArrayList<Patient> patients=this.getALL();
        patients.removeIf(patient -> patient.getId() == ID); //removing that patient and updating the list
        FileWriter myWriterteam = new FileWriter(file);
        for(Patient patient : patients){ //writing onto patient.txt
            myWriterteam.write( String.valueOf(patient.getAdmissionId())+"\t"+String.valueOf(patient.getId())+"\n");
            for(String admission : patient.getAdmission())
                myWriterteam.write(admission+"\n");
        }
        myWriterteam.close();
    }
    public void create(String object) throws IOException { //creating new admission
        ArrayList<Patient> mylist = this.getALL();
        Patient p=(new Patient(Integer.parseInt(object.split("\\s+")[2]), Integer.parseInt(object.split("\\s+")[1])));
        mylist.add(p);
        FileWriter myWriterteam = new FileWriter(file,true);//writing onto patient.txt
        myWriterteam.write(String.valueOf(p.getAdmissionId()) + "\t" + p.getId() + "\n");
        myWriterteam.close();
    }
    @Override
    public void add(String object) throws IOException { //adding examinations to new admission
        ArrayList<Patient> patients = this.getALL();
        for (Patient patient : patients) {
            if (patient.getAdmissionId() == Integer.parseInt(object.split("\\s+")[1])) //find matching patient
            {
                String s = "";
                s = s.concat(object.split("\\s+")[2] + "\t");
                for (int j = 3; j < object.split("\\s+").length; j++) {
                    s = s.concat(object.split("\\s+")[j]+" ");
                }
                ArrayList<String> line1 =new ArrayList<String>();
                if(patient.getAdmission()==null)
                    patient.setAdmission(line1);
                patient.getAdmission().add(s);
                break;
            }
        }

        for (int i = 0; i < patients.size(); i++) //sorting it in ascending order
        {
            for (int j = i + 1; j < patients.size(); j++) {
                if (patients.get(i).getAdmissionId() > patients.get(j).getAdmissionId())
                    Collections.swap(patients, i, j);
            }
        }
        FileWriter myWriterteam = new FileWriter(file);
        for (Patient patient : patients) //writing onto admission.txt
        {   myWriterteam.write(String.valueOf(patient.getAdmissionId()) + "\t" + patient.getId() + "\n");
            for(String admission : patient.getAdmission())
                myWriterteam.write(admission+"\n");}
        myWriterteam.close();

    }

    @Override
    public ArrayList<Patient> getALL() throws FileNotFoundException { //before looking into input file getting every info in admissiontxt into patient arraylist
        File myfile = new File(file);
        Scanner filereader = new Scanner(myfile);
        ArrayList<Patient> mylist = new ArrayList<Patient>();
        ArrayList<String> mylist2;
        mylist2 = new ArrayList<String>();
        while (filereader.hasNextLine())
            mylist2.add(filereader.nextLine());
        filereader.close();
        int k=-1;
        for (String line : mylist2) //creating patient list
        {
            if (Character.isDigit(line.split("\\t")[0].charAt(0))) {
                k++;
                Patient patient=new Patient(Integer.parseInt(line.split("\\t")[1]), Integer.parseInt(line.split("\\t")[0]));
                mylist.add(patient);
            }
            else {
                ArrayList<String> line1 =new ArrayList<String>();
                if(mylist.get(k).getAdmission()==null)
                    mylist.get(k).setAdmission(line1);
                mylist.get(k).getAdmission().add(line);
            }

        }

        return mylist;
    }
}
