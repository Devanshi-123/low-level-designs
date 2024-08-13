public class ReturnChangeState implements VendingMachineState{
    private final VendingMachine vendingMachine;

    public ReturnChangeState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }


    @Override
    public void selectProduct(Product product) {
        System.out.println("product already selected");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("payment already made");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("payment already made");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("product already dispensed");
    }

    @Override
    public void returnChange() {
        if(vendingMachine.getTotalPayment() - vendingMachine.getSelectedProduct().getPrice() > 0){
            System.out.println("change returned --->>");
            System.out.println(vendingMachine.getTotalPayment() - vendingMachine.getSelectedProduct().getPrice());
            vendingMachine.resetTotalPayment();
            vendingMachine.resetSelectedProduct();
            vendingMachine.setState(vendingMachine.getIdleState());
        }
    }
}
