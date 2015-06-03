package integration.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerUtil {

    private static EntityManager entityManager;

    private EntityManagerUtil() {
        entityManager = Persistence.createEntityManagerFactory("Eclipselink_JPA").createEntityManager();
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            new EntityManagerUtil();
        }
        return entityManager;
    }

}
