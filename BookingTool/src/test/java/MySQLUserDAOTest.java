import DAO.MySQLUserDAO;
import Model.LocalModel.User;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

/**
 * Created by Max on 11.01.2016.
 */
public class MySQLUserDAOTest {
    private MySQLUserDAO mySQLUserDAO;
    private EntityManager entityManager;

    @Before
    public void initializeDependencies() {
        entityManager = Persistence.createEntityManagerFactory("test").
                createEntityManager();
        mySQLUserDAO = new MySQLUserDAO();
        mySQLUserDAO.setEntityManager(entityManager);
    }

    @Test
    public void userTest() {
        User user = new User("User", "User2");

        mySQLUserDAO.insertUser(user);
        assertFalse(mySQLUserDAO.insertUser(user));

        boolean checker;
        user = mySQLUserDAO.findUser(user);
        if (user == null){
            checker = false;
        } else {
            checker = true;
        }
        assertTrue(checker);

        mySQLUserDAO.deleteUser(user);
        assertFalse(mySQLUserDAO.deleteUser(user));
    }
}
