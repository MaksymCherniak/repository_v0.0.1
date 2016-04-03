package BookingTool.DAO.Impl;

import BookingTool.DAO.Repository.RouteRepository;
import BookingTool.DAO.Repository.StationRepository;
import BookingTool.DAO.Service.IRouteDAO;
import BookingTool.Entity.Route;
import BookingTool.Entity.Stations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLRouteDAO implements IRouteDAO {
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private StationRepository stationRepository;
    private static Logger log = Logger.getLogger(MySQLRouteDAO.class.getName());

    public MySQLRouteDAO() {
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public boolean insertRoute(Route route) {
        try {
            if (getRouteByNumber(route.getRouteNumber()) == null) {
                routeRepository.saveAndFlush(route);
                return true;
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }

    public boolean insertStation(Stations stations) {
        try {
            stationRepository.saveAndFlush(stations);
            return true;
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }

    public List<Route> getAllRoutes(String livingStation, String arrivalStation) {
        return routeRepository.getRoutesByLeavingAndArrivalStations(livingStation, arrivalStation);
    }

    public Route getRouteByNumber(int number) {
        return routeRepository.getRouteByNumber(number);
    }

    public Stations getStationsByRouteAndName(Route route, String station) {
        return stationRepository.getStationsByRouteAndName(route.getId(), station);
    }
}
