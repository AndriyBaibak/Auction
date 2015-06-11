package gui;

import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;
import entity.lot.Lot;
import org.apache.log4j.Logger;
import service.LotService;
import service.LotServiceImpl;

public class AddLotForm extends Window implements Button.ClickListener {
    private static Logger log = Logger.getLogger(AddLotForm.class);

    Window addLotSubWindow = new Window();
    Button add = new Button("ok");
    Button cancel = new Button("cancel");
    TextField name = new TextField("Name");
    DateField date = new DateField("date");
    TextField description = new TextField("Description");
    TextField price = new TextField("Price");
    LotService service = new LotServiceImpl();
    Lot lot = new Lot();

    public AddLotForm() {
        configureComponents();
        buildAddWindow();
    }

    private void configureComponents() {
        name.addValidator(new StringLengthValidator(
                "The name must be 3-20 letters",
                3, 20, true));
        description.addValidator(new StringLengthValidator("The description must be 5-30 letters",
                5, 30, true));
        add.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    service.addLot(name.getValue(), "date.getValue()", Double.parseDouble(price.getValue()), description.getDescription());
                } catch (Exception e) {
                    log.error("Exception" + e);
                }
            }
        });
        cancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Notification.show("Cancelled", Notification.Type.TRAY_NOTIFICATION);

            }
        });
    }

    public void buildAddWindow() {
        VerticalLayout inputTextFields = new VerticalLayout(name, date, description, price);
        HorizontalLayout buttons = new HorizontalLayout(add, cancel);
        VerticalLayout body = new VerticalLayout(inputTextFields, buttons);
        FormLayout addForm = new FormLayout(body);
        addLotSubWindow.setContent(body);
        addLotSubWindow.center();
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
    }

    public Window getAddLotSubWindow() {
        return addLotSubWindow;
    }

}