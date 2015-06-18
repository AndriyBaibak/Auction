package buisnessLogic;

import entity.user.User;

public interface ActionWithUser {
    public void registration(User user);

    public String getUserPasswordByLogin(String login);

    public String getUserNameByUserLogin(String login);


}
