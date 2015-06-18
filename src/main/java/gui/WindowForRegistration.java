package gui;

import com.vaadin.ui.*;
import entity.user.User;
import org.apache.log4j.Logger;

public class WindowForRegistration extends Window implements Button.ClickListener{
    private static Logger log = Logger.getLogger(WindowForRegistration.class);

   private Button register = new Button("Register");
   private Button cancel = new Button("Cancel");
   TextField login = new TextField("Login");
    private TextField password = new TextField("Password");
    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");


    public WindowForRegistration() {
            configureComponents();
        buildLayout();
    }




    private void configureComponents() {
        register.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    new ServiceForVaadin("anonym", "anonym").registration(new User(login.getValue(), password.getValue(), firstName.getValue(), lastName.getValue()));
                }catch (Exception ex){
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
        HorizontalLayout buttons = new HorizontalLayout(register,cancel);
        VerticalLayout mainLayout = new VerticalLayout(login, password, firstName, lastName, buttons);
        setContent(mainLayout);
       center();
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }


}
