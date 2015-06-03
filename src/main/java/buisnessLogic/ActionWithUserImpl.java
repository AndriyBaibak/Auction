package buisnessLogic;

import entity.user.User;
import integration.UserIntegration;

public class ActionWithUserImpl implements ActionWithUser {

    private UserIntegration userIntegration = new UserIntegration();

    @Override
    public void registration(String login, String password, String firstName, String lastName) {
        userIntegration.registration(login, password, firstName, lastName);
    }

    public User getUserByName(String name) {
        return userIntegration.getUserByName(name);
    }
}

