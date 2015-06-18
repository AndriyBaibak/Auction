package integration;

import entity.user.User;

/**
 * Created by Andriy on 20.05.2015.
 */
public interface UserDao {
    public void registration(User user);
    public String getUserPasswordByLogin(String login);
    public String getUserNameByUserLogin(String login);
}
