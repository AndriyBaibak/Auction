import entity.EntityManagerUtil;
import entity.Lot;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Andriy on 19.05.2015.
 */
public class IntegrationLot implements entity.LotDao {

    private EntityManager entityManager = EntityManagerUtil.getEntityManager();

    @Override
    public void createLot() {

    }

    @Override
    public Lot getLot() {
        return null;
    }

    @Override
    public List<Lot> getAllLots() {
        return null;
    }

    @Override
    public void deleteLot() {

    }
}
