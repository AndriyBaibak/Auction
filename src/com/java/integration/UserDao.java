package integration;

import entity.user.User;

/**
 * Created by Andriy on 20.05.2015.
 */
public interface UserDao {
    public void registration(String login, String password, String firstName, String lastName);
    public String getUserPasswordByLogin(String login);
}
