package gui;

import entity.bid.Bid;
import entity.lot.Lot;
import entity.user.User;
import org.apache.log4j.Logger;
import service.BidService;
import service.LotService;
import service.UserService;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceBeetwenVaadinAndJaxWs {
    private static Logger log = Logger.getLogger(ServiceBeetwenVaadinAndJaxWs.class);
    private String login = null;
    private String password = null;

    private LotService lotService = null;
    private BidService bidService = null;
    private UserService userService = null;


    public ServiceBeetwenVaadinAndJaxWs(String login, String password) {
        this.login = login;
        this.password = password;

        createBidService();
        createLotService();
        createUserService();
    }

    private void createLotService() {
        URL urlForLotWsdl = null;
        try {
            urlForLotWsdl = new URL("http://localhost:8080/auction/lot?wsdl");
        } catch (MalformedURLException e) {
            log.error(e);
        }
        QName qnameForLot = new QName("http://service/", "LotServiceImpl");
        Service serviceForLot = Service.create(urlForLotWsdl, qnameForLot);
        lotService = serviceForLot.getPort(LotService.class);
        addUserDataToSoapHeaders(lotService, "http://localhost:8080/auction/lot?wsdl");

    }

    private void createBidService() {
        URL urlForBidWsdl = null;
        try {
            urlForBidWsdl = new URL("http://localhost:8080/auction/bid?wsdl");
        } catch (MalformedURLException e) {
            log.error(e);
        }
        QName qnameForBid = new QName("http://service/", "BidServiceImpl");
        Service serviceForBid = Service.create(urlForBidWsdl, qnameForBid);
        bidService = serviceForBid.getPort(BidService.class);
        addUserDataToSoapHeaders(bidService, "http://localhost:8080/auction/bid?wsdl");
    }

    private void createUserService() {
        URL urlForUserWsdl = null;
        try {
            urlForUserWsdl = new URL("http://localhost:8080/auction/user?wsdl");
        } catch (MalformedURLException e) {
            log.error(e);
        }
        QName qnameForUser = new QName("http://service/", "UserServiceImpl");
        Service serviceForUser = Service.create(urlForUserWsdl, qnameForUser);
        userService = serviceForUser.getPort(UserService.class);
    }

    protected void addLot(Lot lotForSaving) throws Exception {
        lotService.addLot(lotForSaving);

    }

    protected Lot getLotById(int id) {
        return lotService.getLotById(id);
    }

    protected void deleteLot(int id) {
        lotService.deleteLot(id);
    }

    protected List<Lot> getAllLots() {
        return lotService.getAllLots();
    }

    protected void canceledLot(int id, String owner) {
        lotService.canceledLot(id, owner);
    }

    protected void registration(User user) {
        userService.registration(user);
    }

    protected String getUserPasswordByLogin(String login) {
        return userService.getUserPasswordByLogin(login);
    }

    protected void addBid(Bid bidOnLot) {
        bidService.addBid(bidOnLot);
    }

    protected List<Bid> getAllBidsOnLotByLotId(int lotId) {
        return bidService.getAllBidsOnLotByLotId(lotId);
    }

    protected String getUserNameByUserLogin() {
        return userService.getUserNameByUserLogin(login);
    }

    private void addUserDataToSoapHeaders(Object someService, String urlForWsdl) {
        try {
            Map<String, Object> req_ctx = ((BindingProvider) someService).getRequestContext();
            req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, urlForWsdl);

            Map<String, List<String>> headers = new HashMap<String, List<String>>();
            headers.put("Login", Collections.singletonList(login));
            headers.put("Password", Collections.singletonList(password));
            req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}