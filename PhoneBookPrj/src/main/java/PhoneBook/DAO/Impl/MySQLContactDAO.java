package PhoneBook.DAO.Impl;

import PhoneBook.DAO.Repository.ContactRepository;
import PhoneBook.DAO.Service.IContactDAO;
import PhoneBook.Entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLContactDAO implements IContactDAO {
    private static Logger log = Logger.getLogger(MySQLContactDAO.class.getName());
    @Autowired
    private ContactRepository contactRepository;

    public boolean insertContact(Contact contact) {
        try {
            if (getContactById(contact.getId()) == null) {
                contactRepository.saveAndFlush(contact);
                return true;
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }

    public List<Contact> getAllContactsByUserId(long user_id) {
        return contactRepository.getAllContactsByUser(user_id);
    }

    public void removeContactById(long contact_id) {
        contactRepository.delete(contact_id);
    }

    public Contact getContactById(long contact_id) {
        return contactRepository.findOne(contact_id);
    }

    public void updateContact(Contact contact) {
        contactRepository.saveAndFlush(contact);
    }
}
