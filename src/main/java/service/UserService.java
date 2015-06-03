package service;

import entity.user.User;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Andriy on 26.05.2015.
 */
@WebService
public interface UserService {
    @WebMethod
    public void registration(String login, String password, String firstName, String lastName);

    @WebMethod
    public User getUserByName(String name);
}
