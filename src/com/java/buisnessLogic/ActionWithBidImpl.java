package buisnessLogic;

import entity.bid.Bid;
import integration.BidIntegration;

import java.util.List;

public class ActionWithBidImpl implements ActionWithBid {

    private BidIntegration bidIntegration = new BidIntegration();

    @Override
    public void addBid(double newPrice, String bidder, int lotId) throws Exception {
        bidIntegration.addBid(newPrice, bidder, lotId);
    }

    @Override
    public List<Bid> getAllBidsOnLotByLotId(int lotId) {
        return bidIntegration.getAllBidsOnLotByLotId(lotId);
    }

    @Override
    public List<Bid> getAllBids() {
        return bidIntegration.getAllBids();
    }


}
