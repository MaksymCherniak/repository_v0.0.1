package BookingTool.Controller;

import BookingTool.DAO.Service.IRouteDAO;
import BookingTool.DAO.Service.ITrainDAO;
import BookingTool.DAO.Service.IWagonDAO;
import BookingTool.Entity.Route;
import BookingTool.Entity.Seat;
import BookingTool.Entity.Train;
import BookingTool.Model.LocalModel.Wagon;
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
    private IRouteDAO iRouteDAO;
    @Autowired
    private IWagonDAO iWagonDAO;
    @Autowired
    private ITrainDAO iTrainDAO;

    @RequestMapping(value = "/insertWagon.do", method = RequestMethod.POST)
    public ModelAndView insertWagon(@RequestParam(value = ROUTE_NUMBER) String routeNumber,
                                    @RequestParam(value = "comfortableNumbers") String comfortableNumbers,
                                    @RequestParam(value = "compartmentNumbers") String compartmentNumbers,
                                    @RequestParam(value = "economyNumbers") String economyNumbers) {

        ModelAndView modelAndView = new ModelAndView();
        Route route = iRouteDAO.getRouteByNumber(Integer.parseInt(routeNumber));
        List<Train> listOfTrains = iTrainDAO.getAllTrainsByRoute(route);
        List<Integer> listOfComfortableWagons = getListOfWagonsNumbers(comfortableNumbers.split(" "));
        List<Integer> listOfCompartmentWagons = getListOfWagonsNumbers(compartmentNumbers.split(" "));
        List<Integer> listOfEconomyWagons = getListOfWagonsNumbers(economyNumbers.split(" "));
        for (Train train : listOfTrains) {
            for (Integer number : listOfComfortableWagons) {
                insertWagon(train, number, COMFORTABLE);
            }
            for (Integer number : listOfCompartmentWagons) {
                insertWagon(train, number, COMPARTMENT);
            }
            for (Integer number : listOfEconomyWagons) {
                insertWagon(train, number, ECONOMY);
            }
        }
        modelAndView.setViewName(PAGE_INFO);
        modelAndView.addObject(INFO, "Wagons for route " + route.getRouteNumber() + " is initialize");
        return modelAndView;
    }

    @RequestMapping(value = "/printWagon.do", method = RequestMethod.GET)
    public ModelAndView printWagon(@RequestParam(value = ID) String wagon_id,
                                   @RequestParam(value = TRAIN) String train_id) {

        ModelAndView modelAndView = new ModelAndView();
        Wagon wagon = iWagonDAO.getWagonById(Long.parseLong(wagon_id));
        Train train = iTrainDAO.getTrainById(Long.parseLong(train_id));
        List<Seat> listOfSeats = iWagonDAO.getAllSeats(wagon);
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
        iWagonDAO.insertWagon(wagon);
    }

    private List<Integer> getListOfWagonsNumbers(String[] numbers) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < numbers.length; i++) {
            list.add(Integer.parseInt(numbers[i]));
        }
        return list;
    }
}
