package buisnessLogic;

import entity.lot.Lot;

import java.util.Date;
import java.util.List;

public interface ActionWithLot {
    public void addLot(Lot lotForSaving);

    public Lot getLotById(int id);

    public List<Lot> getAllLots();

    public void deleteLot(int id);

    public void canceledLot(int id);

}
