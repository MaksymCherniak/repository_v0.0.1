package BookingTool.Servlets;

import BookingTool.DAO.Service.IRouteDAO;
import BookingTool.DAO.Service.ITrainDAO;
import BookingTool.Entity.Route;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class InsertTrain extends HttpServlet {
    private static Logger log = Logger.getLogger(InsertTrain.class.getName());
    private IRouteDAO iRouteDAO;
    private ITrainDAO iTrainDAO;
    private WebApplicationContext webApplicationContext;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iRouteDAO = (IRouteDAO) webApplicationContext.getBean("IRouteDAO");
        iTrainDAO = (ITrainDAO) webApplicationContext.getBean("ITrainDAO");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DayOfWeek> daysOfWeek = getDaysOfWeekList(request.getParameterValues("days"));
        Route route = iRouteDAO.getRouteByNumber(Integer.parseInt(request.getParameter("routeNumber")));
        if (route != null && daysOfWeek != null) {
            LocalDate startDate = LocalDate.of(Integer.parseInt(request.getParameter("startYY"))
                    , Integer.parseInt(request.getParameter("startMM")), Integer.parseInt(request.getParameter("startDD")));
            iTrainDAO.insertTrain(route, startDate, daysOfWeek);
            request.setAttribute(IServletResultInfo.INFO, "Trains added.");
            request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
        } else {
            request.setAttribute(IServletResultInfo.INFO, "Wrong route number or date format.");
            request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
        }
    }

    private List<DayOfWeek> getDaysOfWeekList(String[] days) {
        List<DayOfWeek> daysOfWeek = new ArrayList<DayOfWeek>();
        if (days.length == 1 && days[0].equals("allDays")) {
            daysOfWeek.add(DayOfWeek.MONDAY);
            daysOfWeek.add(DayOfWeek.TUESDAY);
            daysOfWeek.add(DayOfWeek.WEDNESDAY);
            daysOfWeek.add(DayOfWeek.THURSDAY);
            daysOfWeek.add(DayOfWeek.FRIDAY);
            daysOfWeek.add(DayOfWeek.SATURDAY);
            daysOfWeek.add(DayOfWeek.SUNDAY);
            return daysOfWeek;
        } else {
            for (int i = 0; i < days.length; i++) {
                if (days[i].equals("monday")) {
                    daysOfWeek.add(DayOfWeek.MONDAY);
                } else if (days[i].equals("tuesday")) {
                    daysOfWeek.add(DayOfWeek.TUESDAY);
                } else if (days[i].equals("wednesday")) {
                    daysOfWeek.add(DayOfWeek.WEDNESDAY);
                } else if (days[i].equals("thursday")) {
                    daysOfWeek.add(DayOfWeek.THURSDAY);
                } else if (days[i].equals("friday")) {
                    daysOfWeek.add(DayOfWeek.FRIDAY);
                } else if (days[i].equals("saturday")) {
                    daysOfWeek.add(DayOfWeek.SATURDAY);
                } else if (days[i].equals("sunday")) {
                    daysOfWeek.add(DayOfWeek.SUNDAY);
                }
            }
            return daysOfWeek;
        }
    }
}
