import PhoneBook.DAO.Repository.ContactRepository;
import PhoneBook.DAO.Repository.UserRepository;
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
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class
        , TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class TContactRepository {
    private User user;
    private Contact contact;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;

    @Before
    public void initialize() {
        user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setFullName("fullName");
        userRepository.saveAndFlush(user);
        contact = new Contact("surname", "name", "patronym", "abc@ukr.net", "Kyiv", "+380936574625", "0447362561", user);
        contactRepository.saveAndFlush(contact);
    }

    @After
    public void deleteContactAndUser() {
        contactRepository.delete(contact.getId());
        userRepository.delete(user.getId());
    }

    @Test
    public void noContactEntriesFound() {
        List<Contact> contactList = contactRepository.getAllContactsByUser(new User("newFullName", "newLogin", "newPassword").getId());
        assertThat(contactList.size(), is(0));
    }

    @Test
    public void contactEntryFound() {
        List<Contact> contactList = contactRepository.getAllContactsByUser(user.getId());
        assertThat(contactList.size(), is(1));
    }
}
