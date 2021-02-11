package pl.edu.wszib.warehouse.services.impl;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.warehouse.configuration.TestConfiguration;
import pl.edu.wszib.warehouse.dao.IProductDAO;
import pl.edu.wszib.warehouse.dao.IUserDAO;
import pl.edu.wszib.warehouse.model.User;
import pl.edu.wszib.warehouse.model.view.RegistrationModel;
import pl.edu.wszib.warehouse.services.IUserService;
import pl.edu.wszib.warehouse.session.SessionObject;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    IUserService userService;

    @Resource
    SessionObject sessionObject;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IProductDAO productDAO;

    @Test
    public void correctAuthenticationTest() {
        User user = new User(1, "jerry", "jerry", User.Role.USER);
        Mockito.when(userDAO.getUserByLogin("jerry")).thenReturn(new User(1, "jerry", "jerry", User.Role.USER));

        this.userService.authenticate(user);

        Assert.assertNotNull(this.sessionObject.getLoggedUser());
    }

    @Test
    public void incorrectLoginAuthenticationTest() {
        User user = new User(1, "jerry", "jerry", User.Role.USER);
        Mockito.when(userDAO.getUserByLogin("jerry")).thenReturn(null);

        this.userService.authenticate(user);

        Assert.assertNull(this.sessionObject.getLoggedUser());
    }

    @Test
    public void incorrectPasswordAuthenticationTest() {
        User user = new User(1, "jerry", "jerry123", User.Role.USER);
        Mockito.when(userDAO.getUserByLogin("jerry")).thenReturn(new User(1, "jerry", "jerry", User.Role.USER));

        this.userService.authenticate(user);

        Assert.assertNull(this.sessionObject.getLoggedUser());
    }

    @Test
    public void correctRegistrationTest(){
        RegistrationModel registrationModel = new RegistrationModel("jerry", "jerry123", "jerry123");
        Mockito.when(this.userDAO.getUserByLogin("jerry")).thenReturn(null);
        Mockito.when(this.userDAO.persistUser(ArgumentMatchers.any())).thenReturn(true);

        boolean result = this.userService.register(registrationModel);

        Assert.assertTrue(result);
    }

    @Test
    public void incorrectRegistrationTest(){
        RegistrationModel registrationModel = new RegistrationModel("tommy", "tommy123", "tommy123");
        Mockito.when(this.userDAO.getUserByLogin("tommy")).thenReturn(new User());

        boolean result = this.userService.register(registrationModel);

        Assert.assertFalse(result);
    }
}
