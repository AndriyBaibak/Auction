package service;

import entity.lot.Lot;
import org.quartz.SchedulerException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by Andriy on 26.05.2015.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface LotService {
    @WebMethod
    public Lot getLotById(int id);

    @WebMethod
    public void addLot(String lotName, Date finishDate, double startPrice, String description) throws ParseException, SchedulerException;

    @WebMethod
    public void deleteLot(int id);

    @WebMethod
    public List<Lot> getAllLots() throws Exception;

    @WebMethod
    public void canceledLot(int id);

}
