public class Imaging extends Operations {
    public Imaging(ManagementSystem examination) {
        super(examination);
    }


    @Override//to find cost
    public int cost() {
        return super.cost() + 10;
    }

    @Override//adding operation
    public ManagementSystem addOperation(ManagementSystem operation) {
        return operation;
    }
}
