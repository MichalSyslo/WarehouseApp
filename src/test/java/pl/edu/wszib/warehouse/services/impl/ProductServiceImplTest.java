package pl.edu.wszib.warehouse.services.impl;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.warehouse.configuration.TestConfiguration;
import pl.edu.wszib.warehouse.dao.IProductDAO;
import pl.edu.wszib.warehouse.dao.IUserDAO;
import pl.edu.wszib.warehouse.model.Product;
import pl.edu.wszib.warehouse.services.IProductService;
import pl.edu.wszib.warehouse.session.SessionObject;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class ProductServiceImplTest {

    @Autowired
    IProductService productService;

    @Resource
    SessionObject sessionObject;

    @MockBean
    IProductDAO productDAO;

    @MockBean
    IUserDAO userDAO;


    @Test
    public void correctUpdateProductTest(){
        Product product = new Product(1, "fridge", 25);
        Mockito.when(this.productDAO.getProductByName(product.getName())).thenReturn(null);

        boolean result = this.productService.updateProduct(product);

        Assert.assertTrue(result);
    }

    @Test
    public void incorrectProductQuantityTest(){
        Product product = new Product(1, "fridge", -1);
        Mockito.when(this.productDAO.getProductByName(product.getName())).thenReturn(null);

        boolean result = this.productService.updateProduct(product);

        Assert.assertFalse(result);
    }

    @Test
    public void incorrectProductNameTest(){
        Product product = new Product(1, "", 25);
        Mockito.when(this.productDAO.getProductByName(product.getName())).thenReturn(null);

        boolean result = this.productService.updateProduct(product);

        Assert.assertFalse(result);
    }

    @Test
    public void removeProductFromDBTest(){
        Product product = new Product(1, "fridge", 25);
        Mockito.when(this.productDAO.getProductByID(product.getId())).thenReturn(new Product());

        boolean result = this.productService.removeProductFromDataBase(product);

        Assert.assertTrue(result);
    }

    @Test
    public void addProductTest(){
        Product product = new Product(1, "fridge", 5);
        Mockito.when(this.productDAO.getProductByID(product.getId())).thenReturn(new Product(1, "fridge", 25));

        boolean result = this.productService.updateProduct(product);

        Assert.assertTrue(result);
    }

    @Test
    public void incorrectSubtractionProductTest(){
        Product product = new Product(1, "fridge", 15);
        Mockito.when(this.productDAO.getProductByID(product.getId())).thenReturn(new Product(1, "fridge", 10));

        boolean result = this.productService.subtractProduct(product);

        Assert.assertFalse(result);
    }
}
