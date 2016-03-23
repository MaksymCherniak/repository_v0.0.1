package BookingTool.Servlets;

import BookingTool.DAO.Service.IRouteDAO;
import BookingTool.Entity.Route;
import BookingTool.Entity.Stations;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.Logger;

public class InsertStation extends HttpServlet {
    private static Logger log = Logger.getLogger(InsertStation.class.getName());
    private IRouteDAO iRouteDAO;
    private WebApplicationContext webApplicationContext;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iRouteDAO = (IRouteDAO) webApplicationContext.getBean("IRouteDAO");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer routeNumber = Integer.parseInt(request.getParameter("routeNumber"));
        Route route = iRouteDAO.getRouteByNumber(routeNumber);
        if (route != null) {
            Stations stations = new Stations();
            stations.setRoute(route);
            stations.setStation(request.getParameter("station"));
            stations.setLeavingTime(LocalTime.of(Integer.parseInt(request.getParameter("leavingHH"))
                    , Integer.parseInt(request.getParameter("leavingMM"))));
            stations.setArrivalTime(LocalTime.of(Integer.parseInt(request.getParameter("arrivalHH"))
                    , Integer.parseInt(request.getParameter("arrivalMM"))));

            if ((stations.getArrivalTime().isBefore(stations.getLeavingTime())) && iRouteDAO.insertStation(stations)) {
                request.setAttribute(IServletResultInfo.INFO, "Stations " + stations.getStation() + " added to the route number " + routeNumber);
                request.getRequestDispatcher("insertStation.jsp").forward(request, response);
            } else {
                request.setAttribute(IServletResultInfo.INFO, "Error. Route didn't add");
                request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
            }
        } else {
            request.setAttribute(IServletResultInfo.INFO, "Wrong route number");
            request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
        }
    }
}
