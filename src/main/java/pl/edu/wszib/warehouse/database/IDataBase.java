package pl.edu.wszib.warehouse.database;

import pl.edu.wszib.warehouse.model.Product;

import java.util.List;

public interface IDataBase {
    boolean addProduct(Product p);
    boolean removeProduct(String productID, int quantityToRemove);
    List<Product> getAllProducts();
}
