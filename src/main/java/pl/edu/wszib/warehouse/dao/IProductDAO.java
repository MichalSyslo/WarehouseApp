package pl.edu.wszib.warehouse.dao;

import pl.edu.wszib.warehouse.model.Product;

import java.util.List;

public interface IProductDAO {
    List<Product> getAllProducts();
    Product getProductByID(int id);
    Product getProductByName(String name);
    void updateProduct(Product product);
    void persistProduct(Product product);
    void removeProduct(Product product);

}
