package BookingTool.DAO.Service;

import BookingTool.Entity.Route;
import BookingTool.Entity.Stations;

import java.util.List;

public interface IRouteDAO {
    boolean insertRoute(Route route);

    boolean insertStation(Stations stations);

    Route getRouteByNumber(int number);

    List<Route> getAllRoutes(String livingStation, String arrivalStation);

    List<Route> getAllRoutes();

    Stations getStationsByRouteAndName(Route route, String station);
}
