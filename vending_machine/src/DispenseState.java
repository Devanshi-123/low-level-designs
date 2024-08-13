public class DispenseState implements VendingMachineState {
    private final VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }


    @Override
    public void selectProduct(Product product) {
        System.out.println("product already selected" + vendingMachine.getSelectedProduct().getName());
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Payment already done" + vendingMachine.getTotalPayment());
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Payment already done" + vendingMachine.getTotalPayment());
    }

    @Override
    public void dispenseProduct() {
        Product prod = vendingMachine.getSelectedProduct();
        Inventory inventory = new Inventory();
        inventory.updateQuantity(prod, inventory.getQuantity(prod)-1);
        vendingMachine.setState(vendingMachine.getReturnChangeState());
        System.out.println("Product dispensed");
    }

    @Override
    public void returnChange() {

    }
}
