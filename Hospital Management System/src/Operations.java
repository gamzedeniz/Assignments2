public abstract class Operations implements ManagementSystem {
    private final ManagementSystem examination;
    public Operations(ManagementSystem examination)
    {
        this.examination=examination;
    }

    @Override //adding operations
    public ManagementSystem addOperation(ManagementSystem operation) {
        return operation;
    }

    @Override//to add cost
    public int cost() {
        return examination.cost();
    }
}
