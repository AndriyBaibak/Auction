package service;

import buisnessLogic.ActionWithBidImpl;
import entity.bid.Bid;

import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "BidServiceImpl",
        portName="EntityPort",
        endpointInterface = "service.BidService")
public class BidServiceImpl implements BidService {

    private ActionWithBidImpl actionWithBid = new ActionWithBidImpl();

    @Override
    public void addBid(Bid bidOnLot) {
            actionWithBid.addBid(bidOnLot);
      }

    @Override
    public List<Bid> getAllBidsOnLotByLotId(int lotId) {
        return actionWithBid.getAllBidsOnLotByLotId(lotId);
    }



}
