public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = VendingMachine.getInstance();

        // Add products to the inventory
        Product coke = new Product(1, "Coke");
        Product pepsi = new Product(2, "Pepsi");
        Product water = new Product(3, "Water");

        vendingMachine.inventory.addProduct(coke, 5);
        vendingMachine.inventory.addProduct(pepsi, 3);
        vendingMachine.inventory.addProduct(water, 2);

        // Select a product
        vendingMachine.selectProduct(coke);

        // Insert coins
        vendingMachine.insertCoin(Coin.Rupee);
        vendingMachine.insertCoin(Coin.Rupee);
        vendingMachine.insertCoin(Coin.Rupee);
        vendingMachine.insertCoin(Coin.Rupee);

        // Insert a note
        vendingMachine.insertNote(Note.Ten);

        // Dispense the product
        vendingMachine.dispenseProduct();

        // Return change
        vendingMachine.returnChange();

        // Select another product
        vendingMachine.selectProduct(pepsi);

        // Insert insufficient payment
        vendingMachine.insertCoin(Coin.Rupee);

        // Try to dispense the product
        vendingMachine.dispenseProduct();

        vendingMachine.selectProduct(water);

        // Insert more coins
        vendingMachine.insertCoin(Coin.Rupee);
        vendingMachine.insertCoin(Coin.Rupee);
        vendingMachine.insertCoin(Coin.Rupee);
        vendingMachine.insertCoin(Coin.Rupee);

        // Dispense the product
        vendingMachine.dispenseProduct();

        // Return change
        vendingMachine.returnChange();
    }
}