package gui;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import entity.lot.Lot;
import entity.lot.State;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;

import java.text.ParseException;

public class ContactForm extends FormLayout implements ClickListener {
    private static Logger log = Logger.getLogger(ContactForm.class);

    Button add = new Button("Add", this);
    Button cancel = new Button("Cancel", this);
    TextField lotName = new TextField("Lot name");
    DateField finishDate = new DateField("Finish date");
    TextField startPrice = new TextField("Start price");
    TextField description = new TextField("Description");

    Lot lot;

    BeanFieldGroup<Lot> formFieldBindings;

    public ContactForm() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {

    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(add, cancel);
        actions.setSpacing(true);

        addComponents(actions, lotName, finishDate, startPrice, description);
        setComponentAlignment(actions,Alignment.MIDDLE_LEFT);
    }

    void edit(Lot lot) {
        this.lot = lot;
        if (lot != null) {
            formFieldBindings = BeanFieldGroup
                    .bindFieldsBuffered(lot, this);
            lotName.focus();
        }
        setVisible(lot != null);

    }

    public AddressbookUI getUI() {
        return (AddressbookUI) super.getUI();
    }

    @Override
    public void buttonClick(ClickEvent event) {
        if (event.getButton() == add) {
            try {
                // Commit the fields from UI to DAO
                formFieldBindings.commit();

                // Save DAO to backend with direct synchronous service API
                getUI().service.addLot(lot.getLotName(), lot.getFinishDate(), lot.getStartPrice(), lot.getDescription(), "andrew", State.active, 123);

                try {
                    getUI().refreshContacts();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (FieldGroup.CommitException e) {
                // Validation exceptions could be shown here
            } catch (SchedulerException e) {
                e.printStackTrace();
            } catch (Exception  e) {
            log.error("___________________" + e);
            }
        } else if (event.getButton() == cancel) {
            // Place to call business logic.
            Notification.show("Cancelled", Type.TRAY_NOTIFICATION);

        }

    }


}
