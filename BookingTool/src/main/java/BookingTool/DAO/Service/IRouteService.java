package BookingTool.DAO.Service;

import BookingTool.Entity.Route;

import java.util.List;

public interface IRouteService {
    boolean insertRoute(Route route);

    Route getRouteByNumber(int number);

    List<Route> getAllRoutes(String livingStation, String arrivalStation);

    List<Route> getAllRoutes();
}
