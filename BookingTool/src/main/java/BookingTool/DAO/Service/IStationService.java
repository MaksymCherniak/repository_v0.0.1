package BookingTool.DAO.Service;

import BookingTool.Entity.Route;
import BookingTool.Entity.Stations;

import java.util.List;

public interface IStationService {
    boolean insertStation(Stations stations);

    void deleteStation(long id);

    Stations getStationByName(String station);

    Stations getStationById(long id);

    List<Stations> getStationsByRoute(Route route);

    void updateStation(Stations stations);
}
