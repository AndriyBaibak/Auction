package gui;


import com.vaadin.data.validator.BeanValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;
import entity.lot.Lot;
import org.apache.log4j.Logger;

import java.util.Date;

public class WindowForAddLot extends Window implements Button.ClickListener {
    private static Logger log = Logger.getLogger(WindowForAddLot.class);

    Button add = new Button("ok");
    Button cancel = new Button("cancel");
    TextField name = new TextField("Name");
    DateField date = new DateField("date");
    TextField description = new TextField("Description");
    TextField price = new TextField("Price");

    public WindowForAddLot() {
        configureComponents();
        buildAddWindow();
    }

    private void configureComponents() {
        date.setRangeStart(new Date());//todo think need that
        date.setResolution(Resolution.MINUTE);
        name.addValidator(new BeanValidator(Lot.class, "lotName"));
        description.addValidator(new BeanValidator(Lot.class, "description"));

        add.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    Lot lotForSaving = new Lot(name.getValue(), date.getValue(), Double.parseDouble(price.getValue()), description.getValue(), MainWindow.owner);
                    MainWindow.serviceForVaadin.addLot(lotForSaving);
                    MainUI.getMainWindow().refreshLots();
                    close();
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
        inputTextFields.setMargin(true);
        HorizontalLayout buttons = new HorizontalLayout(add, cancel);
        buttons.setMargin(true);
        VerticalLayout body = new VerticalLayout(inputTextFields, buttons);
        setContent(body);
        center();
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
    }


}