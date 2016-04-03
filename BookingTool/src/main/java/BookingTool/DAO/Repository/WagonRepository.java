package BookingTool.DAO.Repository;

import BookingTool.Model.LocalModel.Wagon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WagonRepository extends JpaRepository<Wagon, Long> {
    @Query("SELECT s FROM Wagon s WHERE train_id LIKE :train AND s.number LIKE :number")
    Wagon findByNumberAndTrainId(@Param("train") long train_id, @Param("number") int number);

    @Query("SELECT s FROM Wagon s WHERE train_id LIKE :train")
    List<Wagon> getWagonsByTrain(@Param("train") long train_id);

    @Modifying
    @Transactional
    @Query("UPDATE Wagon c set c.freeSeats= :seats WHERE train_id LIKE :train AND c.number LIKE :number")
    void updateWagon(@Param("train") long train_id, @Param("seats") int freeSeats, @Param("number") int number);
}
