package BookingTool.DAO.Repository;

import BookingTool.Entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {
    @Query("SELECT t FROM Train t WHERE route_id LIKE :route")
    List<Train> getAllTrainsByRoute(@Param("route") long route_id);

    @Query("SELECT t FROM Train t WHERE route_id LIKE :route AND t.leavingDate LIKE :date")
    Train getTrainByDateAndRoute(@Param("date") LocalDate date, @Param("route") long route_id);
}
