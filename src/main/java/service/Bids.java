package service;

import entity.bid.Bid;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
@WebService
public interface Bids {
    public void addBid(double newPrice, String bidder, int lotId);
    public List<Bid> getAllBidsOnLotByLotId(int lotId);
    public List<Bid> getAllBids();
}
