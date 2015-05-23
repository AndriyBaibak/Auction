package buisnessLogic;

import entity.lot.Lot;

import integration.LotIntegration;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by Andriy on 20.05.2015.
 */
public class ActionWithLotImpl implements ActionWithLot {

    private LotIntegration lotIntegration = new LotIntegration();

    @Override
    public void addLot(String lotName, String finishDate, double startPrice, String description, String owner, String state, int ownerId) {
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
    public void canceledLot(int id) {
        lotIntegration.canceledLot(id);
    }

    @Override
   public List<Lot> getAllLots(){
        return lotIntegration.getAllLots();
    }
}
