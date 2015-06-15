import service.LotServiceImpl;
import service.UserServiceImpl;

import javax.xml.ws.Endpoint;


public class Publisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/auction", new LotServiceImpl());
        Endpoint.publish("http://localhost:8080/qwe", new UserServiceImpl());
    }



}



