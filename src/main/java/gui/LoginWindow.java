package gui;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Andriy on 09.06.2015.
 */

@Title("Auction")
@Theme("chameleon")
public class LoginWindow extends UI
{
    private Button btnLogin = new Button("Login");
    private TextField login = new TextField ( "Username");
    private PasswordField password = new PasswordField ( "Password");


    @Override
    protected void init(VaadinRequest request) {
        try {
            configureComponents();
        } catch (Exception e) {

        }
        buildLayout();
    }




    private void configureComponents() {

    }
    private void buildLayout() {
        Window mainWindow = new Window();
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.addComponent(login);
        verticalLayout.addComponent(password);
        verticalLayout.addComponent(btnLogin);
        mainWindow.setContent(verticalLayout);
        mainWindow.center();
        addWindow(mainWindow);

    }


    @WebServlet(urlPatterns = "/*" )
    @VaadinServletConfiguration(ui = LoginWindow.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}