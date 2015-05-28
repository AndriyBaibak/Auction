package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Andriy on 26.05.2015.
 */
@WebService
public interface UserService {
    @WebMethod
    public void registration(String login, String password, String firstName, String lastName);
}
