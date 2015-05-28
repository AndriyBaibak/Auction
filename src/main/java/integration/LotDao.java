package integration;

import entity.lot.Lot;
import entity.lot.State;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by Andriy on 19.05.2015.
 */
public interface LotDao {

    public void addLot(String lotName, Date finishDate, double startPrice, String description, String owner, State state, int ownerId);
    public Lot getLotById(int id);
    public List<Lot> getAllLots();
    public void deleteLot(int id);
    public void canceledLot(int id);


}
