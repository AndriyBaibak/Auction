import service.Lots;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * Created by Andriy on 20.05.2015.
 */
public class LotsClient {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost:8888/auction/lots?wsdl");

        QName qname = new QName("http://entity/", "LotsImplService");

        Service service = Service.create(url, qname);

        Lots getLotById = service.getPort(Lots.class);

            System.out.println(getLotById.getLotById(2));

    }
}
