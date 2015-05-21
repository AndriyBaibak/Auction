package integration;

import entity.lot.Lot;
import entity.lot.Status;
import org.apache.log4j.Logger;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
public class LotIntegration implements LotDao {
    private static Logger log = Logger.getLogger(LotIntegration.class);

    private static ArrayList<Lot> lotList = new ArrayList<Lot>();

    @Override
    public void addLot(String lotName, String finishDate, double startPrice, String description, String owner, Status state, int ownerId) {
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

    @Override
    public ArrayList<Lot> getAllLots(){
        return lotList;
    }
}
