package service;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by Andriy on 20.05.2015.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Bids {
    void addBid(double newPrice, String bidder, int lotId);
}
