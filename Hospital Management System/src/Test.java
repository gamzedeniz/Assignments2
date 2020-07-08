public class Test extends Operations {
    public Test(ManagementSystem examination) {
        super(examination);
    }


    @Override//to add cost
    public int cost() {
        return super.cost()+7;
    }

    @Override//to add operation
    public ManagementSystem addOperation(ManagementSystem operation) {
        return operation;
    }
}
