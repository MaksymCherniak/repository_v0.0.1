package BookingTool.DAO.Repository;

import BookingTool.Model.LocalModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.name LIKE :name AND u.surname LIKE :surname")
    User getUserByNameAndSurname(@Param("surname") String surname, @Param("name") String name);
}
