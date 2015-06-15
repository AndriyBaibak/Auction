package integration;

import entity.bid.Bid;
import entity.lot.Lot;
import entity.lot.State;
import integration.jpa.EntityManagerUtil;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class BidIntegration implements BidDao {
    private static Logger log = Logger.getLogger(BidIntegration.class);


    private LotIntegration lotIntegration = new LotIntegration();


    private EntityManager entityManager= EntityManagerUtil.getEntityManager();

    @Override
    public void addBid(double newPrice, String bidder, int lotId) throws Exception {
        Lot lotForBid = lotIntegration.getLotById(lotId);
        try {
            if (getLastBiggestPriceWhichBid(lotId, newPrice) & (lotForBid.getState() == State.active)) {
                entityManager.getTransaction().begin();
                entityManager.persist(new Bid(newPrice, bidder, lotId));
                entityManager.getTransaction().commit();
            } else {
                throw new Exception("Cannot create bid");
            }
        }catch (Exception ex){
            log.error("Exception" + ex);
            entityManager.getTransaction().rollback();
        }
    }


    @Override
    public List<Bid> getAllBidsOnLotByLotId(int lotId) {
        List<Bid> listOfBidsSomeLot = new ArrayList<Bid>();
        Query query = entityManager.createNativeQuery("SELECT id, bidderName, dateBid, newPrice, lotId FROM bid WHERE lotId = " + lotId + ";", Bid.class);
        listOfBidsSomeLot = query.getResultList();
        return listOfBidsSomeLot;
    }

    @Override
    public List<Bid> getAllBids() {
        List<Bid> listOfAllBid = new ArrayList<Bid>();
        Query query = entityManager.createNativeQuery("SELECT id, bidderName, dateBid, newPrice FROM bid ;", Bid.class);
        listOfAllBid = query.getResultList();
        return listOfAllBid;
    }

    private boolean getLastBiggestPriceWhichBid(int lotId, double newPrice) {
        Double lastPrice = (Double) entityManager.createNativeQuery("SELECT MAX(newPrice) FROM bid where lotId = " + lotId + ";").getSingleResult();
        if (lastPrice == null) {
            return true;
        }
        return (newPrice > lastPrice);
    }

}
