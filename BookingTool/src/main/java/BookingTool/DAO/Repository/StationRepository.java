package BookingTool.DAO.Repository;

import BookingTool.Entity.Stations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StationRepository extends JpaRepository<Stations, Long> {
    @Query("SELECT r FROM Stations r WHERE route_id LIKE :route AND r.station LIKE :station")
    Stations getStationsByRouteAndName(@Param("route") long route_id, @Param("station") String station);

    @Query("SELECT r FROM Stations r WHERE route_id LIKE :route")
    List<Stations> getStationsByRoute(@Param("route") long route_id);
}
