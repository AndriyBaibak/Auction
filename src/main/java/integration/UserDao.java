package integration;

import entity.user.User;

public interface UserDao {
    public void registration(User user);
    public String getUserPasswordByLogin(String login);
    public String getUserNameByUserLogin(String login);
}
