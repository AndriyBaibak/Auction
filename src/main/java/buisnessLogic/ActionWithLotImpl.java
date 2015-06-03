package buisnessLogic;

import entity.lot.Lot;
import integration.LotIntegration;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

public class ActionWithLotImpl implements ActionWithLot {
    private static Logger log = Logger.getLogger(LotIntegration.class);


    private LotIntegration lotIntegration = new LotIntegration();

    @Override
    public void addLot(String lotName, Date finishDate, double startPrice, String description) {
        try {
            lotIntegration.addLot(lotName, finishDate, startPrice, description);
        } catch (Exception ex) {
            log.error("Exception" + ex);
        }
    }

    @Override
    public Lot getLotById(int id) {
        return lotIntegration.getLotById(id);
    }

    @Override
    public void deleteLot(int id) {
        try {
            lotIntegration.deleteLot(id);
        } catch (Exception ex) {
            log.error("Exception" + ex);
        }
    }

    @Override
    public void canceledLot(int id) {
        try {
            lotIntegration.canceledLot(id);
        } catch (Exception ex) {
            log.error("Exception" + ex);
        }
    }

    @Override
    public List<Lot> getAllLots() {
        return lotIntegration.getAllLots();
    }

}
