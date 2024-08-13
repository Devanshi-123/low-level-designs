import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    // data members
    private final Map<Product, Integer> products;

    // constructor
    public Inventory(Map<Product, Integer> products) {
        this.products = products;
    }

    public Inventory() {
        products = new ConcurrentHashMap<>();
    }

    // methods
    public void addProduct(Product product, int qty){
        products.put(product, qty);
    }

    public void updateQuantity(Product product, int qty){
        products.put(product, qty);
    }

    public void removeProduct(Product product, int qty){
        products.remove(product, qty);
    }

    public int getQuantity(Product product){
        return products.getOrDefault(product, 0);
    }

    public boolean isAvlbl(Product product){
        return products.containsKey(product) && products.get(product) > 0;
    }

}
