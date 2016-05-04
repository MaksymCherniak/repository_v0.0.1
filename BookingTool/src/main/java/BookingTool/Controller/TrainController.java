package BookingTool.Controller;

import BookingTool.DAO.Service.IRouteDAO;
import BookingTool.DAO.Service.ITrainDAO;
import BookingTool.DAO.Service.IWagonDAO;
import BookingTool.Entity.Route;
import BookingTool.Entity.Train;
import BookingTool.Model.LocalModel.Wagon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class TrainController implements IControllerStaticValues {
    @Autowired
    private IRouteDAO iRouteDAO;
    @Autowired
    private ITrainDAO iTrainDAO;
    @Autowired
    private IWagonDAO iWagonDAO;

    @RequestMapping(value = "/insertTrain.do", method = RequestMethod.POST)
    public ModelAndView insertTrain(@RequestParam(value = ROUTE_NUMBER) String routeNumber,
                                    @RequestParam(value = DAYS) String[] days,
                                    @RequestParam(value = START_YEAR) String startYY,
                                    @RequestParam(value = START_MONTH) String startMM,
                                    @RequestParam(value = START_DAY) String startDD) {

        ModelAndView modelAndView = new ModelAndView();
        List<DayOfWeek> daysOfWeek = getDaysOfWeekList(days);
        Route route = iRouteDAO.getRouteByNumber(Integer.parseInt(routeNumber));
        if (route != null && daysOfWeek != null) {
            LocalDate startDate = LocalDate.of(Integer.parseInt(startYY), Integer.parseInt(startMM), Integer.parseInt(startDD));
            iTrainDAO.insertTrain(route, startDate, daysOfWeek);
            modelAndView.addObject(INFO, "Trains added.");
            modelAndView.setViewName(PAGE_INFO);
            return modelAndView;
        } else {
            modelAndView.addObject(INFO, "Wrong route number or date format.");
            modelAndView.setViewName(PAGE_INFO);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/printTrain.do", method = RequestMethod.GET)
    public ModelAndView printTrain(@RequestParam(value = ID) String id) {

        ModelAndView modelAndView = new ModelAndView();
        Train train = iTrainDAO.getTrainById(Long.parseLong(id));
        List<Wagon> listOfWagons = iWagonDAO.getWagonsByTrain(train);
        Collections.sort(listOfWagons);
        modelAndView.setViewName(PAGE_PRINT_TRAIN);
        modelAndView.addObject(TRAIN, train);
        modelAndView.addObject("listOfWagons", listOfWagons);
        return modelAndView;
    }


    private List<DayOfWeek> getDaysOfWeekList(String[] days) {
        List<DayOfWeek> daysOfWeek = new ArrayList<DayOfWeek>();
        if (days.length == 1 && days[0].equals("allDays")) {
            daysOfWeek.add(DayOfWeek.MONDAY);
            daysOfWeek.add(DayOfWeek.TUESDAY);
            daysOfWeek.add(DayOfWeek.WEDNESDAY);
            daysOfWeek.add(DayOfWeek.THURSDAY);
            daysOfWeek.add(DayOfWeek.FRIDAY);
            daysOfWeek.add(DayOfWeek.SATURDAY);
            daysOfWeek.add(DayOfWeek.SUNDAY);
            return daysOfWeek;
        } else {
            for (int i = 0; i < days.length; i++) {
                if (days[i].equals("monday")) {
                    daysOfWeek.add(DayOfWeek.MONDAY);
                } else if (days[i].equals("tuesday")) {
                    daysOfWeek.add(DayOfWeek.TUESDAY);
                } else if (days[i].equals("wednesday")) {
                    daysOfWeek.add(DayOfWeek.WEDNESDAY);
                } else if (days[i].equals("thursday")) {
                    daysOfWeek.add(DayOfWeek.THURSDAY);
                } else if (days[i].equals("friday")) {
                    daysOfWeek.add(DayOfWeek.FRIDAY);
                } else if (days[i].equals("saturday")) {
                    daysOfWeek.add(DayOfWeek.SATURDAY);
                } else if (days[i].equals("sunday")) {
                    daysOfWeek.add(DayOfWeek.SUNDAY);
                }
            }
            return daysOfWeek;
        }
    }
}
