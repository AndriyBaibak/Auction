package service;

import entity.bid.Bid;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService()
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface BidService {

    @WebMethod
    public void addBid(Bid bidOnLot);

    @WebMethod
    public List<Bid> getAllBidsOnLotByLotId(int lotId);


}
