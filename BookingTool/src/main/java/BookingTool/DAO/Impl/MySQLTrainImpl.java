package BookingTool.DAO.Impl;

import BookingTool.DAO.Repository.TrainRepository;
import BookingTool.DAO.Service.ITrainService;
import BookingTool.Entity.Route;
import BookingTool.Entity.Train;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLTrainImpl implements ITrainService {
    @Autowired
    private TrainRepository trainRepository;
    private static Logger log = Logger.getLogger(MySQLTrainImpl.class.getName());

    public boolean insertTrain(Route route, LocalDate startDate, List<DayOfWeek> days) {
        try {
            if (days.size() == 7) {
                for (int i = 0; i < 20; i++) {
                    Train train = new Train();
                    if (getTrainByDateAndRoute(startDate, route) == null) {
                        train.setRoute(route);
                        train.setLeavingDate(startDate.plusDays(i));
                        trainRepository.saveAndFlush(train);
                    }
                }
            } else {
                for (int i = 0; i < 20; i++) {
                    Train train = new Train();
                    LocalDate localDate = startDate.plusDays(i);
                    if (days.contains(localDate.getDayOfWeek())) {
                        if (getTrainByDateAndRoute(startDate, route) == null) {
                            train.setRoute(route);
                            train.setLeavingDate(startDate.plusDays(i));
                            trainRepository.saveAndFlush(train);
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
            return false;
        }
    }

    public Train getTrainByDateAndRoute(LocalDate date, Route route) {
        return trainRepository.getTrainByDateAndRoute(date, route.getId());
    }

    public List<Train> getAllTrainsByRoute(Route route) {
        return trainRepository.getAllTrainsByRoute(route.getId());
    }

    public Train getTrainById(long id) {
        return trainRepository.findOne(id);
    }
}
