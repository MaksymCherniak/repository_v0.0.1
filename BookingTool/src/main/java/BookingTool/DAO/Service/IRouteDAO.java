package BookingTool.DAO.Service;

import BookingTool.Entity.Route;
import BookingTool.Entity.Stations;
import BookingTool.Entity.Train;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public interface IRouteDAO {
    boolean insertRoute(Route route);

    boolean insertStation(Stations stations);

    boolean insertTrain(Route route, LocalDate leavingDate, List<DayOfWeek> days);

    Route getRouteByNumber(int number);

    List<Train> getAllTrainsByRoute(Route route);

    List<Route> getAllRoutes(String livingStation, String arrivalStation, LocalDate localDate);

    List<Route> getAllRoutes();

}
