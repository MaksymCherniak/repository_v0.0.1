package DAO;

import Model.LocalModel.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLUserDAO implements IUserDAO {
    private static Logger log = Logger.getLogger(MySQLUserDAO.class.getName());
    private static EntityManager entityManager;

    public MySQLUserDAO() {
        entityManager = HibernateUtil.getEm();
    }

    public int insertUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Exception: ", e);
        }
        return 0;
    }

    public User findUser(Integer id) {
        return entityManager.find(User.class, id);
    }

    public void deleteUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    public List<User> getAllUsers() {
        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getAll", User.class);
        return namedQuery.getResultList();
    }

}