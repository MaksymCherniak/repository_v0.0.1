package BookingTool.DAO.Impl;

import BookingTool.DAO.Repository.StationRepository;
import BookingTool.DAO.Service.IStationService;
import BookingTool.Entity.Route;
import BookingTool.Entity.Stations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLStationImpl implements IStationService {
    @Autowired
    private StationRepository stationRepository;
    private static Logger log = Logger.getLogger(MySQLStationImpl.class.getName());

    public boolean insertStation(Stations stations) {
        try {
            stationRepository.saveAndFlush(stations);
            return true;
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }

    public void updateStation(Stations stations) {
        stationRepository.saveAndFlush(stations);
    }

    public void deleteStation(long id) {
        stationRepository.delete(id);
    }

    public Stations getStationByName(String station) {
        return stationRepository.getStationByName(station);
    }

    public Stations getStationById(long id) {
        return stationRepository.findOne(id);
    }

    public List<Stations> getStationsByRoute(Route route) {
        return stationRepository.getStationsByRoute(route.getId());
    }
}
