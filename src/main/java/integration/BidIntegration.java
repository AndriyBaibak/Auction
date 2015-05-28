package integration;

import entity.bid.Bid;
import entity.lot.Lot;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */

public class BidIntegration implements BidDao {
    private static Logger log = Logger.getLogger(BidIntegration.class);

    private LotIntegration lot = new LotIntegration();


    private EntityManager entityManager = Persistence.createEntityManagerFactory("Eclipselink_JPA").createEntityManager();

    @Override
    public void addBid(double newPrice, String bidder, int lotId)throws Exception{
              Lot lotForBid = lot.getLotById(lotId);
          //  String lotOwner = lotForBid.getOwner();
           // if((newPrice > getLastBiggestPriceWhichBid(lotId))&(bidder!=lotOwner)&(lotForBid.getState()== Status.active)) {
                entityManager.getTransaction().begin();
                entityManager.persist(new Bid(newPrice, bidder, lotId));
                entityManager.getTransaction().commit();
          //  }else{throw new Exception("Ставку зробити не можливо");}
    }

    @Override
    public List<Bid> getAllBidsOnLotByLotId(int lotId) {
        List<Bid> listOfBidsSomeLot = new ArrayList<Bid>();
        Query query = entityManager.createNativeQuery("SELECT id, bidderName, dateBid, newPrice FROM bid WHERE lotId = " + lotId + ";", Bid.class);
        listOfBidsSomeLot = query.getResultList();
        return  listOfBidsSomeLot;
    }

    @Override
    public List<Bid> getAllBids() {
        List<Bid> listOfAllBid = new ArrayList<Bid>();
        Query query = entityManager.createNativeQuery("SELECT id, bidderName, dateBid, newPrice FROM bid ;", Bid.class);
        listOfAllBid = query.getResultList();
        return  listOfAllBid;
    }

    private double getLastBiggestPriceWhichBid(int lotId){
        return (Double)entityManager.createNativeQuery("SELECT MAX(newPrice) FROM bid where lotId = " + lotId + ";").getSingleResult();

    }

}
