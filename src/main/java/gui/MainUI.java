package gui;


import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import entity.bid.Bid;
import entity.lot.Lot;
import org.apache.log4j.Logger;
import service.BidService;
import service.BidServiceImpl;
import service.LotService;
import service.LotServiceImpl;

import javax.servlet.annotation.WebServlet;
//@HandlerChain(file = "handler-inject-chain.xml")
public class MainUI extends UI {


    private static Logger log = Logger.getLogger(MainUI.class);
    Grid gridLots = new Grid("Lots");
    Grid gridBids = new Grid("Bids");
    Table lotsDetails = new Table("Lot details");
    Button newLot = new Button("New Lot");
    Button newBid = new Button("New Bid");
    Button cancelTrades = new Button("Cancel trades");
    LotService lotService = new LotServiceImpl();
    BidService bidService = new BidServiceImpl();
    AddLotForm addLorForm = new AddLotForm();
    AddBidForm addBidForm = new AddBidForm();
    LoginWindow loginWindow = new LoginWindow();

    Lot lotForDetails;

    @Override
    protected void init(VaadinRequest request) {
       // setContent(new LoginWindow());
        try {
            configureComponents();
        } catch (Exception e) {
            log.error("Exception " + e);
        }
        buildLayout();
    }

    private void configureComponents() {

        newLot.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getCurrent().addWindow(addLorForm.getAddLotSubWindow());

            }
        });
        cancelTrades.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                lotService.canceledLot(lotForDetails.getId());
            }
        });
        newBid.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().addWindow(addBidForm.getAddBidSubWindow(lotForDetails.getId()));
            }
        });

        gridLots.setContainerDataSource(new BeanItemContainer<Lot>(
                Lot.class));
        gridLots.setColumnOrder("id", "lotName", "finishDate", "state");
        gridLots.removeColumn("owner");
        gridLots.removeColumn("startPrice");
        gridLots.removeColumn("ownerId");
        gridLots.removeColumn("description");
        gridLots.removeColumn("remainingTime");
        gridLots.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridLots.addSelectionListener(new SelectionEvent.SelectionListener() {
            @Override
            public void select(SelectionEvent selectionEvent) {
                lotForDetails = (Lot) gridLots.getSelectedRow();
                lotsDetails.addContainerProperty("parameter", String.class, null);
                lotsDetails.addContainerProperty("value", Object.class, null);
                lotsDetails.setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
                refreshLotDetails(lotForDetails);
            }
        });

        try {
            refreshLots();
        } catch (Exception e) {
            log.error("Exception" + e);
        }
    }

    private void buildLayout() {
        VerticalLayout lots = new VerticalLayout(gridLots, newLot);
        gridLots.setHeight("535px");
        gridLots.setWidth("600px");
        lots.setComponentAlignment(newLot, Alignment.BOTTOM_RIGHT);

        VerticalLayout bids = new VerticalLayout(gridBids, newBid);
        gridBids.setHeight("200px");
        gridBids.setWidth("100%");
        bids.setComponentAlignment(newBid, Alignment.BOTTOM_RIGHT);

        HorizontalLayout details = new HorizontalLayout(lotsDetails, cancelTrades);
        lotsDetails.setHeight("310px");
        lotsDetails.setWidth("600px");
        details.setHeight("100%");
        details.setWidth("100%");

        details.setComponentAlignment(cancelTrades, Alignment.BOTTOM_RIGHT);

        Panel head = new Panel("Auction");
        Panel lotPanel = new Panel();
        Panel bidPanel = new Panel();
        Panel lotDetail = new Panel();

        head.setWidth("100%");
        head.setHeight("40px");

        lotPanel.setWidth("600px");
        lotPanel.setHeight("800px");
        lotPanel.setContent(lots);

        bidPanel.setHeight("100%");
        bidPanel.setWidth("760px");
        bidPanel.setContent(bids);

        lotDetail.setHeight("100%");
        lotDetail.setWidth("760px");
        lotDetail.setContent(details);

        VerticalLayout rightHalf = new VerticalLayout();
        rightHalf.setWidth("100%");
        rightHalf.addComponent(lotDetail);
        lotDetail.setHeight("330px");

        rightHalf.addComponent(bidPanel);
        bidPanel.setHeight("270px");
        rightHalf.setComponentAlignment(bidPanel, Alignment.BOTTOM_CENTER);


        HorizontalLayout body = new HorizontalLayout();
        body.addComponent(lotPanel);
        body.addComponent(rightHalf);
        lotPanel.setHeight("600px");
        rightHalf.setHeight("600px");

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.addComponent(head);
        mainLayout.addComponent(body);
        setContent(mainLayout);

        getCurrent().addWindow(loginWindow.getAddFormWindow());


    }

    public void refreshLotDetails(Lot lotForDetails) {
        lotsDetails.removeAllItems();
        lotsDetails.addItem(new Object[]{"Code:", lotForDetails.getId()}, 1);
        lotsDetails.addItem(new Object[]{"Name:", lotForDetails.getLotName()}, 2);
        lotsDetails.addItem(new Object[]{"State:", lotForDetails.getState()}, 3);
        lotsDetails.addItem(new Object[]{"Finish date:", lotForDetails.getFinishDate()}, 4);
        lotsDetails.addItem(new Object[]{"Owner:", lotForDetails.getOwner()}, 5);
        lotsDetails.addItem(new Object[]{"Remaining time:", lotForDetails.getRemainingTime()}, 6);
        lotsDetails.addItem(new Object[]{"Description:", lotForDetails.getDescription()}, 7);
        lotsDetails.addItem(new Object[]{"Start price:", lotForDetails.getStartPrice()}, 8);


        gridBids.removeAllColumns();
        gridBids.setContainerDataSource(new BeanItemContainer<Bid>(Bid.class, bidService.getAllBidsOnLotByLotId(lotForDetails.getId())));
        gridBids.setColumnOrder("newPrice", "dateBid", "bidderName");
        gridBids.removeColumn("lotId");
        gridBids.removeColumn("id");
    }

    public void refreshLots() throws Exception {
        gridLots.setContainerDataSource(new BeanItemContainer<Lot>(
                Lot.class, lotService.getAllLots()));

    }
        public static MainUI getCurrent(){
        return  (MainUI) UI.getCurrent();
    }

   @WebServlet(urlPatterns = {"/*" , "/VAADIN/*"})
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}