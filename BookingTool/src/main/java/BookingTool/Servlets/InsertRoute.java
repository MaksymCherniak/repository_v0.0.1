package BookingTool.Servlets;

import BookingTool.DAO.Service.IRouteDAO;
import BookingTool.Entity.Route;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertRoute extends HttpServlet {
    private static Logger log = Logger.getLogger(InsertRoute.class.getName());
    private IRouteDAO iRouteDAO;
    private WebApplicationContext webApplicationContext;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iRouteDAO = (IRouteDAO) webApplicationContext.getBean("IRouteDAO");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer routeNumber = routeNumberCheck(request.getParameter("routeNumber"));
        if (routeNumber != null) {
            Route route = new Route();
            route.setRouteNumber(routeNumber);
            route.setLeavingStation(request.getParameter("leavingStation"));
            route.setArrivalStation(request.getParameter("arrivalStation"));
            if (iRouteDAO.insertRoute(route)) {
                request.setAttribute(IServletResultInfo.INFO, route.toString() + " added");
                request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
            } else {
                request.setAttribute(IServletResultInfo.INFO, route.toString() + " didn't add");
                request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
            }
        } else {
            request.setAttribute(IServletResultInfo.INFO, "Wrong route number format");
            request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
        }
    }

    private Integer routeNumberCheck(String routeNumber) {
        try {
            return Integer.parseInt(routeNumber);
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
            return null;
        }
    }
}
