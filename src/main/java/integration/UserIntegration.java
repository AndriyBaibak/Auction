package integration;

import entity.user.User;
import integration.jpa.EntityManagerUtil;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;

public class UserIntegration implements UserDao {
    private static Logger log = Logger.getLogger(UserIntegration.class);

    private EntityManager entityManager = EntityManagerUtil.getEntityManager();

    @Override
    public void registration(String login, String password, String firstName, String lastName) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(new User(login, password, firstName, lastName));
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Exception" + ex);
            entityManager.getTransaction().rollback();
        }

    }

    public User getUserByName(String name) {
        User user = null;
        try {
            entityManager.getTransaction().begin();
            user = (User) entityManager.createNativeQuery("SELECT * FROM user where firstName = " + name + ";", User.class);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Exception" + ex);
            entityManager.getTransaction().rollback();
        }
        return user;
    }

}
