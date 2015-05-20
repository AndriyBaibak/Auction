package service;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by Andriy on 20.05.2015.
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Users {
    void registration (String login, String password, String firstName, String lastName);
}
