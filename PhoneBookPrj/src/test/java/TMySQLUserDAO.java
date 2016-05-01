import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class
        , TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class TMySQLUserDAO {
    private User user;
    @Autowired
    private IUserDAO iUserDAO;

    @Before
    public void initialize() {
        user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setFullName("fullName");
    }

    @Test
    public void addDuplicatedUserIsImpossible_userDoesNotExistAfterDelete() {
        iUserDAO.insertUser(user);

        assertFalse("Duplicated user added", iUserDAO.insertUser(user));

        iUserDAO.deleteUser(user.getId());

        assertNull("User exists after remove", iUserDAO.findUserById(user.getId()));
    }

    @Test
    public void whenAddTheUserExists_findUserByLoginAndPassword() {
        assertNull("User exists before added", iUserDAO.findUserByLoginAndPassword(user.getLogin(), user.getPassword()));

        iUserDAO.insertUser(user);

        assertNotNull("User does not exist after added", iUserDAO.findUserByLoginAndPassword(user.getLogin(), user.getPassword()));

        iUserDAO.deleteUser(user.getId());
    }

    @Test
    public void whenAddTheUserExists_findUserById() {
        assertNull("User exists before added", iUserDAO.findUserById(user.getId()));

        iUserDAO.insertUser(user);

        assertNotNull("User does not exist after added", iUserDAO.findUserById(user.getId()));

        iUserDAO.deleteUser(user.getId());
    }
}
