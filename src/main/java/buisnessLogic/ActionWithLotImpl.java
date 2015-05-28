package buisnessLogic;

import entity.lot.Lot;

import entity.lot.State;
import integration.LotIntegration;

import java.util.Date;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
public class ActionWithLotImpl implements ActionWithLot {

    private LotIntegration lotIntegration = new LotIntegration();

    @Override
    public void addLot(String lotName, Date finishDate, double startPrice, String description, String owner, State state, int ownerId) {
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
