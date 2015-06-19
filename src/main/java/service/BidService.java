package service;

import entity.bid.Bid;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface BidService {

    @WebMethod
    public void addBid(Bid bidOnLot);

    @WebMethod
    public List<Bid> getAllBidsOnLotByLotId(int lotId);


}
