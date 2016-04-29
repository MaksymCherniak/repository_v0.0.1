package PhoneBook.DAO.Repository;

import PhoneBook.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("SELECT c FROM Contact c WHERE user_id LIKE :user_id")
    List<Contact> getAllContactsByUser(@Param("user_id") long user_id);
}
