package pl.edu.wszib.warehouse.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.warehouse.dao.IProductDAO;
import pl.edu.wszib.warehouse.model.Product;
import pl.edu.wszib.warehouse.services.IProductService;
import pl.edu.wszib.warehouse.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    IProductDAO productDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public List<Product> getAllProducts() {
        return this.productDAO.getAllProducts();
    }

    @Override
    public Product getProductByID(int id) {
        return this.productDAO.getProductByID(id);
    }

    @Override
    public boolean updateProduct(Product product) {
        if(this.productDAO.getProductByName(product.getName()) != null ) {
            this.sessionObject.setInfo("Product with given name already exists");
            return false;
        } else if(product.getQuantity() < 0){
            this.sessionObject.setInfo("Please provide positive amount");
            return false;
        } else if (product.getName().length() == 0){
            this.sessionObject.setInfo("Please provide correct product name");
            return false;
        }

        this.productDAO.updateProduct(product);
        return true;
    }

    @Override
    public boolean createNewProduct(Product product) {
        if(this.productDAO.getProductByName(product.getName()) != null) {
            this.sessionObject.setInfo("Such product already exists");
            return false;
        }else if(product.getQuantity() < 0){
            this.sessionObject.setInfo("Please provide positive amount");
            return false;
        } else if (product.getName().length() == 0){
            this.sessionObject.setInfo("Please provide correct product name");
            return false;
        }

        this.productDAO.persistProduct(product);
        return true;
    }

    @Override
    public boolean removeProductFromDataBase(Product product) {
        if(this.productDAO.getProductByID(product.getId()) == null){
            this.sessionObject.setInfo("Product with the given ID doesn't exist");
            return false;
        }
        this.productDAO.removeProduct(product);
        return true;
    }

    @Override
    public boolean addProduct(Product product) {
        if(product.getQuantity()<=0){
            this.sessionObject.setInfo("Please provide positive amount");
            return false;
        }
        Product productFromDB = this.productDAO.getProductByID(product.getId());
        productFromDB.setQuantity(productFromDB.getQuantity() + product.getQuantity());

        this.productDAO.updateProduct(productFromDB);
        return true;
    }

    @Override
    public boolean subtractProduct(Product product) {
        if(product.getQuantity()<=0){
            this.sessionObject.setInfo("Please provide positive amount");
            return false;
        }
        Product productFromDB = this.productDAO.getProductByID(product.getId());
        if(productFromDB.getQuantity() - product.getQuantity() < 0){
            this.sessionObject.setInfo("Given number exceeds number of products in stock");
            return false;
        }
        productFromDB.setQuantity(productFromDB.getQuantity() - product.getQuantity());
        this.productDAO.updateProduct(productFromDB);
        return true;
    }
}
