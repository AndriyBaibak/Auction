package integration;

import entity.lot.Lot;
import java.util.Date;
import java.util.List;

public interface LotDao {

    public void addLot(String lotName, Date finishDate, double startPrice, String description) throws Exception;

    public Lot getLotById(int id);

    public List<Lot> getAllLots();

    public void deleteLot(int id);

    public void canceledLot(int id);


}
