package integration;

import entity.lot.Lot;
import entity.lot.Status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
public class LotIntegration implements LotDao {

    private static List<Lot> lotList = new ArrayList<Lot>();

    static{lotList.add(new Lot("iphone", new Date(2012-11-11), 123.23, "[eqyz" , "andrew", Status.ACTIVE, 123));}

    @Override
    public void addLot(String lotName, Date finishDate, double startPrice, String description, String owner, Status state, int ownerId) {
        lotList.add(new Lot(lotName, finishDate, startPrice, description, owner, state, ownerId));
    }

    @Override
    public Lot getLotById(int id) {
        return lotList.get(id);
    }

    @Override
    public void deleteLot(int id) {
        lotList.remove(id);
    }
}
