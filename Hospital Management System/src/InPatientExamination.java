public class InPatientExamination implements ManagementSystem {

    @Override//to find cost
    public int cost()
    {
        return 10;
    }

    @Override//to add operations
    public ManagementSystem addOperation(ManagementSystem operation) {
        return operation;
    }
}
