package BookingTool.Controller;

import BookingTool.DAO.Service.IRouteService;
import BookingTool.DAO.Service.IStationService;
import BookingTool.Entity.Route;
import BookingTool.Entity.Stations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Controller
public class StationController implements IControllerStaticValues {
    @Autowired
    private IRouteService iRouteService;
    @Autowired
    private IStationService iStationService;

    @RequestMapping(value = "/getStationsByRoute.do", method = RequestMethod.GET)
    public ModelAndView getStationsByRoute(@RequestParam(value = ROUTE_NUMBER) String routeNumber) {
        Route route = iRouteService.getRouteByNumber(Integer.parseInt(routeNumber));
        List<Stations> listOfStations = iStationService.getStationsByRoute(route);
        Collections.sort(listOfStations);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_GET_STATIONS_BY_ROUTE);
        modelAndView.addObject(ROUTE, route);
        modelAndView.addObject(STATION_LIST_OF_STATIONS, listOfStations);

        return modelAndView;
    }

    @RequestMapping(value = "insertStation.do", method = RequestMethod.GET)
    public ModelAndView insertStation(@RequestParam(value = ROUTE_NUMBER) String routeNumber) {
        return new ModelAndView(PAGE_INSERT_STATION, ROUTE, iRouteService.getRouteByNumber(Integer.parseInt(routeNumber)));
    }

    @RequestMapping(value = "/insertStation.do", method = RequestMethod.POST)
    public ModelAndView insertStation(@RequestParam(value = ROUTE_NUMBER) String routeNumber,
                                      @RequestParam(value = STATION) String station,
                                      @RequestParam(value = STATION_LEAVING_HOUR) String leavingHH,
                                      @RequestParam(value = STATION_LEAVING_MINUTE) String leavingMM,
                                      @RequestParam(value = STATION_ARRIVAL_HOUR) String arrivalHH,
                                      @RequestParam(value = STATION_ARRIVAL_MINUTE) String arrivalMM) {

        ModelAndView modelAndView = new ModelAndView();
        Route route = iRouteService.getRouteByNumber(Integer.parseInt(routeNumber));

        Stations stations = new Stations();
        stations.setRoute(route);
        stations.setStation(station);
        stations.setLeavingTime(LocalTime.of(Integer.parseInt(leavingHH), Integer.parseInt(leavingMM)));
        stations.setArrivalTime(LocalTime.of(Integer.parseInt(arrivalHH), Integer.parseInt(arrivalMM)));

        if ((stations.getArrivalTime().isBefore(stations.getLeavingTime())) && iStationService.insertStation(stations)) {
            modelAndView.setViewName(PAGE_INSERT_STATION);
            modelAndView.addObject(ROUTE, route);
            modelAndView.addObject(INFO, "Stations " + stations.getStation() + " added to the route number " + routeNumber);
            return modelAndView;
        } else {
            modelAndView.setViewName(PAGE_INSERT_STATION);
            modelAndView.addObject(ROUTE, route);
            modelAndView.addObject(INFO, "Error. Route didn't add");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/editStationGet.do", method = RequestMethod.GET)
    public ModelAndView editStation(@RequestParam(value = STATION_ID) String station_id) {
        return new ModelAndView(PAGE_EDIT_STATION, STATION, iStationService.getStationById(Long.parseLong(station_id)));
    }

    @RequestMapping(value = "/editStation.do", method = RequestMethod.POST)
    public ModelAndView editStation(@RequestParam(value = STATION_ID) String station_id,
                                    @RequestParam(value = STATION) String station,
                                    @RequestParam(value = STATION_LEAVING_HOUR) String leavingHH,
                                    @RequestParam(value = STATION_LEAVING_MINUTE) String leavingMM,
                                    @RequestParam(value = STATION_ARRIVAL_HOUR) String arrivalHH,
                                    @RequestParam(value = STATION_ARRIVAL_MINUTE) String arrivalMM) {

        Stations editedStations = iStationService.getStationById(Long.parseLong(station_id));
        if (!station.equals("")) {
            editedStations.setStation(station);
        }
        if (!leavingHH.equals("0") || (leavingHH.equals("0") && !leavingMM.equals("0"))) {
            editedStations.setLeavingTime(LocalTime.of(Integer.parseInt(leavingHH), Integer.parseInt(leavingMM)));
        }
        if (!arrivalHH.equals("0") || (arrivalHH.equals("0") && !arrivalMM.equals("0"))) {
            editedStations.setArrivalTime(LocalTime.of(Integer.parseInt(arrivalHH), Integer.parseInt(arrivalMM)));
        }
        iStationService.updateStation(editedStations);
        List<Stations> listOfStations = iStationService.getStationsByRoute(editedStations.getRoute());
        Collections.sort(listOfStations);
        return new ModelAndView(PAGE_GET_STATIONS_BY_ROUTE, STATION_LIST_OF_STATIONS, listOfStations);
    }

    @RequestMapping(value = "/deleteStation.do", method = RequestMethod.GET)
    public ModelAndView deleteStation(@RequestParam(value = STATION_ID) String station_id) {

        Stations stations = iStationService.getStationById(Long.parseLong(station_id));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_GET_STATIONS_BY_ROUTE);
        modelAndView.addObject(ROUTE, stations.getRoute());

        iStationService.deleteStation(stations.getId());

        modelAndView.addObject(STATION_LIST_OF_STATIONS, iStationService.getStationsByRoute(stations.getRoute()));
        return modelAndView;
    }
}
