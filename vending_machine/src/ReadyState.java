public class ReadyState implements VendingMachineState{
    private final VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }


    @Override
    public void selectProduct(Product product) {

    }

    @Override
    public void insertCoin(Coin coin) {
        vendingMachine.addCoin(coin);
        System.out.println("Coin inserted");
        checkPaymentStatus();
    }

    @Override
    public void insertNote(Note note) {
        vendingMachine.addNote(note);
        System.out.println("Note inserted");
        checkPaymentStatus();
    }

    @Override
    public void dispenseProduct() {
        System.out.println("First make payment");
    }

    @Override
    public void returnChange() {
        System.out.println("First make payment");
    }

    private void checkPaymentStatus(){
        Product pro = vendingMachine.getSelectedProduct();
        if (vendingMachine.getTotalPayment() >= pro.getPrice()){
            System.out.println("Payment done");
            vendingMachine.setState(vendingMachine.getDispenseState());
        }
    }
}
