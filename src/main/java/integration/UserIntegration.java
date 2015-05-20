package integration;

import entity.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
public class UserIntegration implements UserDao{

    private List<User> userList = new ArrayList<User>();

    @Override
    public void registration(String login, String password, String firstName, String lastName) {
        userList.add(new User(login, password, firstName, lastName));
    }
}
