package entity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by Andriy on 19.05.2015.
 */
public class EntityManagerUtil {

    private static EntityManager entityManager;

    public EntityManagerUtil(){
        entityManager = (Persistence.createEntityManagerFactory("some EntityManager").createEntityManager());
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
