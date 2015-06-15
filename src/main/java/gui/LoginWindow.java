package gui;


import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.apache.log4j.Logger;

public class LoginWindow extends CustomLayout
{
    private static Logger log = Logger.getLogger(LoginWindow.class);

    public Window getAddFormWindow() {
        return addFormWindow;
    }

    private Window addFormWindow = new Window();
    private Button btnLogin = new Button("Login");
    private TextField login = new TextField ( "Username");
    private PasswordField password = new PasswordField ( "Password");



   public  LoginWindow() {
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
                addFormWindow.close();
            }
        });

    }
    private void buildLayout() {
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.addComponent(login);
        verticalLayout.addComponent(password);
        verticalLayout.addComponent(btnLogin);
        verticalLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        addFormWindow.setContent(verticalLayout);

        addFormWindow.center();


   }




}