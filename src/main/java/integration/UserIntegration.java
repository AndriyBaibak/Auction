package integration;

import entity.user.User;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
public class UserIntegration implements UserDao{
    private static Logger log = Logger.getLogger(UserIntegration.class);


    private List<User> userList = new ArrayList<User>();
    private EntityManager entityManager = Persistence.createEntityManagerFactory("Eclipselink_JPA").createEntityManager();

    @Override
    public void registration(String login, String password, String firstName, String lastName) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(new User(login, password,firstName,lastName));
            entityManager.getTransaction().commit();
        }catch (Exception ex) {
            log.error("===============" + ex);
            entityManager.getTransaction().rollback();
        }
    }
}
