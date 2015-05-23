package service;

import buisnessLogic.ActionWithUserImpl;
import entity.user.User;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
@WebService(endpointInterface = "service.Users")
public class UsersImpl implements Users {

    private ActionWithUserImpl actionWithUser = new ActionWithUserImpl();

    @Override
    public void registration(String login, String password, String firstName, String lastName) {
        actionWithUser.registration(login, password, firstName, lastName);
    }
}
