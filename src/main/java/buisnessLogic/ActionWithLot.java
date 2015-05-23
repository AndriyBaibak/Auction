package buisnessLogic;

import entity.lot.Lot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by Andriy on 20.05.2015.
 */
public interface ActionWithLot {
    public void addLot(String lotName, String finishDate, double startPrice, String description, String owner, String state, int ownerId);
    public Lot getLotById(int id);
    public List<Lot> getAllLots();
    public void deleteLot(int id);
    public void canceledLot(int id);

}
