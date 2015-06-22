package gui;

import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ChameleonTheme;
import entity.user.User;
import org.apache.log4j.Logger;

public class WindowForRegistration extends Window implements Button.ClickListener {
    private static Logger log = Logger.getLogger(WindowForRegistration.class);

    private Button register = new Button("Register");
    private Button cancel = new Button("Cancel");
    TextField login = new TextField();
    private TextField password = new TextField();
    private TextField firstName = new TextField();
    private TextField lastName = new TextField();


    public WindowForRegistration() {
        configureComponents();
        buildLayout();
    }


    private void configureComponents() {
        login.addValidator(new BeanValidator(User.class, "login"));
        password.addValidator(new BeanValidator(User.class, "password"));
        register.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    new ServiceBeetwenVaadinAndJaxWs("anonym", "anonym").registration(new User(login.getValue(), password.getValue(), firstName.getValue(), lastName.getValue()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                close();
            }
        });
        cancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Notification.show("Cancelled", Notification.Type.TRAY_NOTIFICATION);
            }
        });


    }

    private void buildLayout() {

        Label loginLabel = new Label("Login");
        HorizontalLayout loginLayout = new HorizontalLayout(loginLabel, login);
        loginLayout.setMargin(true);
        loginLayout.setSpacing(true);
        loginLayout.setWidth("60%");

        Label passwordLabel = new Label("Password");
        HorizontalLayout passwordLayout = new HorizontalLayout(passwordLabel, password);
        passwordLayout.setMargin(true);
        passwordLayout.setSpacing(true);
        passwordLayout.setWidth("60%");

        Label firstNameLabel = new Label("First name");
        HorizontalLayout firstNameLayout = new HorizontalLayout(firstNameLabel, firstName);
        firstNameLayout.setMargin(true);
        firstNameLayout.setSpacing(true);
        firstNameLayout.setWidth("60%");

        Label lastNameLabel = new Label("Last name");
        HorizontalLayout lastNameLayout = new HorizontalLayout(lastNameLabel, lastName);
        lastNameLayout.setMargin(true);
        lastNameLayout.setSpacing(true);
        lastNameLayout.setWidth("60%");

        HorizontalLayout buttons = new HorizontalLayout(register, cancel);
        buttons.setMargin(true);
        buttons.setSpacing(true);
        register.setStyleName(ChameleonTheme.BUTTON_WIDE);
        cancel.setStyleName(ChameleonTheme.BUTTON_WIDE);

        VerticalLayout mainLayout = new VerticalLayout(loginLayout, passwordLayout, firstNameLayout, lastNameLayout, buttons);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setComponentAlignment(buttons, Alignment.BOTTOM_CENTER);

        setSizeUndefined();
        setCaption("Registration");
        setContent(mainLayout);
        setStyleName("headers");
        center();
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }


}
