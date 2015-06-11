package service;

import buisnessLogic.ActionWithUserImpl;
import entity.user.User;

import javax.jws.WebService;

/**
 * Created by Andriy on 26.05.2015.
 */
@WebService(endpointInterface = "service.UserService")
public class UserServiceImpl implements UserService {
    private ActionWithUserImpl actionWithUser = new ActionWithUserImpl();

    @Override
    public void registration(String login, String password, String firstName, String lastName) {
        actionWithUser.registration(login, password, firstName, lastName);
    }

    @Override
    public String getUserPasswordByLogin(String login) {
        return actionWithUser.getUserPasswordByLogin(login);
    }
}
