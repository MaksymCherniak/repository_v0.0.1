package BookingTool.DAO.Repository;

import BookingTool.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email LIKE :login AND u.password LIKE :password")
    User getUserByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
