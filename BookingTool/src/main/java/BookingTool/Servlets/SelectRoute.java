package BookingTool.Servlets;

import BookingTool.DAO.Service.IRouteDAO;
import BookingTool.DAO.Service.ITrainDAO;
import BookingTool.Entity.Route;
import BookingTool.Entity.Train;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SelectRoute extends HttpServlet {
    private static Logger log = Logger.getLogger(SelectRoute.class.getName());
    private IRouteDAO iRouteDAO;
    private ITrainDAO iTrainDAO;
    private WebApplicationContext webApplicationContext;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iRouteDAO = (IRouteDAO) webApplicationContext.getBean("IRouteDAO");
        iTrainDAO = (ITrainDAO) webApplicationContext.getBean("ITrainDAO");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String leavingStation = request.getParameter("leavingStation");
        String arrivalStation = request.getParameter("arrivalStation");
        LocalDate routeDate = LocalDate.of(Integer.parseInt(request.getParameter("routeYY"))
                , Integer.parseInt(request.getParameter("routeMM")), Integer.parseInt(request.getParameter("routeDD")));
        List<Route> listOfRoutes = iRouteDAO.getAllRoutes(leavingStation, arrivalStation);
        List<Train> listOfTrains = new ArrayList<Train>();
        for (int i = 0; i < listOfRoutes.size(); i++) {
            Train train = iTrainDAO.getTrainByDateAndRoute(routeDate, listOfRoutes.get(i));
            if (train != null) {
                listOfTrains.add(train);
            }
        }
        if (listOfTrains.size() != 0) {
            request.setAttribute("listOfTrains", listOfTrains);
            request.getRequestDispatcher("printRoutes.jsp").forward(request, response);
        }
        response.sendRedirect("pageInfo.jsp");
    }
}
