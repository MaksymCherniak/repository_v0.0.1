import PhoneBook.DAO.Repository.UserRepository;
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class
        , TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class TUserRepository {
    private User user;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void initialize() {
        user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setFullName("fullName");
        userRepository.saveAndFlush(user);
    }

    @Test
    public void noUserEntriesFound() {
        user = userRepository.findByLoginAndPassword("testLogin", "testPassword");
        assertNull("User who isn't in database exist", user);
    }

    @Test
    public void userEntryFound() {
        user = userRepository.findByLoginAndPassword("login", "password");
        assertNotNull("User not found", user);
    }
}
