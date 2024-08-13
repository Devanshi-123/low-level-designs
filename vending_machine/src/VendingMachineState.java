public interface VendingMachineState {
    // Operations which the classes will have to perform
    void selectProduct(Product product);
    void insertCoin(Coin coin);
    void insertNote(Note note);
    void dispenseProduct();
    void returnChange();
}
