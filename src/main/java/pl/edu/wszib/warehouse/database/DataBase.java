package pl.edu.wszib.warehouse.database;

import org.springframework.stereotype.Component;
import pl.edu.wszib.warehouse.model.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataBase implements IDataBase{
    private final List<Product> products = new ArrayList<>();

    public DataBase(){
        products.add(new Product("refrigerator", 50, "XYZ111"));
        products.add(new Product("dishwasher", 20, "AAA666"));
        products.add(new Product("camera", 35, "BCD456"));
        products.add(new Product("Vacuum cleaner", 5, "777777"));
        products.add(new Product("microwave", 17, "PUB696"));

    }

    @Override
    public boolean addProduct(Product p) {
        if(p.getQuantity()<0) return false;
        for(Product currentProduct : products) {
            if(currentProduct.getProductID().equals(p.getProductID())){
                currentProduct.setQuantity(currentProduct.getQuantity()+p.getQuantity());
                return true;
            }
        }
        products.add(p);
        return true;
    }

    @Override
    public boolean removeProduct(String productID, int quantityToRemove) {
        for(Product currentProduct : products) {
            if (currentProduct.getProductID().equals(productID) && currentProduct.getQuantity()-quantityToRemove >=0) {
                currentProduct.setQuantity(currentProduct.getQuantity()-quantityToRemove);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.products;
    }
}
