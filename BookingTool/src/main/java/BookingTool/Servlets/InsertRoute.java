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
        Route route = new Route();
        route.setRouteNumber(Integer.parseInt(request.getParameter("routeNumber")));
        route.setLeavingStation(request.getParameter("leavingStation"));
        route.setArrivalStation(request.getParameter("arrivalStation"));

        if (iRouteDAO.insertRoute(route)) {
            request.setAttribute(IServletResultInfo.INFO, route.toString() + " added");
            request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
        } else {
            request.setAttribute(IServletResultInfo.INFO, route.toString() + " didn't add");
            request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
        }
    }


}
