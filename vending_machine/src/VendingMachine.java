import jdk.jfr.FlightRecorder;

public class VendingMachine {
    // if access modifier not speciified it is package private
    private static VendingMachine instance;
    Inventory inventory;
    private double totalPayment;
    private Product selectedProduct;
    private VendingMachineState currentState;
    private final VendingMachineState readyState;
    private final VendingMachineState dispenseState;
    private final VendingMachineState idleState;
    private final VendingMachineState returnChangeState;

    // since we want a singleton instance of vending machine class make its constructor as private
    private VendingMachine() {
        inventory = new Inventory();
        idleState = new IdleState(this);
        readyState = new ReadyState(this);
        dispenseState = new DispenseState(this);
        returnChangeState = new ReturnChangeState(this);
        currentState = idleState;
        selectedProduct = null;
        totalPayment = 0.0;
    }

    public static synchronized VendingMachine getInstance(){
        if(instance == null){
            instance = new VendingMachine();
        }
        return instance;
    }

    public void addCoin(Coin coin) {
        totalPayment += coin.getCoinValue();
    }

    public void addNote(Note note) {
        totalPayment += note.getNoteVal();
    }


    public void setSelectedProduct(Product product) {
        selectedProduct = product;
    }


    public VendingMachineState getReadyState() {
        return readyState;
    }

    public void setState(VendingMachineState state) {
        currentState = state;
    }


    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public VendingMachineState getDispenseState() {
        return dispenseState;
    }

    public VendingMachineState getReturnChangeState() {
        return returnChangeState;
    }

    public void resetTotalPayment() {
        totalPayment = 0;
    }

    public void resetSelectedProduct() {
        selectedProduct = null;
    }

    public VendingMachineState getIdleState() {
        return idleState;
    }

    public void selectProduct(Product coke) {
        currentState.selectProduct(coke);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    public void returnChange() {
        currentState.returnChange();
    }

    public void insertCoin(Coin coin){
        currentState.insertCoin(coin);
    }

    public void insertNote(Note note){
        currentState.insertNote(note);
    }
}
