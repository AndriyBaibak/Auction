package service;

import entity.lot.Status;
import entity.lot.Lot;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Date;

/**
 * Created by Andriy on 20.05.2015.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Lots {
    @WebMethod
    Lot getLotById(int id);
    @WebMethod
    void addLot(String lotName, Date finishDate, double startPrice, String description, String owner, Status state, int ownerId);
    @WebMethod
    void deleteLot(int id);
}
