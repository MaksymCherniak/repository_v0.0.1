package BookingTool.DAO.Repository;

import BookingTool.Entity.Stations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Stations, Long>{
}
