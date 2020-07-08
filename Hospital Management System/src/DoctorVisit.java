public class DoctorVisit extends Operations {
    public DoctorVisit(ManagementSystem examination) {
        super(examination);
    }

    @Override //to find cost
    public int cost() {
        return super.cost() + 15;
    }


    @Override //adding operation
    public ManagementSystem addOperation(ManagementSystem operation) {
        return operation;
    }
}
