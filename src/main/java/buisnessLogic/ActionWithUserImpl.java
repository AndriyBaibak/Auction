package buisnessLogic;

import entity.user.User;
import integration.UserIntegration;

public class ActionWithUserImpl implements ActionWithUser {

    private UserIntegration userIntegration = new UserIntegration();

    @Override
    public void registration(User user) {
        userIntegration.registration(user);
    }

    public String getUserPasswordByLogin(String login) {
        return userIntegration.getUserPasswordByLogin(login);
    }

    @Override
    public String getUserNameByUserLogin(String login) {
        return userIntegration.getUserNameByUserLogin(login);
    }
}

