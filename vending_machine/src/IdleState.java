public class IdleState implements VendingMachineState {
    // an object of vending machine
    private final VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        boolean isAvlbl = vendingMachine.inventory.isAvlbl(product);

        if(isAvlbl){
            vendingMachine.setSelectedProduct(product);
            vendingMachine.setState(vendingMachine.getReadyState());
            System.out.println("Product selected" + product.getName());
        } else{
            System.out.println("This product is out of stock" + product.getName());
        }
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please select product first");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Please select product first");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select product first");
    }

    @Override
    public void returnChange() {
        System.out.println("Please select product first");
    }
}
