package BookingTool.Servlets;

import BookingTool.DAO.Service.IRouteDAO;
import BookingTool.DAO.Service.ITrainDAO;
import BookingTool.DAO.Service.IWagonDAO;
import BookingTool.Entity.Route;
import BookingTool.Entity.Train;
import BookingTool.Model.LocalModel.Wagon;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InsertWagon extends HttpServlet {
    private IWagonDAO iWagonDAO;
    private IRouteDAO iRouteDAO;
    private ITrainDAO iTrainDAO;
    private WebApplicationContext webApplicationContext;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iWagonDAO = (IWagonDAO) webApplicationContext.getBean("IWagonDAO");
        iRouteDAO = (IRouteDAO) webApplicationContext.getBean("IRouteDAO");
        iTrainDAO = (ITrainDAO) webApplicationContext.getBean("ITrainDAO");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Route route = iRouteDAO.getRouteByNumber(Integer.parseInt(request.getParameter("routeNumber")));
        List<Train> listOfTrains = iTrainDAO.getAllTrainsByRoute(route);
        List<Integer> listOfComfortableWagons = getListOfWagonsNumbers(request.getParameter("comfortableNumbers").split(" "));
        List<Integer> listOfCompartmentWagons = getListOfWagonsNumbers(request.getParameter("compartmentNumbers").split(" "));
        List<Integer> listOfEconomyWagons = getListOfWagonsNumbers(request.getParameter("economyNumbers").split(" "));
        for (Train train : listOfTrains) {
            for (Integer number : listOfComfortableWagons) {
                Wagon wagon = new Wagon();
                wagon.setTrain(train);
                wagon.setNumber(number);
                wagon.setWagonType("comfortable");
                iWagonDAO.insertWagon(wagon);
            }
            for (Integer number : listOfCompartmentWagons) {
                Wagon wagon = new Wagon();
                wagon.setTrain(train);
                wagon.setNumber(number);
                wagon.setWagonType("compartment");
                iWagonDAO.insertWagon(wagon);
            }
            for (Integer number : listOfEconomyWagons) {
                Wagon wagon = new Wagon();
                wagon.setTrain(train);
                wagon.setNumber(number);
                wagon.setWagonType("economy");
                iWagonDAO.insertWagon(wagon);
            }
        }
        request.setAttribute(IServletResultInfo.INFO, "Wagons for route " + route.getRouteNumber() + " is initialize");
        request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
    }

    private List<Integer> getListOfWagonsNumbers(String[] numbers) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < numbers.length; i++) {
            list.add(Integer.parseInt(numbers[i]));
        }
        return list;
    }
}
