package service;

import buisnessLogic.ActionWithBidImpl;
import entity.bid.Bid;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
@WebService(endpointInterface = "service.Bids")
public class BidsImpl implements Bids {
    private static Logger log = Logger.getLogger(BidsImpl.class);


    private ActionWithBidImpl actionWithBid = new ActionWithBidImpl();

    @Override
    public void addBid(double newPrice, String bidder, int lotId) {

        try {
            actionWithBid.addBid(newPrice, bidder, lotId);
        }catch(Exception ex){
            log.error("--------------" + ex.toString());
        }

    }

    @Override
    public List<Bid> getAllBidsOnLotByLotId(int lotId) {
        return actionWithBid.getAllBidsOnLotByLotId(lotId);
    }

    @Override
    public List<Bid> getAllBids() {
        return actionWithBid.getAllBids();
    }
}
