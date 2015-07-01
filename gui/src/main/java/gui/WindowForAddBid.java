package gui;

import com.vaadin.ui.*;
import entity.bid.Bid;
import org.apache.log4j.Logger;

public class WindowForAddBid extends Window implements Button.ClickListener {
    private static Logger log = Logger.getLogger(WindowForAddBid.class);

    Button ok = new Button("Ok");
    TextField textFieldForBid = new TextField();
    Label $ = new Label("$");
    int idLotForbid;

    public WindowForAddBid(int idLotForbid) {
        configureComponents();
        buildBidSubWindow();
        this.idLotForbid = idLotForbid;
    }

    private void configureComponents() {

        ok.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Double newPrice = Double.parseDouble(textFieldForBid.getValue());
                Bid bidOnLot = new Bid(newPrice, MainWindow.owner, idLotForbid);
                MainWindow.serviceForVaadin
                        .addBid(bidOnLot);
                MainUI.getMainWindow().refreshBid(idLotForbid);
            }
        });
    }

    public void buildBidSubWindow() {
        HorizontalLayout main = new HorizontalLayout();
        main.addComponent(new Label("Bid: "));
        main.addComponent(textFieldForBid);
        main.addComponent($);
        main.addComponent(ok);
        main.setSpacing(true);
        main.setMargin(true);
        main.setHeight("15%");

        setWidth("300px");
        setHeight("105px");
        setCaption("New Bid");
        setContent(main);
        center();
    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
    }


}
