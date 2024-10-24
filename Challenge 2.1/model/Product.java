package model;

public class Product{
    private final  String name;
    private final double  price;
    private final int quantity;

    public Product() {
        this.name = null;
        this.price = 0;
        this.quantity = 0;
    }


    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }


    @Override
    public String toString() {
        return name + ": price " + price + ", quantity " + quantity;
    }
      
}