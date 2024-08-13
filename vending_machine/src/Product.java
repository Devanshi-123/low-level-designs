public class Product {
    // for encapsulation we made data members as private
    private final int price;
    private final String name;

    // constructor
    Product(int price, String name){
        this.price = price;
        this.name = name;
    }

    // getter and setter
    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }

}
