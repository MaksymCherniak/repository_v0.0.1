package BookingTool.DAO.Repository;

import BookingTool.Model.LocalModel.Wagon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface WagonRepository extends JpaRepository<Wagon, Long> {
    @Query("SELECT w FROM Wagon w WHERE w.number LIKE :number")
    Wagon findByNumber(@Param("number") int number);

    @Modifying
    @Transactional
    @Query("UPDATE Wagon c set c.freeSeats= :seats WHERE c.number LIKE :number")
    void updateWagon(@Param("seats") int freeSeats, @Param("number") int number);
}
