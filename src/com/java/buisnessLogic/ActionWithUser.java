package buisnessLogic;

import entity.user.User;

public interface ActionWithUser {
    public void registration(String login, String password, String firstName, String lastName);

    public String getUserPasswordByLogin(String login);

}
