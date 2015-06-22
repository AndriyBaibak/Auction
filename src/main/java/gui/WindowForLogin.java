package gui;


import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ChameleonTheme;
import entity.user.User;
import org.apache.log4j.Logger;

public class WindowForLogin extends Window implements Button.ClickListener {
    private static Logger log = Logger.getLogger(WindowForLogin.class);
    private Button btnLogin = new Button("Login");
    private Button registration = new Button("Registration");
    private TextField login = new TextField();
    private PasswordField password = new PasswordField();
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
                if ((passwordForCheck).equals(passwordOfRegisteredEarlyUser)) {
                    MainUI.addMainWindow(loginForCheck, passwordOfRegisteredEarlyUser);
                    MainUI.getMainWindow().refreshLots();
                    close();
                } else {
                    close();
                    try {
                        throw new Exception("not valid password"); //todo вирішити проблему за роботою коду навіть якщо данні невірні
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

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

        Label loginLabel = new Label("Login");
        HorizontalLayout loginLayout = new HorizontalLayout(loginLabel, login);
        loginLayout.setComponentAlignment(login, Alignment.MIDDLE_RIGHT);
        loginLayout.setSpacing(true);
        loginLayout.setMargin(true);

        Label passwordLabel = new Label("Password");
        HorizontalLayout passwordLayout = new HorizontalLayout(passwordLabel, password);
        passwordLayout.setSpacing(true);
        passwordLayout.setMargin(true);

        HorizontalLayout buttons = new HorizontalLayout(btnLogin, registration);
        buttons.setMargin(true);
        buttons.setSpacing(true);
        buttons.setComponentAlignment(btnLogin, Alignment.MIDDLE_LEFT);
        buttons.setComponentAlignment(registration, Alignment.MIDDLE_RIGHT);
        btnLogin.setStyleName(ChameleonTheme.BUTTON_WIDE);
        registration.setStyleName(ChameleonTheme.BUTTON_WIDE);

        VerticalLayout verticalLayout = new VerticalLayout(loginLayout, passwordLayout, buttons);
        verticalLayout.setComponentAlignment(loginLayout, Alignment.TOP_RIGHT);
        verticalLayout.setComponentAlignment(buttons, Alignment.BOTTOM_CENTER);
        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);
        verticalLayout.setStyleName("backColor");

        setSizeUndefined();
        setCaption("Authentication");
        setContent(verticalLayout);
        center();


    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }
}