package integration;

import entity.bid.Bid;

import java.util.List;

public interface BidDao {

    public void addBid(double newPrice, String bidder, int lotId) throws Exception;

    public List<Bid> getAllBidsOnLotByLotId(int lotId);

    public List<Bid> getAllBids();


}
