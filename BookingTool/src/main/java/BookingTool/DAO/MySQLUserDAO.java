package BookingTool.DAO;

import BookingTool.Model.LocalModel.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("MySQLUserDAO")
@Repository
@Transactional
public class MySQLUserDAO implements IUserDAO {
    private static final String FIND_USER = "SELECT u FROM User u WHERE u.name LIKE :name AND u.surname LIKE :surname";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static Logger log = Logger.getLogger(MySQLUserDAO.class.getName());
    @PersistenceContext
    private EntityManager entityManager;

    public MySQLUserDAO() {
        entityManager = HibernateUtil.getEm();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean insertUser(User user) {
        try {
            if (findUser(user) == null) {
                entityManager.persist(user);
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

    @Transactional(readOnly = true)
    public User findUser(User user) {
        List<User> userList = entityManager.createQuery(FIND_USER).setParameter(NAME, user.getName())
                .setParameter(SURNAME, user.getSurname()).getResultList();
        if (userList.size() != 0) {
            return userList.get(0);
        } else {
            log.info("User not found");
            return null;
        }
    }

    @Transactional(readOnly = true)
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
                entityManager.remove(user);
                log.info("User removed");
                return true;
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return entityManager.createNamedQuery("User.getAll", User.class).getResultList();
    }
}