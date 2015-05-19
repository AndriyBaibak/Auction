package entity;

import java.util.List;

/**
 * Created by Andriy on 19.05.2015.
 */
public interface LotDao {

    public void createLot();
    public Lot getLot();
    public List<Lot> getAllLots();
    public void deleteLot();

}
