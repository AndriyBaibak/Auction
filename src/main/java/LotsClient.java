import org.apache.log4j.Logger;
import service.Lots;
import service.LotsImpl;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * Created by Andriy on 20.05.2015.
 */
public class LotsClient {     private static Logger log = Logger.getLogger(LotsClient.class);


    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost:8888/auction/lots?wsdl");

        QName qname = new QName("http://service/", "LotsImplService");

        Service service = Service.create(url, qname);

        Lots lots=  service.getPort(Lots.class);

            System.out.println(lots.getLotById(0));

    }
}
