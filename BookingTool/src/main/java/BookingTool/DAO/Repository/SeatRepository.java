package BookingTool.DAO.Repository;

import BookingTool.Model.LocalModel.Seat;
import BookingTool.Model.LocalModel.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE wagon_id LIKE :wagon AND s.number LIKE :number")
    Seat getSeat(@Param("wagon") long wagon_id, @Param("number") int number);

    @Query("SELECT s FROM Seat s WHERE wagon_id LIKE :wagon AND s.status='FREE'")
    List<Seat> getFreeSeats(@Param("wagon") long wagon_id);

    @Query("SELECT s FROM Seat s WHERE wagon_id LIKE :wagon")
    List<Seat> getAllSeats(@Param("wagon") long wagon_id);

    @Modifying
    @Transactional
    @Query("UPDATE Seat c set c.status=:status WHERE wagon_id LIKE :wagon AND c.number LIKE :number")
    void updateSeat(@Param("wagon") long wagon_id, @Param("number") int number, @Param("status") SeatStatus status);
}
