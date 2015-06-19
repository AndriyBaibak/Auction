package service;

import entity.user.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface UserService {

    @WebMethod
    public void registration(User user);

    @WebMethod
    public String getUserPasswordByLogin(String login);

    @WebMethod
    public String getUserNameByUserLogin(String login);
}
