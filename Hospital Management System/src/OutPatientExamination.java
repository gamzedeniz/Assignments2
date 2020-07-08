public class OutPatientExamination implements ManagementSystem {

    @Override//to add cost
    public int cost() {
        return 15;
    }

    @Override //add operation
    public ManagementSystem addOperation(ManagementSystem operation) {
        return operation;
    }
}
