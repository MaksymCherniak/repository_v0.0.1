package BookingTool.DAO.Impl;

import BookingTool.DAO.Repository.RouteRepository;
import BookingTool.DAO.Service.IRouteService;
import BookingTool.Entity.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLRouteImpl implements IRouteService {
    @Autowired
    private RouteRepository routeRepository;
    private static Logger log = Logger.getLogger(MySQLRouteImpl.class.getName());

    public MySQLRouteImpl() {
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

    public List<Route> getAllRoutes(String livingStation, String arrivalStation) {
        return routeRepository.getRoutesByLeavingAndArrivalStations(livingStation, arrivalStation);
    }

    public Route getRouteByNumber(int number) {
        return routeRepository.getRouteByNumber(number);
    }
}
