package buisnessLogic;

import entity.lot.Lot;
import entity.lot.Status;
import integration.LotIntegration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
public class ActionWithLotImpl implements ActionWithLot {

    private LotIntegration lotIntegration = new LotIntegration();

    @Override
    public void addLot(String lotName, String finishDate, double startPrice, String description, String owner, Status state, int ownerId) {
        lotIntegration.addLot(lotName, finishDate, startPrice, description, owner, state, ownerId);
    }

    @Override
    public Lot getLotById(int id) {
        return lotIntegration.getLotById(id);
    }

    @Override
    public void deleteLot(int id) {
        lotIntegration.deleteLot(id);
    }

    @Override
   public ArrayList<Lot> getAllLots(){
        return lotIntegration.getAllLots();
    }
}
