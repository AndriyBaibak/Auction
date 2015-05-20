package buisnessLogic;

import integration.BidIntegration;

import javax.inject.Inject;

/**
 * Created by Andriy on 21.05.2015.
 */
public class ActionWithBidImpl implements ActionWithBid {
    @Inject
    private BidIntegration bidIntegration;

    @Override
    public void addBid(double newPrice, String bidder, int lotId) {
        bidIntegration.addBid(newPrice, bidder, lotId);
    }
}
