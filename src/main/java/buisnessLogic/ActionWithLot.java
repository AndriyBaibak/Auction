package buisnessLogic;

import entity.lot.Lot;
import entity.lot.Status;

import java.util.Date;

/**
 * Created by Andriy on 20.05.2015.
 */
public interface ActionWithLot {
    public void addLot(String lotName, Date finishDate, double startPrice, String description, String owner, Status state, int ownerId);
    public Lot getLotById(int id);
    //public List<Lot> getAllLots();
    public void deleteLot(int id);
}
