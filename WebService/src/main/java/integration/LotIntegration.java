package integration;

import entity.lot.Lot;
import entity.lot.State;
import integration.jpa.EntityManagerUtil;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class LotIntegration implements LotDao {

    private static Logger log = Logger.getLogger(LotIntegration.class);

    private EntityManager entityManager = EntityManagerUtil.getEntityManager();

    @Override
    public void addLot(Lot lotForSaving){
       try {
            entityManager.getTransaction().begin();
            entityManager.persist(lotForSaving);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Exception" + ex);
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public Lot getLotById(int id) {
        Lot findLot = null;
        try {
            entityManager.getTransaction().begin();
            findLot = (Lot) entityManager.getReference(Lot.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Exception" + ex);
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
        } catch (Exception ex) {
            log.error("Exception" + ex);
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void canceledLot(int id, String owner) {
        Lot lotForCanceled = getLotById(id);
        if(lotForCanceled.getOwner().equals(owner)) {
            lotForCanceled.setState(State.Cancelled);
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(lotForCanceled);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            log.error("Exception" + e);
        }
    }

    @Override
    public List<Lot> getAllLots() {
        List<Lot> listOfEmailDomains = null;
       try {
            entityManager.getTransaction().begin();
            CriteriaQuery<Lot> criteria = entityManager.getCriteriaBuilder().createQuery(Lot.class);
            criteria.select(criteria.from(Lot.class));
            listOfEmailDomains = entityManager.createQuery(criteria).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Exception" + ex);
            entityManager.getTransaction().rollback();
        }
        return listOfEmailDomains;
    }
}
