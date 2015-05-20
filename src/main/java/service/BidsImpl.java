package service;

import buisnessLogic.ActionWithBidImpl;
import entity.bid.Bid;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
@WebService(endpointInterface = "service.Bids")
public class BidsImpl implements Bids {

    @Inject
    private ActionWithBidImpl actionWithBid;

    @Override
    public void addBid(double newPrice, String bidder, int lotId) {
       actionWithBid.addBid(newPrice, bidder, lotId);

    }
}
