package integration;

import entity.user.User;
import integration.jpa.EntityManagerUtil;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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
    @Override
    public String getUserPasswordByLogin(String login) {
        String userPassword = null;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNativeQuery("SELECT password FROM user where login = " + login + ";");
            List<String> list = new ArrayList<String>();
            list = query.getResultList();
            userPassword = list.get(0);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Exception" + ex);
            entityManager.getTransaction().rollback();
        }
        return userPassword;
    }

}
