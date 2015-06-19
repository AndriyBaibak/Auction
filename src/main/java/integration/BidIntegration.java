package integration;

import entity.bid.Bid;
import entity.lot.Lot;
import entity.lot.State;
import integration.jpa.EntityManagerUtil;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class BidIntegration implements BidDao {

    private static Logger log = Logger.getLogger(BidIntegration.class);

    private LotIntegration lotIntegration = new LotIntegration();

    private EntityManager entityManager = EntityManagerUtil.getEntityManager();

    @Override
    public void addBid(Bid bidOnLot) {
        Lot lotForBid = lotIntegration.getLotById(bidOnLot.getLotId());
        try {
            if (isNewPriceBiggest(bidOnLot.getLotId(), bidOnLot.getNewPrice()) & (lotForBid.getState() == State.active) & isNewPriceBiggestFroStartPrice(bidOnLot.getLotId(), bidOnLot.getNewPrice()) & (!(getOwnerOfThisLot(bidOnLot.getLotId()).equals(bidOnLot.getBidderName())))) {
                entityManager.getTransaction().begin();
                entityManager.persist(bidOnLot);
                entityManager.getTransaction().commit();
            } else {
                throw new Exception("Cannot create bid");
            }
        } catch (Exception ex) {
            log.error("Exception" + ex);
            entityManager.getTransaction().rollback();
        }
    }


    @Override
    public List<Bid> getAllBidsOnLotByLotId(int lotId) {
        List<Bid> listOfBidsSomeLot = null;
        Query query = entityManager.createNativeQuery("SELECT id, bidderName, dateBid, newPrice, lotId FROM bid WHERE lotId = " + lotId + ";", Bid.class);
        listOfBidsSomeLot = query.getResultList();
        return listOfBidsSomeLot;
    }

    private String getOwnerOfThisLot(int lotId) {
        String ownerOfCurrentLot = (String) entityManager.createNativeQuery("SELECT owner FROM lot where id = " + lotId + ";").getSingleResult();
        return ownerOfCurrentLot;
    }

    private boolean isNewPriceBiggestFroStartPrice(int lotId, double newPrice) {
        Double startPrice = (Double) entityManager.createNativeQuery("SELECT startPrice FROM lot where id = " + lotId + ";").getSingleResult();
        return (newPrice > startPrice);
    }

    private boolean isNewPriceBiggest(int lotId, double newPrice) {
        Double lastPrice = (Double) entityManager.createNativeQuery("SELECT MAX(newPrice) FROM bid where lotId = " + lotId + ";").getSingleResult();
        if (lastPrice == null) {
            return true;
        }
        return (newPrice > lastPrice);
    }

}
