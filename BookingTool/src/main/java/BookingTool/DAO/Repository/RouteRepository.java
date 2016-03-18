package BookingTool.DAO.Repository;

import BookingTool.Entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT r FROM Route r WHERE r.routeNumber LIKE :number")
    Route getRouteByNumber(@Param("number") int number);
}
