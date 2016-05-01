import PhoneBook.DAO.Service.IContactDAO;
import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.Contact;
import PhoneBook.Entity.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.After;
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

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class
        , TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class TMySQLContactDAO {
    private User user;
    private Contact contact;
    @Autowired
    private IUserDAO iUserDAO;
    @Autowired
    private IContactDAO iContactDAO;

    @Before
    public void initialize() {
        user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setFullName("fullName");
        iUserDAO.insertUser(user);
        contact = new Contact("surname", "name", "patronym", "abc@ukr.net", "Kyiv", "+380936574625", "0447362561", user);
    }

    @After
    public void deleteUser() {
        iUserDAO.deleteUser(user.getId());
    }

    @Test
    public void addDuplicatedContactIsImpossible_contactDoesNotExistAfterDelete() {
        iContactDAO.insertContact(contact);

        assertFalse("Duplicated contact added", iContactDAO.insertContact(contact));

        iContactDAO.removeContactById(contact.getId());

        assertNull("Contact exists after remove", iContactDAO.getContactById(contact.getId()));
    }

    @Test
    public void whenAddTheContactExists_getContactById() {
        assertNull("Contact exists before added", iContactDAO.getContactById(contact.getId()));

        iContactDAO.insertContact(contact);

        assertNotNull("Contact does not exist after added", iContactDAO.getContactById(contact.getId()));

        iContactDAO.removeContactById(contact.getId());
    }

    @Test
    public void noContactEntriesFoundIfUserNotExist() {
        List<Contact> contactList = iContactDAO.getAllContactsByUserId(new User("newFullName", "newLogin", "newPassword").getId());

        assertThat(contactList.size(), is(0));
    }

    @Test
    public void contactEntryFoundWhenUserExist() {
        iContactDAO.insertContact(contact);

        List<Contact> contactList = iContactDAO.getAllContactsByUserId(user.getId());

        assertThat(contactList.size(), is(1));

        iContactDAO.removeContactById(contact.getId());
    }

    @Test
    public void updateContact() {
        iContactDAO.insertContact(contact);

        contact.setName("newName");
        iContactDAO.updateContact(contact);

        assertTrue("Contact didn't update", iContactDAO.getContactById(contact.getId()).getName().equals("newName"));

        iContactDAO.removeContactById(contact.getId());
    }
}
