package buisnessLogic;

import entity.bid.Bid;

import java.util.List;

/**
 * Created by Andriy on 21.05.2015.
 */
public interface ActionWithBid {

    public void addBid(double newPrice, String bidder, int lotId) throws Exception;

    public List<Bid> getAllBidsOnLotByLotId(int lotId);

    public List<Bid> getAllBids();



}
