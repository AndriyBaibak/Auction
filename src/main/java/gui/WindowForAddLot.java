package gui;


import com.vaadin.data.validator.BeanValidator;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ChameleonTheme;
import entity.lot.Lot;
import org.apache.log4j.Logger;

import java.util.Date;

public class WindowForAddLot extends Window implements Button.ClickListener {
    private static Logger log = Logger.getLogger(WindowForAddLot.class);

    Button add = new Button("ok");
    Button cancel = new Button("cancel");
    TextField name = new TextField();
    DateField date = new DateField();
    TextField description = new TextField();
    TextField price = new TextField();

    public WindowForAddLot() {
        configureComponents();
        buildAddWindow();
    }

    private void configureComponents() {
        date.setRangeStart(new Date());
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

        Label lotNameLabel = new Label("Lot name");
        HorizontalLayout layoutLotName = new HorizontalLayout(lotNameLabel, name);
        layoutLotName.setSpacing(true);
        layoutLotName.setMargin(true);
        layoutLotName.setWidth("56%");

        Label dateLabel = new Label("Date");
        HorizontalLayout dateLayout = new HorizontalLayout(dateLabel, date);
        dateLayout.setSpacing(true);
        dateLayout.setMargin(true);
        dateLayout.setWidth("56%");

        Label descriptionLabel = new Label("Description");
        HorizontalLayout descriptionLayout = new HorizontalLayout(descriptionLabel, description);
        descriptionLayout.setSpacing(true);
        descriptionLayout.setMargin(true);
        descriptionLayout.setWidth("56%");

        Label priceLabel = new Label("Price");
        HorizontalLayout priceLayout = new HorizontalLayout(priceLabel, price);
        priceLayout.setSpacing(true);
        priceLayout.setMargin(true);
        priceLayout.setWidth("56%");

        HorizontalLayout buttons = new HorizontalLayout(add, cancel);
        buttons.setSpacing(true);
        buttons.setMargin(true);
        add.setStyleName(ChameleonTheme.BUTTON_WIDE);
        cancel.setStyleName(ChameleonTheme.BUTTON_WIDE);

        VerticalLayout body = new VerticalLayout(layoutLotName, dateLayout, descriptionLayout, priceLayout, buttons);
        body.setComponentAlignment(buttons, Alignment.BOTTOM_CENTER);
        body.setSpacing(true);

        setSizeUndefined();
        setContent(body);
        setCaption("New Lot");
        center();
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
    }


}