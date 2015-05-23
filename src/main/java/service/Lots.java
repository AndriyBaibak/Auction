package service;


import entity.lot.Lot;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by Andriy on 20.05.2015.
 */
@WebService
public interface Lots {

    @WebMethod
    Lot getLotById(int id);
    @WebMethod
    void addLot(String lotName, String finishDate, double startPrice, String description, String owner, String state, int ownerId) throws ParseException, SchedulerException;
    @WebMethod
    void deleteLot(int id);

    public List<Lot> getAllLots() throws Exception;

    @WebMethod
    public void canceledLot(int id);


}
