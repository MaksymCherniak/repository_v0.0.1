package BookingTool.Model.ConsoleCommands;

import BookingTool.DAO.Service.IRouteDAO;
import BookingTool.Model.LocalModel.Route;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

public class PrintRoute implements ICommand {
    private IRouteDAO iRouteDAO;
    private static Logger log = Logger.getLogger(PrintRoute.class.getName());
    private static final String name = "printroute";
    private String[] parts;
    private String[] date;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 4) {
            Route route = new Route();
            route.setLeavingStation(parts[1]);
            route.setArrivalStation(parts[2]);
            date = parts[3].split("-");
            route.setRouteDate(LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0])));
            List<Route> list = iRouteDAO.getAllRoutes(route);
            for (Route route1 : list) {
                System.out.println(route1.toString());
            }
        } else {
            log.info("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print all routes.");
    }

    public IRouteDAO getiRouteDAO() {
        return iRouteDAO;
    }

    public void setiRouteDAO(IRouteDAO iRouteDAO) {
        this.iRouteDAO = iRouteDAO;
    }
}
