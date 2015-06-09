import service.LotService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * Created by Andriy on 08.06.2015.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/auction?wsdl");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://service/", "LotServiceImplService");

        Service service = Service.create(url, qname);

        LotService hello = service.getPort(LotService.class);

        System.out.println(hello.getAllLots());

    }
}
