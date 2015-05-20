package integration;

import entity.lot.Lot;
import entity.lot.Status;

import java.util.Date;
import java.util.List;

/**
 * Created by Andriy on 19.05.2015.
 */
public interface LotDao {

    public void addLot(String lotName, Date finishDate, double startPrice, String description, String owner, Status state, int ownerId);
    public Lot getLotById(int id);
    //public List<Lot> getAllLots();
    public void deleteLot(int id);

}
