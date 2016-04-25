package PhoneBook.DAO.Impl;

import PhoneBook.DAO.Repository.ContactRepository;
import PhoneBook.DAO.Service.IContactDAO;
import PhoneBook.Entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MySQLContactDAO implements IContactDAO {
    @Autowired
    private ContactRepository contactRepository;

    public void insertContact(Contact contact) {
        contactRepository.saveAndFlush(contact);
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
