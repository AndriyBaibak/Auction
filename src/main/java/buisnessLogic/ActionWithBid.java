package buisnessLogic;

import entity.bid.Bid;

import java.util.List;

public interface ActionWithBid {

    public void addBid(Bid bidOnLot);

    public List<Bid> getAllBidsOnLotByLotId(int lotId);



}
