package pl.edu.wszib.warehouse.model;

public class Product {
    private String name;
    private String productID;
    private int quantity;

    public Product(String name, int quantity, String productID){
        this.name = name;
        this.quantity=quantity;
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", productID=" + productID +
                '}';
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductID() {
        return productID;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }


}
