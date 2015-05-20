package integration;

/**
 * Created by Andriy on 20.05.2015.
 */
public interface BidDao {

    public void addBid(double newPrice, String bidder, int lotId);

}
