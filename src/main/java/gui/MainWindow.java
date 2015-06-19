package gui;


import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.Runo;
import com.vaadin.ui.themes.ValoTheme;
import entity.bid.Bid;
import entity.lot.Lot;
import org.apache.log4j.Logger;

public class MainWindow extends Window implements Button.ClickListener {

    Grid gridLots = new Grid("Lots");
    Grid gridBids = new Grid("Bids");
    Table lotsDetails = new Table("Lot details");
    Button newLot = new Button("New Lot");
    Button newBid = new Button("New Bid");
    Button cancelTrades = new Button("Cancel trades");
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
                serviceForVaadin.canceledLot(lotForDetails.getCode());
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
        lots.setSpacing(true);
        gridLots.setHeight("535px");
        gridLots.setWidth("600px");
        lots.setComponentAlignment(newLot, Alignment.BOTTOM_RIGHT);

        VerticalLayout bids = new VerticalLayout(gridBids, newBid);
        bids.setSpacing(true);
        gridBids.setHeight("200px");
        gridBids.setWidth("100%");
        bids.setComponentAlignment(newBid, Alignment.BOTTOM_RIGHT);

        HorizontalLayout details = new HorizontalLayout(lotsDetails, cancelTrades);
        details.setStyleName("backColor");
        lotsDetails.setHeight("100%");//todo
        lotsDetails.setWidth("100%");
        details.setHeight("98%");
        details.setWidth("98%");//todo

        details.setComponentAlignment(cancelTrades, Alignment.BOTTOM_RIGHT);


        Panel lotPanel = new Panel();
        Panel bidPanel = new Panel();
        bidPanel.setStyleName("backColor");
        Panel lotDetail = new Panel();
        lotDetail.setStyleName("backColor");


        lotPanel.setWidth("600px");
        lotPanel.setHeight("800px");
        lotPanel.setContent(lots);
        lotPanel.setStyleName(Runo.CSSLAYOUT_SHADOW);//todo


        bidPanel.setHeight("100%");
        bidPanel.setWidth("760px");
        bidPanel.setContent(bids);

        lotDetail.setHeight("100%");
        lotDetail.setWidth("760px");
        lotDetail.setContent(details);

        VerticalLayout rightHalf = new VerticalLayout();
        rightHalf.setWidth("98%");
        rightHalf.addComponent(lotDetail);
        lotDetail.setHeight("330px");
        lotDetail.setStyleName("backColor");//todo

        rightHalf.addComponent(bidPanel);
        bidPanel.setHeight("270px");
        rightHalf.setComponentAlignment(bidPanel, Alignment.BOTTOM_CENTER);


        HorizontalLayout body = new HorizontalLayout();
        body.addComponent(lotPanel);
        body.addComponent(rightHalf);
        lotPanel.setHeight("600px");
        rightHalf.setHeight("600px");

        VerticalLayout mainLayout = new VerticalLayout();

        Label auction = new Label(" Auction");
        auction.setStyleName(ChameleonTheme.LABEL_H1);
        auction.setWidth("70%");
        Label labelForUserName = new Label(" User: " + owner);
        labelForUserName.setWidth("250px");

        HorizontalLayout rightPartOfHead = new HorizontalLayout(labelForUserName, logout);
        rightPartOfHead.setWidth("30%");
        rightPartOfHead.setComponentAlignment(labelForUserName, Alignment.MIDDLE_LEFT);
        rightPartOfHead.setComponentAlignment(logout, Alignment.MIDDLE_RIGHT);
        HorizontalLayout horizontalLayout = new HorizontalLayout(auction, rightPartOfHead);
        horizontalLayout.setHeight("40px");
        System.out.print(horizontalLayout.getHeight());

        horizontalLayout.setComponentAlignment(rightPartOfHead, Alignment.MIDDLE_RIGHT);
        horizontalLayout.setComponentAlignment(auction, Alignment.MIDDLE_LEFT);
        horizontalLayout.setWidth("100%");
        mainLayout.addComponent(horizontalLayout);
        mainLayout.addComponent(body);
        mainLayout.setStyleName("backColor");
        setCaption("Auction");
        setResizable(false);//todo
        setContent(mainLayout);
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
