package BookingTool.DAO.Impl;

import BookingTool.DAO.Repository.RouteRepository;
import BookingTool.DAO.Repository.StationRepository;
import BookingTool.DAO.Repository.TrainRepository;
import BookingTool.DAO.Service.IRouteDAO;
import BookingTool.Entity.Route;
import BookingTool.Entity.Stations;
import BookingTool.Entity.Train;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLRouteDAO implements IRouteDAO {
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private TrainRepository trainRepository;
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
            log.log(Level.WARNING, "Exception: ", e);
        }
        return false;
    }

    public boolean insertStation(Stations stations) {
        try {
            stationRepository.saveAndFlush(stations);
            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
        return false;
    }

    public boolean insertTrain(Route route, LocalDate startDate, List<DayOfWeek> days) {
        try {
            if (days.size() == 7) {
                for (int i = 0; i < 20; i++) {
                    Train train = new Train();
                    train.setRoute(route);
                    train.setLeavingDate(startDate.plusDays(i));
                    System.out.println(train.toString());
                    trainRepository.saveAndFlush(train);
                }
            } else {
                for (int i = 0; i < 20; i++) {
                    Train train = new Train();
                    LocalDate localDate = startDate.plusDays(i);
                    if (days.contains(localDate.getDayOfWeek())) {
                        train.setRoute(route);
                        train.setLeavingDate(localDate);
                        trainRepository.saveAndFlush(train);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception: ", e);
            return false;
        }
    }

    public List<Route> getAllRoutes(String livingStation, String arrivalStation, LocalDate localDate) {
        return null;
    }

    public List<Train> getAllTrainsByRoute(Route route) {
        return trainRepository.getAllTrainsByRoute(route.getId());
    }

    public Route getRouteByNumber(int number) {
        return routeRepository.getRouteByNumber(number);
    }
}
