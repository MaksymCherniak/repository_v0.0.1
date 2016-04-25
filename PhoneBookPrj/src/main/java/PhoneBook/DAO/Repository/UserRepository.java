package PhoneBook.DAO.Repository;

import PhoneBook.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.login LIKE :login AND u.password LIKE :password")
    User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
