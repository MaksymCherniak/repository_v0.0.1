package BookingTool.Interceptors;

import BookingTool.Controller.IControllerStaticValues;
import BookingTool.DAO.Service.IRouteService;
import BookingTool.DAO.Service.IStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckStationName extends HandlerInterceptorAdapter implements IControllerStaticValues {
    @Autowired
    private IStationService iStationService;
    @Autowired
    private IRouteService iRouteService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String leavingStation = request.getParameter(STATION_LEAVING_STATION);
        String arrivalStation = request.getParameter(STATION_ARRIVAL_STATION);
        if (iStationService.getStationByName(leavingStation) == null) {
            return sendResponse("Station " + leavingStation + " not found.", request, response);
        }
        if (iStationService.getStationByName(arrivalStation) == null) {
            return sendResponse("Station " + arrivalStation + " not found.", request, response);
        }
        if (iRouteService.getAllRoutes(leavingStation, arrivalStation) == null) {
            return sendResponse("Routes not found", request, response);
        }
        return true;
    }

    private boolean sendResponse(String info, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute(INFO, info);
        request.getRequestDispatcher(JSP_PAGE_GET_ROUTE).forward(request, response);
        return false;
    }
}
