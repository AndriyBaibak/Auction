package gui;

import com.vaadin.ui.*;
import org.apache.log4j.Logger;
import service.BidService;
import service.BidServiceImpl;

public class AddBidForm extends Window implements Button.ClickListener {
    private static Logger log = Logger.getLogger(AddBidForm.class);

    Window addBidSubWindow = new Window();
    Button ok = new Button("Ok");
    TextField textFieldForBid = new TextField("Bid");
    Label $ = new Label("$");
    BidService bidService = new BidServiceImpl();
    int idLotForbid;

    public AddBidForm() {
        configureComponents();
        buildBidSubWindow();
    }

    private void configureComponents() {

        ok.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                bidService.addBid(Double.parseDouble(textFieldForBid.getValue()), "andrew", idLotForbid);

            }
        });
    }

    public void buildBidSubWindow() {
        HorizontalLayout bid = new HorizontalLayout(ok, textFieldForBid, $);
        bid.setComponentAlignment(ok, Alignment.MIDDLE_RIGHT);
        bid.setComponentAlignment(textFieldForBid, Alignment.MIDDLE_CENTER);
        bid.setComponentAlignment($, Alignment.MIDDLE_LEFT);
        addBidSubWindow.setContent(bid);
        addBidSubWindow.center();
    }

    public MainUI getUI() {
        return (MainUI) super.getUI();
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
    }

    public Window getAddBidSubWindow(int lotId) {
        this.idLotForbid = lotId;
        return addBidSubWindow;
    }

}
