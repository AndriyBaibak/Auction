package gui;


import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.Reindeer;
import entity.user.User;
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
        login.addValidator(new BeanValidator(User.class, "login"));
        password.addValidator(new BeanValidator(User.class, "password"));
        btnLogin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String loginForCheck = login.getValue();
                String passwordForCheck = password.getValue();
                ServiceBeetwenVaadinAndJaxWs serviceForCurrentUserWithLoginAndPassword = new ServiceBeetwenVaadinAndJaxWs(loginForCheck, passwordForCheck);
                String passwordOfRegisteredEarlyUser = serviceForCurrentUserWithLoginAndPassword.getUserPasswordByLogin(loginForCheck);
                if (!(passwordForCheck).equals(passwordOfRegisteredEarlyUser)) {
                        close();
                    try {
                        throw new Exception("not valid password"); //todo вирішити проблему за роботою коду навіть якщо данні невірні
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                try {
                    MainUI.addMainWindow(loginForCheck, passwordOfRegisteredEarlyUser);
                    MainUI.getMainWindow().refreshLots();
                    close();
                } catch (Exception ex) {
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
        btnLogin.setStyleName(ChameleonTheme.BUTTON_WIDE);//todo
        buttons.addComponent(registration);
        verticalLayout.addComponent(buttons);
        verticalLayout.setStyleName("backColor");
        buttons.setMargin(true);
        setStyleName("backColor");
        verticalLayout.setMargin(true);
        setContent(verticalLayout);

        center();


    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }
}