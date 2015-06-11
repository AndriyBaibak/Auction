package gui;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.apache.log4j.Logger;

/**
 * Created by Andriy on 09.06.2015.
 */

@Title("Auction")
@Theme("reindeer")
public class LoginWindow extends UI
{
    private static Logger log = Logger.getLogger(LoginWindow.class);

    private Button btnLogin = new Button("Login");
    private TextField login = new TextField ( "Username");
    private PasswordField password = new PasswordField ( "Password");


    @Override
    protected void init(VaadinRequest request) {
        try {
            configureComponents();
        } catch (Exception e) {
            log.error("-----------------------2321");
        }
        buildLayout();
    }




    private void configureComponents() {
        btnLogin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                log.error("-----------------------");
                getUI().getNavigator().navigateTo(MainUI.NAME);
            }
        });

    }
    private void buildLayout() {
        Window mainWindow = new Window();
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.addComponent(login);
        verticalLayout.addComponent(password);
        verticalLayout.addComponent(btnLogin);
        verticalLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        mainWindow.setContent(verticalLayout);

        mainWindow.center();
        addWindow(mainWindow);

   }




}