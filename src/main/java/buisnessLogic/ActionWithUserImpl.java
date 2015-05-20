package buisnessLogic;

import integration.UserIntegration;

import javax.inject.Inject;

/**
 * Created by Andriy on 21.05.2015.
 */
public class ActionWithUserImpl implements ActionWithUser{
    @Inject
    private UserIntegration userIntegration;

    @Override
    public void registration(String login, String password, String firstName, String lastName) {
        userIntegration.registration(login, password, firstName, lastName);
    }
}