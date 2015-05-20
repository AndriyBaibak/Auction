package buisnessLogic;

import entity.lot.Lot;
import entity.lot.Status;
import integration.LotIntegration;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by Andriy on 20.05.2015.
 */
public class ActionWithLotImpl implements ActionWithLot {
    @Inject
    private LotIntegration lotIntegration;

    @Override
    public void addLot(String lotName, Date finishDate, double startPrice, String description, String owner, Status state, int ownerId) {
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
}
