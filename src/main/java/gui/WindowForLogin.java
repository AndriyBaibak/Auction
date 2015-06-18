package gui;


import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.apache.log4j.Logger;

public class WindowForLogin extends Window implements Button.ClickListener {
    private static Logger log = Logger.getLogger(WindowForLogin.class);
    private Button btnLogin = new Button("Login");
    private Button registration = new Button("Registration");
    private TextField login = new TextField("Login");
    private PasswordField password = new PasswordField("Password");
    private WindowForRegistration registrationForm = new WindowForRegistration();


    public WindowForLogin() {
        configureComponents();
        buildLayout();
    }


    private void configureComponents() {
        btnLogin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String loginForCheck = login.getValue();
                String passwordForCheck = password.getValue();
                ServiceForVaadin serviceForCurrentUserWithLoginAndPassword = new ServiceForVaadin(loginForCheck, passwordForCheck);
                String passwordOfRegisteredEarlyUser = serviceForCurrentUserWithLoginAndPassword.getUserPasswordByLogin(loginForCheck);
                if (!(passwordForCheck).equals(passwordOfRegisteredEarlyUser)) {
                    try {
                        close();
                        throw new Exception("not valid password");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                    try {
                        MainUI.addMainWindow(loginForCheck, passwordOfRegisteredEarlyUser);
                        MainUI.getMainWindow().refreshLots();
                        close();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }

            }
        });
        registration.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                MainUI.getCurrent().addWindow(new WindowForRegistration());
            }
        });

    }

    private void buildLayout() {
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.addComponent(login);
        verticalLayout.addComponent(password);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponent(btnLogin);
        buttons.addComponent(registration);
        verticalLayout.addComponent(buttons);
        verticalLayout.setComponentAlignment(buttons, Alignment.BOTTOM_CENTER);
        verticalLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setContent(verticalLayout);

        center();


    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }
}