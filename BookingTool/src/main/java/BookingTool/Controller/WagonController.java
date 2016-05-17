package BookingTool.Controller;

import BookingTool.DAO.Service.IRouteService;
import BookingTool.DAO.Service.ITrainService;
import BookingTool.DAO.Service.IWagonService;
import BookingTool.Entity.Route;
import BookingTool.Entity.Seat;
import BookingTool.Entity.Train;
import BookingTool.Entity.Wagon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WagonController implements IControllerStaticValues {
    @Autowired
    private IRouteService iRouteService;
    @Autowired
    private IWagonService iWagonService;
    @Autowired
    private ITrainService iTrainService;

    @RequestMapping(value = "/insertWagon.do", method = RequestMethod.GET)
    public ModelAndView insertWagon(@RequestParam(value = ROUTE_NUMBER) String routeNumber) {
        return new ModelAndView(PAGE_INSERT_WAGON, ROUTE, iRouteService.getRouteByNumber(Integer.parseInt(routeNumber)));
    }

    @RequestMapping(value = "/insertWagon.do", method = RequestMethod.POST)
    public ModelAndView insertWagon(@RequestParam(value = ROUTE_NUMBER) String routeNumber,
                                    @RequestParam(value = "comfortableNumbers") String comfortableNumbers,
                                    @RequestParam(value = "compartmentNumbers") String compartmentNumbers,
                                    @RequestParam(value = "economyNumbers") String economyNumbers) {

        Route route = iRouteService.getRouteByNumber(Integer.parseInt(routeNumber));
        List<Train> listOfTrains = iTrainService.getAllTrainsByRoute(route);
        List<Integer> listOfComfortableWagons = getListOfWagonsNumbers(comfortableNumbers.split(" "));
        List<Integer> listOfCompartmentWagons = getListOfWagonsNumbers(compartmentNumbers.split(" "));
        List<Integer> listOfEconomyWagons = getListOfWagonsNumbers(economyNumbers.split(" "));
        for (Train train : listOfTrains) {
            for (Integer number : listOfComfortableWagons) {
                insertWagon(train, number, WAGON_COMFORTABLE);
            }
            for (Integer number : listOfCompartmentWagons) {
                insertWagon(train, number, WAGON_COMPARTMENT);
            }
            for (Integer number : listOfEconomyWagons) {
                insertWagon(train, number, WAGON_ECONOMY);
            }
        }
        return new ModelAndView(PAGE_MAIN, INFO, "Wagons for route " + route.getRouteNumber() + " is initialize");
    }

    @RequestMapping(value = "/printWagon.do", method = RequestMethod.GET)
    public ModelAndView printWagon(@RequestParam(value = ID) String wagon_id,
                                   @RequestParam(value = TRAIN) String train_id) {

        ModelAndView modelAndView = new ModelAndView();
        Wagon wagon = iWagonService.getWagonById(Long.parseLong(wagon_id));
        Train train = iTrainService.getTrainById(Long.parseLong(train_id));
        List<Seat> listOfSeats = iWagonService.getAllSeats(wagon);
        modelAndView.setViewName(PAGE_PRINT_WAGON);
        modelAndView.addObject(TRAIN, train);
        modelAndView.addObject("listOfSeats", listOfSeats);
        return modelAndView;
    }

    private void insertWagon(Train train, int number, String type) {
        Wagon wagon = new Wagon();
        wagon.setTrain(train);
        wagon.setNumber(number);
        wagon.setWagonType(type);
        iWagonService.insertWagon(wagon);
    }

    private List<Integer> getListOfWagonsNumbers(String[] numbers) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < numbers.length; i++) {
            list.add(Integer.parseInt(numbers[i]));
        }
        return list;
    }
}
