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
import java.util.logging.Level;
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
        String stationName = request.getParameter("station");
        LocalTime leavingTime = getLocalTime(request.getParameter("leavingTime").split(":"));
        LocalTime arrivalTime = getLocalTime(request.getParameter("arrivalTime").split(":"));
        if (route != null && leavingTime != null && arrivalTime != null) {
            Stations stations = new Stations();
            stations.setRoute(route);
            stations.setStation(stationName);
            stations.setLeavingTime(leavingTime);
            stations.setArrivalTime(arrivalTime);
            if (iRouteDAO.insertStation(stations)) {
                request.setAttribute(IServletResultInfo.INFO, "Stations " + stationName + " added to the route number " + routeNumber);
                request.getRequestDispatcher("insertStation.jsp").forward(request, response);
            } else {
                request.setAttribute(IServletResultInfo.INFO, "Error. Route didn't add");
                request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
            }
        } else {
            request.setAttribute(IServletResultInfo.INFO, "Wrong route number or time format.");
            request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
        }
    }

    private LocalTime getLocalTime(String[] leavingTime){
        try {
            return LocalTime.of(Integer.parseInt(leavingTime[0]), Integer.parseInt(leavingTime[1]));
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return null;
    }
}
