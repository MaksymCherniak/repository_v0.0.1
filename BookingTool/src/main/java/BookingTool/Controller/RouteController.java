package BookingTool.Controller;

import BookingTool.DAO.Service.IRouteService;
import BookingTool.DAO.Service.ITrainService;
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
    private IRouteService iRouteService;
    @Autowired
    private ITrainService iTrainService;

    @RequestMapping(value = "/getAllRoutesGet.do", method = RequestMethod.GET)
    public ModelAndView selectAllRoutes() {
        return new ModelAndView(PAGE_GET_ALL_ROUTES, ROUTE_LIST_OF_ROUTES, iRouteService.getAllRoutes());
    }

    @RequestMapping(value = "/getAllRoutesForAction.do", method = RequestMethod.GET)
    public ModelAndView selectAllRoutesForActions() {
        return new ModelAndView(PAGE_GET_ALL_ROUTES_FOR_ACTIONS, ROUTE_LIST_OF_ROUTES, iRouteService.getAllRoutes());
    }

    @RequestMapping(value = "/selectRoute.do", method = RequestMethod.GET)
    public ModelAndView selectRoute(@RequestParam(value = STATION_LEAVING_STATION) String leavingStation,
                                    @RequestParam(value = STATION_ARRIVAL_STATION) String arrivalStation,
                                    @RequestParam(value = ROUTE_YEAR) String routeYY,
                                    @RequestParam(value = ROUTE_MONTH) String routeMM,
                                    @RequestParam(value = ROUTE_DAY) String routeDD) {

        List<Route> listOfRoutes = iRouteService.getAllRoutes(leavingStation, arrivalStation);
        LocalDate routeDate = LocalDate.of(Integer.parseInt(routeYY), Integer.parseInt(routeMM), Integer.parseInt(routeDD));
        List<Train> listOfTrains = new ArrayList<Train>();
        for (int i = 0; i < listOfRoutes.size(); i++) {
            Train train = iTrainService.getTrainByDateAndRoute(routeDate, listOfRoutes.get(i));
            if (train != null) {
                listOfTrains.add(train);
            }
        }
        if (listOfTrains.size() != 0) {
            return new ModelAndView(PAGE_PRINT_ROUTES, "listOfTrains", listOfTrains);
        }
        return new ModelAndView(PAGE_MAIN);
    }

    @RequestMapping(value = "/insertRoute.do", method = RequestMethod.POST)
    public ModelAndView insertRoute(@RequestParam(value = ROUTE_NUMBER) String routeNumber,
                                    @RequestParam(value = STATION_LEAVING_STATION) String leavingStation,
                                    @RequestParam(value = STATION_ARRIVAL_STATION) String arrivalStation,
                                    @RequestParam(value = STATION_LEAVING_HOUR) String leavingHH,
                                    @RequestParam(value = STATION_LEAVING_MINUTE) String leavingMM,
                                    @RequestParam(value = STATION_ARRIVAL_HOUR) String arrivalHH,
                                    @RequestParam(value = STATION_ARRIVAL_MINUTE) String arrivalMM) {

        Integer routeNum = routeNumberCheck(routeNumber);
        if (routeNum != null) {
            Route route = new Route();
            route.setRouteNumber(routeNum);
            route.setLeavingStation(leavingStation);
            route.setArrivalStation(arrivalStation);
            route.setLeavingTime(LocalTime.of(Integer.parseInt(leavingHH), Integer.parseInt(leavingMM)));
            route.setArrivalTime(LocalTime.of(Integer.parseInt(arrivalHH), Integer.parseInt(arrivalMM)));
            if (iRouteService.insertRoute(route)) {
                return new ModelAndView(PAGE_MAIN, INFO, route.toString() + " added");
            } else {
                return new ModelAndView(PAGE_INSERT_ROUTE, INFO, route.toString() + " didn't add");
            }
        } else {
            return new ModelAndView(PAGE_INSERT_ROUTE, INFO, "Wrong route number format");
        }
    }

    @RequestMapping(value = "routeActions.do", method = RequestMethod.GET)
    public ModelAndView routeAction(@RequestParam(value = ROUTE_NUMBER) String routeNumber) {
        Route route = iRouteService.getRouteByNumber(Integer.parseInt(routeNumber));
        return new ModelAndView(PAGE_ROUTE_ACTIONS, ROUTE, route);
    }

    private Integer routeNumberCheck(String routeNumber) {
        try {
            Route route = iRouteService.getRouteByNumber(Integer.parseInt(routeNumber));
            if (route == null) {
                return Integer.parseInt(routeNumber);
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return null;
    }
}
