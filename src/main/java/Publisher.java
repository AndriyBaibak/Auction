import service.BidsImpl;
import service.LotsImpl;
import service.UsersImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by Andriy on 20.05.2015.
 */
public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/auction/lots", new LotsImpl());
        Endpoint.publish("http://localhost:8888/auction/bids", new BidsImpl());
        Endpoint.publish("http://localhost:8888/auction/users", new UsersImpl());
    }

}
