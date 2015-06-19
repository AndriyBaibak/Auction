package integration;

import entity.bid.Bid;

import java.util.List;

public interface BidDao {

    public void addBid(Bid bidOnLot);

    public List<Bid> getAllBidsOnLotByLotId(int lotId);




}
