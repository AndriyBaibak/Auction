package service;

import entity.lot.Lot;
import org.quartz.SchedulerException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.text.ParseException;
import java.util.List;

@WebService()
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface LotService {
    @WebMethod
    public Lot getLotById(int id);

    @WebMethod
    public void addLot(Lot lotForSaving) throws ParseException, SchedulerException;

    @WebMethod
    public void deleteLot(int id);

    @WebMethod
    public List<Lot> getAllLots() throws Exception;

    @WebMethod
    public void canceledLot(int id);

}
