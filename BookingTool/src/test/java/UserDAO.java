import BookingTool.DAO.MySQLUserDAO;
import BookingTool.Model.LocalModel.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Max on 11.01.2016.
 */
public class UserDAO {
    private MySQLUserDAO mySQLUserDAO;
    private EntityManager entityManager;
    private User user;

    @Before
    public void initializeDependencies() {
        entityManager = Persistence.createEntityManagerFactory("test").
                createEntityManager();
        mySQLUserDAO = new MySQLUserDAO();
        mySQLUserDAO.setEntityManager(entityManager);
        user = new User("User", "User2");
    }

    @After
    public void tearDown() {
        entityManager.close();
    }

    @Test
    public void addDuplicatedUserIsImpossible() {
        mySQLUserDAO.insertUser(user);
        assertFalse("Duplicated user added", mySQLUserDAO.insertUser(user));

        List<User> userList = mySQLUserDAO.getAllUsers();
        assertTrue("User was not added", userList.contains(user));
    }

    @Test
    public void whenAddTheUserExists() {
        assertNull("User exists before added", mySQLUserDAO.findUser(user));

        mySQLUserDAO.insertUser(user);

        assertNotNull("User does not exist after added", mySQLUserDAO.findUser(user));
    }

    @Test
    public void doubleRemoveUserIsImpossible() {
        mySQLUserDAO.insertUser(user);
        user = mySQLUserDAO.findUser(user);

        mySQLUserDAO.deleteUser(user);
        assertFalse("User double removed", mySQLUserDAO.deleteUser(user));
    }

    @Test
    public void canNotRemoveIfTheUserIsNotInTheDatabase() {
        assertFalse("The user is not in the database removed", mySQLUserDAO.deleteUser(user));
    }
}
