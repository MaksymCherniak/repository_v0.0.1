package BookingTool.Controller;

import BookingTool.DAO.Service.IRouteDAO;
import BookingTool.DAO.Service.ITrainDAO;
import BookingTool.Entity.Route;
import BookingTool.Entity.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class RouteController implements IControllerStaticValues {
    private static Logger log = Logger.getLogger(RouteController.class.getName());
    @Autowired
    private IRouteDAO iRouteDAO;
    @Autowired
    private ITrainDAO iTrainDAO;

    @RequestMapping(value = "/selectRoute.do", method = RequestMethod.GET)
    public ModelAndView selectRoute(@RequestParam(value = LEAVING_STATION) String leavingStation,
                                    @RequestParam(value = ARRIVAL_STATION) String arrivalStation,
                                    @RequestParam(value = ROUTE_YEAR) String routeYY,
                                    @RequestParam(value = ROUTE_MONTH) String routeMM,
                                    @RequestParam(value = ROUTE_DAY) String routeDD) {

        ModelAndView modelAndView = new ModelAndView();
        List<Route> listOfRoutes = iRouteDAO.getAllRoutes(leavingStation, arrivalStation);
        LocalDate routeDate = LocalDate.of(Integer.parseInt(routeYY), Integer.parseInt(routeMM), Integer.parseInt(routeDD));
        List<Train> listOfTrains = new ArrayList<Train>();
        for (int i = 0; i < listOfRoutes.size(); i++) {
            Train train = iTrainDAO.getTrainByDateAndRoute(routeDate, listOfRoutes.get(i));
            if (train != null) {
                listOfTrains.add(train);
            }
        }
        if (listOfTrains.size() != 0) {
            modelAndView.addObject("listOfTrains", listOfTrains);
            modelAndView.setViewName(PAGE_PRINT_ROUTES);
            return modelAndView;
        }
        modelAndView.setViewName(PAGE_INFO);
        return modelAndView;
    }

    @RequestMapping(value = "/insertRoute.do", method = RequestMethod.POST)
    public ModelAndView insertRoute(@RequestParam(value = ROUTE_NUMBER) String routeNumber,
                                    @RequestParam(value = LEAVING_STATION) String leavingStation,
                                    @RequestParam(value = ARRIVAL_STATION) String arrivalStation,
                                    @RequestParam(value = LEAVING_HOUR) String leavingHH,
                                    @RequestParam(value = LEAVING_MINUTE) String leavingMM,
                                    @RequestParam(value = ARRIVAL_HOUR) String arrivalHH,
                                    @RequestParam(value = ARRIVAL_MINUTE) String arrivalMM) {

        ModelAndView modelAndView = new ModelAndView();
        Integer routeNum = routeNumberCheck(routeNumber);
        if (routeNum != null) {
            Route route = new Route();
            route.setRouteNumber(routeNum);
            route.setLeavingStation(leavingStation);
            route.setArrivalStation(arrivalStation);
            route.setLeavingTime(LocalTime.of(Integer.parseInt(leavingHH), Integer.parseInt(leavingMM)));
            route.setArrivalTime(LocalTime.of(Integer.parseInt(arrivalHH), Integer.parseInt(arrivalMM)));
            if (iRouteDAO.insertRoute(route)) {
                modelAndView.addObject(INFO, route.toString() + " added");
                modelAndView.setViewName(PAGE_INFO);
                return modelAndView;
            } else {
                modelAndView.addObject(INFO, route.toString() + " didn't add");
                modelAndView.setViewName(PAGE_INFO);
                return modelAndView;
            }
        } else {
            modelAndView.addObject(INFO, "Wrong route number format");
            modelAndView.setViewName(PAGE_INFO);
            return modelAndView;
        }
    }

    private Integer routeNumberCheck(String routeNumber) {
        try {
            Route route = iRouteDAO.getRouteByNumber(Integer.parseInt(routeNumber));
            if (route == null) {
                return Integer.parseInt(routeNumber);
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return null;
    }
}
