import java.util.ArrayList;

public class Patient {
    //all the info about a patient
    private int id;
    private int admissionId;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private int cost;
    private ArrayList<String> admission;

    public Patient(int id,String name, String surname, String phone,String address)
    {
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.phone=phone;
        this.address=address;
    }
    public Patient(int id,int admissionId)
    {
        this.id=id;
        this.admissionId=admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }

    public int getAdmissionId() {
        return admissionId;
    }

    public ArrayList<String> getAdmission() {
        return admission;
    }

    public void setAdmission(ArrayList<String> admission) {
        this.admission = admission;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
