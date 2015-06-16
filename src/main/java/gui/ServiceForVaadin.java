package gui;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import service.LotService;
import service.LotServiceImpl;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

public class ServiceForVaadin {
    private static Logger log = Logger.getLogger(ServiceForVaadin.class);




    public void createRequest() throws Exception {
        URL url = null;
        try {
           url = new URL("http://localhost:8088/lot?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        QName qname = new QName("http://service/","LotServiceImpl");

        Service service = Service.create(url, qname);

        LotService hello = service.getPort(LotService.class);

        try {
            hello.addLot("qwe","22-11-2015", 34, "blabla");
        } catch (ParseException e) {
            log.error(e);
        } catch (SchedulerException e) {
            log.error(e);
        }

        System.out.print(hello.getAllLots().size());
    }
}
