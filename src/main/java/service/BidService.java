package service;

import entity.bid.Bid;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Andriy on 26.05.2015.
 */
@WebService
public interface BidService {
    @WebMethod
    public void addBid(double newPrice, String bidder, int lotId);

    @WebMethod
    public List<Bid> getAllBidsOnLotByLotId(int lotId);

    @WebMethod
    public List<Bid> getAllBids();

}
