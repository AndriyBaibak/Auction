package integration;

import entity.bid.Bid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
public class BidIntegration implements BidDao {
    private List<Bid> bidList = new ArrayList<Bid>();

    @Override
    public void addBid(double newPrice, String bidder, int lotId) {
        bidList.add(new Bid(newPrice, bidder, lotId));
    }
}
