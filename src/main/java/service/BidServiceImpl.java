package service;

import buisnessLogic.ActionWithBidImpl;
import entity.bid.Bid;
import org.apache.log4j.Logger;

import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "BidServiceImpl",
        portName="EntityPort",
        endpointInterface = "service.BidService")
public class BidServiceImpl implements BidService {
    private static Logger log = Logger.getLogger(BidServiceImpl.class);

    private ActionWithBidImpl actionWithBid = new ActionWithBidImpl();

    @Override
    public void addBid(Bid bidOnLot) {
        try {
            actionWithBid.addBid(bidOnLot);
        } catch (Exception ex) {
            log.error("Exception" + ex);
        }

    }

    @Override
    public List<Bid> getAllBidsOnLotByLotId(int lotId) {
        return actionWithBid.getAllBidsOnLotByLotId(lotId);
    }



}
