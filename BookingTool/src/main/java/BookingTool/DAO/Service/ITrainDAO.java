package BookingTool.DAO.Service;

import BookingTool.Entity.Route;
import BookingTool.Entity.Train;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public interface ITrainDAO {
    boolean insertTrain(Route route, LocalDate leavingDate, List<DayOfWeek> days);

    Train getTrainByDateAndRoute(LocalDate date, Route route);

    List<Train> getAllTrainsByRoute(Route route);

    Train getTrainById(long id);
}
