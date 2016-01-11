package DAO;

import Model.LocalModel.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLUserDAO implements IUserDAO {
    private static final String FIND_USER = "SELECT u FROM User u WHERE u.name LIKE :name AND u.surname LIKE :surname";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static Logger log = Logger.getLogger(MySQLUserDAO.class.getName());
    private static EntityManager entityManager;

    public MySQLUserDAO() {
        entityManager = HibernateUtil.getEm();
    }

    public static void setEntityManager(EntityManager entityManager) {
        MySQLUserDAO.entityManager = entityManager;
    }

    public boolean insertUser(User user) {
        try {
            if (findUser(user) == null) {
                entityManager.getTransaction().begin();
                entityManager.persist(user);
                entityManager.getTransaction().commit();
                entityManager.clear();
                log.info("User created");
                return true;
            } else {
                log.info("User already created");
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }

    public User findUser(User user){
        List<User> userList = entityManager.createQuery(FIND_USER).setParameter(NAME, user.getName())
                .setParameter(SURNAME, user.getSurname()).getResultList();
        if (userList.size() != 0) {
            return userList.get(0);
        } else {
            log.info("User not found");
            return null;
        }
    }
    public User findUserById(Integer id) {
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
            if (findUser(user) != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(user);
                entityManager.getTransaction().commit();
                entityManager.clear();
                log.info("User removed");
                return true;
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }

    public List<User> getAllUsers() {
        return entityManager.createNamedQuery("User.getAll", User.class).getResultList();
    }
}