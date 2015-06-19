package gui;

import com.vaadin.ui.*;
import entity.bid.Bid;
import org.apache.log4j.Logger;

public class WindowForAddBid extends Window implements Button.ClickListener {
    private static Logger log = Logger.getLogger(WindowForAddBid.class);

    Button ok = new Button("Ok");
    TextField textFieldForBid = new TextField("Bid");
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
        HorizontalLayout bid = new HorizontalLayout(ok);
        HorizontalLayout horizontalLayout = new HorizontalLayout(textFieldForBid);
        HorizontalLayout horizontalLayout1 = new HorizontalLayout($);
        horizontalLayout.setMargin(true);
        HorizontalLayout main = new HorizontalLayout();
        main.addComponent(bid);
        main.addComponent(textFieldForBid);
        main.addComponent($);
        /*bid.setComponentAlignment(ok, Alignment.MIDDLE_RIGHT);
        bid.setComponentAlignment(textFieldForBid, Alignment.MIDDLE_CENTER);
        bid.setComponentAlignment($, Alignment.MIDDLE_LEFT);*/
        main.setMargin(true);
        setHeight("25%");
        setWidth("35%");
        setContent(main);
        center();
    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
    }


}
