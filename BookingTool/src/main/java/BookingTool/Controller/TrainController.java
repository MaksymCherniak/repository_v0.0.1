package BookingTool.Controller;

import BookingTool.DAO.Service.IRouteService;
import BookingTool.DAO.Service.ITrainService;
import BookingTool.DAO.Service.IWagonService;
import BookingTool.Entity.Route;
import BookingTool.Entity.Train;
import BookingTool.Entity.Wagon;
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
    private IRouteService iRouteService;
    @Autowired
    private ITrainService iTrainService;
    @Autowired
    private IWagonService iWagonService;

    @RequestMapping(value = "/insertTrain.do", method = RequestMethod.GET)
    public ModelAndView insertTrain(@RequestParam(value = ROUTE_NUMBER) String routeNumber) {
        return new ModelAndView(PAGE_INSERT_TRAIN, ROUTE, iRouteService.getRouteByNumber(Integer.parseInt(routeNumber)));
    }

    @RequestMapping(value = "/insertTrain.do", method = RequestMethod.POST)
    public ModelAndView insertTrain(@RequestParam(value = ROUTE_NUMBER) String routeNumber,
                                    @RequestParam(value = TRAIN_DAYS) String[] days,
                                    @RequestParam(value = TRAIN_START_YEAR) String startYY,
                                    @RequestParam(value = TRAIN_START_MONTH) String startMM,
                                    @RequestParam(value = TRAIN_START_DAY) String startDD) {

        List<DayOfWeek> daysOfWeek = getDaysOfWeekList(days);
        Route route = iRouteService.getRouteByNumber(Integer.parseInt(routeNumber));
        if (route != null && daysOfWeek != null) {
            LocalDate startDate = LocalDate.of(Integer.parseInt(startYY), Integer.parseInt(startMM), Integer.parseInt(startDD));
            iTrainService.insertTrain(route, startDate, daysOfWeek);
            return new ModelAndView(PAGE_MAIN, INFO, "Trains added.");
        } else {
            return new ModelAndView(PAGE_MAIN, INFO, "Wrong route number or date format.");
        }
    }

    @RequestMapping(value = "/printTrain.do", method = RequestMethod.GET)
    public ModelAndView printTrain(@RequestParam(value = ID) String id) {

        ModelAndView modelAndView = new ModelAndView();
        Train train = iTrainService.getTrainById(Long.parseLong(id));
        List<Wagon> listOfWagons = iWagonService.getWagonsByTrain(train);
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
