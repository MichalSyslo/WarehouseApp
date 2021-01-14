package pl.edu.wszib.warehouse.services;

import pl.edu.wszib.warehouse.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product getProductByID(int id);
    boolean updateProduct(Product product);
    boolean createNewProduct(Product product);
    boolean removeProductFromDataBase(Product product);

    boolean addProduct(Product product);
    boolean subtractProduct(Product product);
}
