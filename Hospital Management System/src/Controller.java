import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Controller {

    public DAO dao;

    public void ReadInput(String file) throws IOException { //reading input file to do whats asked of us
        File myfile = new File(file);
        Scanner filereader = new Scanner(myfile);
        FileWriter myWriterteam1 = new FileWriter("output.txt");//creating the output file
        myWriterteam1.write("");
        myWriterteam1.close();
        FileWriter myWriterteam = new FileWriter("output.txt", true); //appending to output file
        ArrayList<String> mylist2;
        mylist2 = new ArrayList<String>();
        while (filereader.hasNextLine())
            mylist2.add(filereader.nextLine());
        filereader.close();
        for (String line : mylist2){

            if(line.split("\\s+")[0].equals("AddPatient")) //adding patient and writing onto output file
            {
                PatientDAO patientDAO=new PatientDAO();
                patientDAO.add(line);
                myWriterteam.write("Patient"+"\t"+line.split("\\s+")[1]+"\t"+line.split("\\s+")[2]+"\t"+"added"+"\n");
            }
            else if(line.split("\\s+")[0].equals("RemovePatient")) //removing patient and writing onto output file
            {
                PatientDAO patientDAO=new PatientDAO();
                AdmissionDAO admissionDAO=new AdmissionDAO();
                myWriterteam.write("Patient"+"\t"+line.split("\\s+")[1]+"\t"+patientDAO.getByID(Integer.parseInt(line.split("\\s+")[1])).getName()+"\t"+"removed"+"\n");
                patientDAO.deleteByID(Integer.parseInt(line.split("\\s+")[1]));
                admissionDAO.deleteByID(Integer.parseInt(line.split("\\s+")[1]));
            }
            else if(line.split("\\s+")[0].equals("CreateAdmission")){ //creating admission and wiritn onto output file
                AdmissionDAO admissionDAO=new AdmissionDAO();
                admissionDAO.create(line);
                myWriterteam.write("Admission"+"\t"+line.split("\\s+")[1]+" created\n");
            }
            else if(line.split("\\s+")[0].equals("AddExamination")) //adding exmaination to the patient and wiritn onto output file
            {
                AdmissionDAO admissionDAO=new AdmissionDAO();
                admissionDAO.add(line);
                myWriterteam.write(line.split("\\s+")[2]+" examination added to admission "+line.split("\\s+")[1]+"\n");
            }
            else if(line.split("\\s+")[0].equals("TotalCost")) //calculation total cost and writing onto output file
            {
                int total=0;
                ArrayList<Integer> cost = new ArrayList<Integer>();
                AdmissionDAO admissionDAO=new AdmissionDAO();
                ManagementSystem inPatientExamination=new InPatientExamination();
                ManagementSystem outPatientExamination= new OutPatientExamination();
                myWriterteam.write("TotalCost for admission "+line.split("\\s+")[1]+"\n");
                this.calculateCost(cost,admissionDAO.getByID(Integer.parseInt(line.split("\\s+")[1])),myWriterteam); //to get cost of each examination and writing
                for(int costs : cost) //for total cost finding the sum of its members
                    total+=costs;
                myWriterteam.write("\t"+"Total: "+String.valueOf(total)+"$"+"\n"); //for total cost
            }
            else if(line.split("\\s+")[0].equals("ListPatients")){ //listing patients
                PatientDAO patientDAO=new PatientDAO();
                ArrayList<Patient> patients  = patientDAO.getALL();//Sort all employees by first name
                patients.sort(Comparator.comparing(Patient::getName));
                myWriterteam.write("Patient List:\n");
                for(Patient patient : patients) //writing onto output.txt
                    myWriterteam.write(String.valueOf(patient.getId())+"\t"+patient.getName()+" "+patient.getSurname()+"\t"+patient.getPhone()+"\t"+patient.getAddress()+"\n");
            }
        }
        myWriterteam.close();
    }

    public void calculateCost(ArrayList<Integer> total,Patient admissions,FileWriter myWriterteam) throws IOException { //calculate cost
        for(String line: admissions.getAdmission())
        {
            //check which kind of examination it is
            ManagementSystem examine;
            if(line.split("\\s+")[0].equals("Inpatient")){
                examine = new InPatientExamination();
            }
            else{
                examine= new OutPatientExamination();}
            //using decorator pattern to calculate cost by adding necessary operations to examinations
            if(line.contains("measurements")) {
                    if ((line.contains("tests")) && (line.contains("doctorvisit")) && (line.contains("imaging"))) {
                        examine = examine.addOperation(new Imaging(new DoctorVisit(new Test(new Measurement(examine)))));
                    } else if ((line.contains("tests")) && (line.contains("doctorvisit"))) {
                        examine = examine.addOperation(new DoctorVisit(new Test(new Measurement(examine))));
                    } else if (line.contains("imaging") && (line.contains("doctorvisit"))) {
                        examine = examine.addOperation(new DoctorVisit(new Imaging(new Measurement(examine))));
                    } else if (line.contains("tests")) {
                        examine = examine.addOperation(new Test(new Measurement(examine)));
                    } else if (line.contains("doctorvisit")) {
                        examine = examine.addOperation(new DoctorVisit(new Measurement(examine)));
                    } else if (line.contains("imaging")) {
                        examine = examine.addOperation(new Imaging(new Measurement(examine)));
                    } else {
                        examine = examine.addOperation(new Measurement(examine));
                    }
                }
            else if(line.contains("tests")) {
                    if (line.contains("doctorvisit") && (line.contains("imaging"))) {
                        examine = examine.addOperation(new DoctorVisit(new Test(new Imaging(examine))));

                    } else if (line.contains("doctorvisit")) {
                        examine = examine.addOperation(new DoctorVisit(new Test(examine)));

                    } else if (line.contains("imaging")) {
                        examine = examine.addOperation(new Imaging(new Test(examine)));

                    } else {
                        examine = examine.addOperation(new Test(examine));

                    }
                }
            else if(line.contains("doctorvisit")) {
                    if (line.contains("imaging")) {
                        examine = examine.addOperation(new Imaging(new DoctorVisit(examine)));

                    } else {
                        examine = examine.addOperation(new DoctorVisit(examine));
                    }
                }
            else if(line.contains("imaging")){
                    examine = examine.addOperation(new Imaging(examine)); }
            total.add(examine.cost()); //putting every cost into an array to sum it up later
            //writing each time about the examination and cost
            myWriterteam.write("\t"+line.split("\\t")[0]+" "+line.split("\\t")[1]+" "+String.valueOf(examine.cost())+"$"+"\n");

        }

    }
}
