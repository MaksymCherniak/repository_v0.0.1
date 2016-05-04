package BookingTool.Controller;

import BookingTool.DAO.Service.IRouteDAO;
import BookingTool.Entity.Route;
import BookingTool.Entity.Stations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;

@Controller
public class StationController implements IControllerStaticValues {
    @Autowired
    private IRouteDAO iRouteDAO;

    @RequestMapping(value = "/insertStation.do", method = RequestMethod.POST)
    public ModelAndView insertStation(@RequestParam(value = ROUTE_NUMBER) String routeNumber,
                                      @RequestParam(value = STATION) String station,
                                      @RequestParam(value = LEAVING_HOUR) String leavingHH,
                                      @RequestParam(value = LEAVING_MINUTE) String leavingMM,
                                      @RequestParam(value = ARRIVAL_HOUR) String arrivalHH,
                                      @RequestParam(value = ARRIVAL_MINUTE) String arrivalMM) {

        ModelAndView modelAndView = new ModelAndView();
        Route route = iRouteDAO.getRouteByNumber(Integer.parseInt(routeNumber));
        if (route != null) {
            Stations stations = new Stations();
            stations.setRoute(route);
            stations.setStation(station);
            stations.setLeavingTime(LocalTime.of(Integer.parseInt(leavingHH), Integer.parseInt(leavingMM)));
            stations.setArrivalTime(LocalTime.of(Integer.parseInt(arrivalHH), Integer.parseInt(arrivalMM)));

            if ((stations.getArrivalTime().isBefore(stations.getLeavingTime())) && iRouteDAO.insertStation(stations)) {
                modelAndView.addObject(INFO, "Stations " + stations.getStation() + " added to the route number " + routeNumber);
                modelAndView.setViewName(PAGE_INSERT_STATION);
                return modelAndView;
            } else {
                modelAndView.addObject(INFO, "Error. Route didn't add");
                modelAndView.setViewName(PAGE_INFO);
                return modelAndView;
            }
        } else {
            modelAndView.addObject(INFO, "Wrong route number");
            modelAndView.setViewName(PAGE_INFO);
            return modelAndView;
        }
    }
}
