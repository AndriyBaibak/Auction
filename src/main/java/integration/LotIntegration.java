package integration;

import entity.bid.Bid;
import entity.lot.Lot;

import org.apache.log4j.Logger;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
public class LotIntegration implements LotDao {
    private static Logger log = Logger.getLogger(LotIntegration.class);

    private EntityManager entityManager = Persistence.createEntityManagerFactory("Eclipselink_JPA").createEntityManager();


    private static ArrayList<Lot> lotList = new ArrayList<Lot>();

    @Override
    public void addLot(String lotName, String finishDate, double startPrice, String description, String owner, String state, int ownerId) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(new Lot(lotName, finishDate, startPrice, description, owner, state, ownerId));
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            log.error("+++++++++++++++++ " + ex.toString());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public Lot getLotById(int id) {
        Lot findLot = null;
        try {
            entityManager.getTransaction().begin();
            findLot = entityManager.find(Lot.class, id);
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            log.error("+++++++++++++++++ " + ex.toString());
            entityManager.getTransaction().rollback();
        }
        return findLot;
    }

    @Override
    public void deleteLot(int id) {
        Lot lotForDeleting = getLotById(id);
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(lotForDeleting);
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            log.error("+++++++++++++++++ " + ex.toString());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void canceledLot(int id) {
        Lot lotForCanceled = getLotById(id);
        lotForCanceled.setState("CANCELLED");
        entityManager.getTransaction().begin();
        entityManager.merge(lotForCanceled);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Lot> getAllLots(){
       List<Lot> listOfEmailDomains = new ArrayList<Lot>();
        try {
            entityManager.getTransaction().begin();
            CriteriaQuery<Lot> criteria = entityManager.getCriteriaBuilder().createQuery(Lot.class);
            criteria.select(criteria.from(Lot.class));
            listOfEmailDomains = entityManager.createQuery(criteria).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex){
            log.error("------------------------" + ex);
            entityManager.getTransaction().rollback();
        }
        return listOfEmailDomains;
    }
}
