package BookingTool.DAO.Repository;

import BookingTool.Controller.IControllerStaticValues;
import BookingTool.Entity.Stations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StationRepository extends JpaRepository<Stations, Long>, IControllerStaticValues {
    @Query("SELECT r FROM Stations r WHERE r.station LIKE :station")
    Stations getStationByName(@Param(STATION) String station);

    @Query("SELECT r FROM Stations r WHERE route_id LIKE :route")
    List<Stations> getStationsByRoute(@Param(ROUTE) long route_id);
}
