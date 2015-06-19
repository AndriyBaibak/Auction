package service;

import buisnessLogic.ActionWithLotImpl;
import entity.lot.Lot;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "LotServiceImpl",
        portName="EntityPort",
        endpointInterface = "service.LotService")
public class LotServiceImpl implements LotService {

    private ActionWithLotImpl actionWithLot = new ActionWithLotImpl();

    @Override
    public Lot getLotById(int id) {
        return actionWithLot.getLotById(id);
    }

    @Override
    public void addLot(Lot lotForSaving)  {
                actionWithLot.addLot(lotForSaving);
    }

    @Override
    public void deleteLot(int id) {
            actionWithLot.deleteLot(id);
    }

    @Override
    public List<Lot> getAllLots() {
        return actionWithLot.getAllLots();
    }

    @Override
    public void canceledLot(int id) {
            actionWithLot.canceledLot(id);
    }
}
