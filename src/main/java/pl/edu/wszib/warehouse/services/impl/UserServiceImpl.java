package pl.edu.wszib.warehouse.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.warehouse.dao.IUserDAO;
import pl.edu.wszib.warehouse.model.User;
import pl.edu.wszib.warehouse.model.view.RegistrationModel;
import pl.edu.wszib.warehouse.services.IUserService;
import pl.edu.wszib.warehouse.session.SessionObject;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(User user) {
        User userFromDB = this.userDAO.getUserByLogin(user.getLogin());
        if(userFromDB==null){
            return;
        }
        if(userFromDB.getPassword().equals(user.getPassword())){
            this.sessionObject.setLoggedUser(userFromDB);
        }
    }

    @Override
    public boolean register(RegistrationModel registrationModel) {
        if(this.userDAO.getUserByLogin(registrationModel.getLogin()) != null){
            return false;
        }

        return this.userDAO.persistUser(new User(0, registrationModel.getLogin(), registrationModel.getPassword(), User.Role.USER));
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }
}
