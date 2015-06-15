package service;

import entity.user.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by Andriy on 26.05.2015.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface UserService {
    @WebMethod
    public void registration(String login, String password, String firstName, String lastName);

    @WebMethod
    public String getUserPasswordByLogin(String login);
}
