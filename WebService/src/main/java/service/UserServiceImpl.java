package service;

import buisnessLogic.ActionWithUserImpl;
import entity.user.User;

import javax.jws.WebService;

@WebService(serviceName = "UserServiceImpl",
        portName="EntityPort",
        endpointInterface = "service.UserService", targetNamespace = "")
public class UserServiceImpl implements UserService {

    private ActionWithUserImpl actionWithUser = new ActionWithUserImpl();

    @Override
    public void registration(User user) {
        actionWithUser.registration(user);
    }
    @Override
    public String getUserPasswordByLogin(String login) {
        return actionWithUser.getUserPasswordByLogin(login);
    }
    @Override
    public String getUserNameByUserLogin(String login){
        return actionWithUser.getUserNameByUserLogin(login);
    }
}
