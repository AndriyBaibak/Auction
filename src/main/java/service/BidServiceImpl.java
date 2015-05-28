package service;

import buisnessLogic.ActionWithBidImpl;
import entity.bid.Bid;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Andriy on 26.05.2015.
 */
public class BidServiceImpl implements BidService{
    private static Logger log = Logger.getLogger(BidServiceImpl.class);

    private ActionWithBidImpl actionWithBid = new ActionWithBidImpl();
        @Override
        public void addBid(double newPrice, String bidder, int lotId) {
            try {
                actionWithBid.addBid(newPrice, bidder, lotId);
            } catch (Exception ex) {
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
