package service;

import buisnessLogic.ActionWithUserImpl;
import entity.user.User;

/**
 * Created by Andriy on 26.05.2015.
 */
public class UserServiceImpl implements UserService {
    private ActionWithUserImpl actionWithUser = new ActionWithUserImpl();

    @Override
    public void registration(String login, String password, String firstName, String lastName) {
        actionWithUser.registration(login, password, firstName, lastName);
    }

    @Override
    public User getUserByName(String name) {
        return actionWithUser.getUserByName(name);
    }
}
