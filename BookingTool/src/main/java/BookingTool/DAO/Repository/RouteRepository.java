package BookingTool.DAO.Repository;

import BookingTool.Controller.IControllerStaticValues;
import BookingTool.Entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long>, IControllerStaticValues {
    @Query("SELECT r FROM Route r WHERE r.routeNumber LIKE :routeNumber")
    Route getRouteByNumber(@Param(ROUTE_NUMBER) int number);

    @Query("SELECT r FROM Route r WHERE r.leavingStation LIKE :leavingStation AND r.arrivalStation LIKE :arrivalStation")
    List<Route> getRoutesByLeavingAndArrivalStations(@Param(STATION_LEAVING_STATION) String leavingStation
            , @Param(STATION_ARRIVAL_STATION) String arrivalStation);
}
