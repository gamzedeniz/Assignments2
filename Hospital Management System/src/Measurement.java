public class Measurement extends Operations {
    public Measurement(ManagementSystem examination) {
        super(examination);
    }

    @Override //finding cost
    public int cost() {
        return super.cost()+5;
    }


    @Override //adding operations
    public ManagementSystem addOperation(ManagementSystem operation) {
        return operation;
    }
}
