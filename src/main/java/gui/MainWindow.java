package gui;


import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ChameleonTheme;
import entity.bid.Bid;
import entity.lot.Lot;

public class MainWindow extends Window implements Button.ClickListener {

    Grid gridLots = new Grid();
    Grid gridBids = new Grid();
    Table lotsDetails = new Table();
    Button newLot = new Button("New Lot");
    Button newBid = new Button("New Bid");
    Button cancelTrades = new Button("Cancell trades");
    Button logout = new Button("Logout");
    public static String owner;

    public static ServiceBeetwenVaadinAndJaxWs serviceForVaadin;

    Lot lotForDetails;

    public MainWindow(String loginForCurrentUser, String passwordForCurrentUser) {
        serviceForVaadin = new ServiceBeetwenVaadinAndJaxWs(loginForCurrentUser, passwordForCurrentUser);
        configureComponents();
        owner = serviceForVaadin.getUserNameByUserLogin();
        buildLayout();

    }


    private void configureComponents() {
        logout.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                MainUI.getCurrent().logout();
            }
        });
        newLot.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                MainUI.getCurrent().addWindow(new WindowForAddLot());
            }
        });
        cancelTrades.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                serviceForVaadin.canceledLot(lotForDetails.getCode(), owner);
                lotsDetails.removeAllItems();
                refreshLots();
            }
        });
        newBid.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                MainUI.getCurrent().addWindow(new WindowForAddBid(lotForDetails.getCode()));
            }
        });

        gridLots.setContainerDataSource(new BeanItemContainer<Lot>(
                Lot.class));
        gridLots.setColumnOrder("code", "lotName", "finishDate", "state");
        gridLots.removeColumn("owner");
        gridLots.removeColumn("startPrice");
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
                refreshBid(lotForDetails.getCode());
            }
        });
    }

    private void buildLayout() {
        VerticalLayout lots = new VerticalLayout(gridLots, newLot);
        lots.setCaption("Lots");
        lots.setSpacing(true);
        lots.setMargin(true);
        gridLots.setHeight("395px");
        gridLots.setWidth("600px");
        lots.setComponentAlignment(newLot, Alignment.BOTTOM_RIGHT);

        VerticalLayout bids = new VerticalLayout(gridBids, newBid);
        bids.setCaption("Bids");
        bids.setMargin(true);
        bids.setSpacing(true);
        gridBids.setHeight("140px");
        gridBids.setWidth("100%");
        bids.setComponentAlignment(newBid, Alignment.BOTTOM_RIGHT);

        HorizontalLayout details = new HorizontalLayout(lotsDetails, cancelTrades);
        details.setCaption("Lot details");
        details.setMargin(true);
        details.setSpacing(true);
        lotsDetails.setHeight("180px");
        lotsDetails.setWidth("475px");
        details.setComponentAlignment(cancelTrades, Alignment.BOTTOM_RIGHT);

        Panel panelForLots = new Panel("Lots");
        panelForLots.setContent(lots);

        Panel panelForBids = new Panel("Bids");
        panelForBids.setWidth("100%");
        panelForBids.setContent(bids);

        Panel panelFoLotDetails = new Panel("Lot details");
        panelFoLotDetails.setContent(details);


        VerticalLayout rightHalf = new VerticalLayout(panelFoLotDetails, panelForBids);
        rightHalf.setMargin(true);
        rightHalf.setSpacing(true);
        rightHalf.setWidth("665px");

        HorizontalLayout body = new HorizontalLayout(panelForLots, rightHalf);
        body.setComponentAlignment(panelForLots, Alignment.MIDDLE_LEFT);
        body.setSpacing(true);
        body.setMargin(true);

        Label auction = new Label(" Auction");
        auction.setStyleName(ChameleonTheme.LABEL_H2);

        Label labelForUserName = new Label(" User: " + owner);

        HorizontalLayout rightPartOfHead = new HorizontalLayout(labelForUserName, logout);
        rightPartOfHead.setSpacing(true);

        HorizontalLayout horizontalLayout = new HorizontalLayout(auction, rightPartOfHead);
        horizontalLayout.setComponentAlignment(auction, Alignment.TOP_LEFT);
        horizontalLayout.setComponentAlignment(rightPartOfHead, Alignment.TOP_RIGHT);
        horizontalLayout.setWidth("100%");

        VerticalLayout mainLayout = new VerticalLayout(horizontalLayout, body);
        horizontalLayout.setHeight("5px");
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);

        setCaption(" Auction");
        setContent(mainLayout);
        setHeight("100%");
        setWidth("100%");
        center();
    }

    public void refreshLotDetails(Lot lotForDetails) {
        lotsDetails.removeAllItems();
        lotsDetails.addItem(new Object[]{"Code:", lotForDetails.getCode()}, 1);
        lotsDetails.addItem(new Object[]{"Name:", lotForDetails.getLotName()}, 2);
        lotsDetails.addItem(new Object[]{"State:", lotForDetails.getState()}, 3);
        lotsDetails.addItem(new Object[]{"Finish date:", lotForDetails.getFinishDate()}, 4);
        lotsDetails.addItem(new Object[]{"Owner:", lotForDetails.getOwner()}, 5);
        lotsDetails.addItem(new Object[]{"Remaining time:", lotForDetails.getRemainingTime()}, 6);
        lotsDetails.addItem(new Object[]{"Description:", lotForDetails.getDescription()}, 7);
        lotsDetails.addItem(new Object[]{"Start price:", lotForDetails.getStartPrice()}, 8);
    }

    protected void refreshBid(int id) {
        gridBids.removeAllColumns();
        gridBids.setContainerDataSource(new BeanItemContainer<Bid>(Bid.class, serviceForVaadin.getAllBidsOnLotByLotId(id)));
        gridBids.setColumnOrder("newPrice", "dateBid", "bidderName");
        gridBids.removeColumn("lotId");
        gridBids.removeColumn("id");
    }

    public void refreshLots() {
        gridLots.setContainerDataSource(new BeanItemContainer<Lot>(
                Lot.class, serviceForVaadin.getAllLots()));
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }
}
