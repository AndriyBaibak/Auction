package buisnessLogic;

import entity.lot.Lot;
import entity.lot.State;

import java.util.Date;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
public interface ActionWithLot {
    public void addLot(String lotName, Date finishDate, double startPrice, String description, String owner, State state, int ownerId);
    public Lot getLotById(int id);
    public List<Lot> getAllLots();
    public void deleteLot(int id);
    public void canceledLot(int id);

}
