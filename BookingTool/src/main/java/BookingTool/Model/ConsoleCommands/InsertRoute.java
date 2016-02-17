package BookingTool.Model.ConsoleCommands;

import BookingTool.DAO.IRouteDAO;
import BookingTool.DAO.MySqlDaoFactory;
import BookingTool.Model.LocalModel.Route;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertRoute implements ICommand {
    private IRouteDAO iRouteDAO;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Logger log = Logger.getLogger(InsertRoute.class.getName());
    private static final String name = "insertroute";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 4) {
            try {
                insertRoute(parts[1], parts[2], Integer.parseInt(parts[3]));
            } catch (IOException e) {
                log.log(Level.INFO, "Exception: ", e);
            }
        } else {
            log.info("Wrong command");
        }
    }

    public void insertRoute(String leavingStation, String arrivalStation, int routeNumber) throws IOException {
        Route route = new Route();
        route.setLeavingStation(leavingStation);
        route.setArrivalStation(arrivalStation);
        route.setRouteNumber(routeNumber);
        try {
            System.out.println("Enter leaving time. Like: \"HH:MM\"");
            route = localTimeDateInit(reader.readLine().split(":"), route, "leavingTime");
            System.out.println("Enter arrival time. Like: \"HH:MM\"");
            route = localTimeDateInit(reader.readLine().split(":"), route, "arrivalTime");
            System.out.println("Enter date start cruising. Like: \"DD-MM-YYYY\"");
            route = localTimeDateInit(reader.readLine().split("-"), route, "dateStart");
            iRouteDAO.insertRoute(route);
        } catch (DateTimeException e) {
            log.log(Level.INFO, "Exception: ", e);
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- insert route.");
        System.out.println("     " + name + " \"leaving station\" \"arrival station\" \"route number\"");
    }

    private Route localTimeDateInit(String[] parts, Route route, String op) {
        if (parts.length == 2 && op.equals("leavingTime")) {
            route.setLeavingTime(LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            return route;
        } else if (parts.length == 2 && op.equals("arrivalTime")) {
            route.setArrivalTime(LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            return route;
        } else if (parts.length == 3 && op.equals("dateStart")) {
            route.setRouteDate(LocalDate.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0])));
            return route;
        } else {
            log.info("Wrong command");
            return route;
        }
    }

    public IRouteDAO getiRouteDAO() {
        return iRouteDAO;
    }

    public void setiRouteDAO(IRouteDAO iRouteDAO) {
        this.iRouteDAO = iRouteDAO;
    }
}
