package buisnessLogic;

import entity.bid.Bid;
import integration.BidIntegration;

import java.util.List;

public class ActionWithBidImpl implements ActionWithBid {

    private BidIntegration bidIntegration = new BidIntegration();

    @Override
    public void addBid(Bid bidOnLot) throws Exception {
        bidIntegration.addBid(bidOnLot);
    }

    @Override
    public List<Bid> getAllBidsOnLotByLotId(int lotId) {
        return bidIntegration.getAllBidsOnLotByLotId(lotId);
    }



}
