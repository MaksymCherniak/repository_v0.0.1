package BookingTool.DAO.Repository;

import BookingTool.Model.LocalModel.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Max on 18.02.2016.
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE t.train LIKE :train AND wagon_id LIKE :wagon AND t.seat LIKE :seat")
    Ticket findTicket(@Param("train") int train, @Param("wagon") long wagon_id, @Param("seat") int seat);
}
