package DAO;

import Model.LocalModel.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLUserDAO implements IUserDAO {
    private static Logger log = Logger.getLogger(MySQLUserDAO.class.getName());
    private static EntityManager entityManager;

    public MySQLUserDAO() {
        entityManager = HibernateUtil.getEm();
    }

    public boolean insertUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Exception: ", e);
            return false;
        }
    }

    public User findUser(Integer id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            return user;
        } else {
            log.info("User not found");
        }
        return null;
    }

    public boolean deleteUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception: ", e);
            return false;
        }
    }

    public List<User> getAllUsers() {
        return entityManager.createNamedQuery("User.getAll", User.class).getResultList();
    }
}