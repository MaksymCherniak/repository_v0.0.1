package PhoneBook.DAO.Service;

import PhoneBook.Entity.Contact;

import java.util.List;

public interface IContactDAO {
    void insertContact(Contact contact);

    List<Contact> getAllContactsByUserId(long user_id);

    void removeContactById(long contact_id);

    Contact getContactById(long contact_id);

    void updateContact(Contact contact);
}
