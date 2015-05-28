package gui;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import entity.lot.Lot;
import service.LotService;
import service.LotServiceImpl;

import javax.servlet.annotation.WebServlet;

@Title("Auction")
@Theme("valo")
public class AddressbookUI extends UI {


    TextField filter = new TextField();
    Grid table = new Grid("Lots");
    Button newLot = new Button("New Lot");


    ContactForm contactForm = new ContactForm();

    LotService service = new LotServiceImpl();


    @Override
    protected void init(VaadinRequest request) {
        try {
            configureComponents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        buildLayout();
    }

    private void configureComponents() throws Exception {

        newLot.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                contactForm.edit(new Lot());
            }
        });


        table.setContainerDataSource(new BeanItemContainer<Lot>(
                Lot.class));
        table.setColumnOrder("id", "lotName", "finishDate", "state");
        table.removeColumn("owner");
        table.removeColumn("startPrice");
        table.removeColumn("ownerId");
        table.removeColumn("description");

        refreshContacts();
    }


    private void buildLayout() {
        VerticalLayout left = new VerticalLayout(table, newLot);
        left.setWidth("50%");
        table.setWidth("100%");
        left.setExpandRatio(table, 1);
        left.setComponentAlignment(newLot, Alignment.BOTTOM_RIGHT);

        HorizontalLayout mainLayout = new HorizontalLayout(left, contactForm);
        mainLayout.setComponentAlignment(contactForm, Alignment.MIDDLE_LEFT);
        mainLayout.setExpandRatio(left, 1);

        setContent(mainLayout);
    }

    void refreshContacts()throws Exception {
        refreshContacts(filter.getValue());
    }

    private void refreshContacts(String stringFilter)throws Exception {
        table.setContainerDataSource(new BeanItemContainer<Lot>(
                Lot.class, service.getAllLots()));
        contactForm.setVisible(false);
    }


    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = AddressbookUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
