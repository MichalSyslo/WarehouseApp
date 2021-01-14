package pl.edu.wszib.warehouse.services;

import pl.edu.wszib.warehouse.model.User;
import pl.edu.wszib.warehouse.model.view.RegistrationModel;

public interface IUserService {
    void authenticate(User user);
    boolean register(RegistrationModel registrationModel);
    void logout();
}
