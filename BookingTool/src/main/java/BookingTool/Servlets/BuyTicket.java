package BookingTool.Servlets;

import BookingTool.DAO.Service.IRouteDAO;
import BookingTool.DAO.Service.ITicketDAO;
import BookingTool.DAO.Service.IUserDAO;
import BookingTool.DAO.Service.IWagonDAO;
import BookingTool.Entity.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

public class BuyTicket extends HttpServlet {
    private static Logger log = Logger.getLogger(BuyTicket.class.getName());
    private WebApplicationContext webApplicationContext;
    private IRouteDAO iRouteDAO;
    private IWagonDAO iWagonDAO;
    private IUserDAO iUserDAO;
    private ITicketDAO iTicketDAO;
    private Route route;
    private Wagon wagon;
    private Train train;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iWagonDAO = (IWagonDAO) webApplicationContext.getBean("IWagonDAO");
        iUserDAO = (IUserDAO) webApplicationContext.getBean("IUserDAO");
        iTicketDAO = (ITicketDAO) webApplicationContext.getBean("ITicketDAO");
        iRouteDAO = (IRouteDAO) webApplicationContext.getBean("IRouteDAO");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        route = iRouteDAO.getRouteByNumber(Integer.parseInt(request.getParameter("trainNumber")));
        if (route != null) {
            LocalDate date = LocalDate.of(Integer.parseInt(request.getParameter("dateYY"))
                    , Integer.parseInt(request.getParameter("dateMM")), Integer.parseInt(request.getParameter("dateDD")));
            train = iRouteDAO.getTrainByDateAndRoute(date, route);
        } else {
            request.setAttribute(IServletResultInfo.INFO, "Train not found");
            request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
        }
        if (train != null) {
            wagon = iWagonDAO.findByNumberAndTrainId(Integer.parseInt(request.getParameter("wagonNumber"))
                    , train.getId());
        } else {
            request.setAttribute(IServletResultInfo.INFO, "Wrong date");
            request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
        }
        if (wagon != null) {
            Ticket ticket = new Ticket();
            ticket.setWagon(wagon);
            ticket.setTrain(route.getRouteNumber());
            ticket.setSeat(Integer.parseInt(request.getParameter("seatNumber")));
            User user = new User(request.getParameter("lastName"), request.getParameter("firstName"));
            if (iWagonDAO.checkSeatAvailable(ticket)) {
                iUserDAO.insertUser(user);
                ticket.setUser(user);
                iTicketDAO.insertTicket(ticket);
                iWagonDAO.updateSeat(ticket);
                log.info("Thanks for your order. Your seat is number " + request.getParameter("seatNumber")
                        + ", Ticket number: " + ticket.getIndex());
                request.setAttribute(IServletResultInfo.INFO, "Thanks for your order. Your seat is number " + ticket.getSeat()
                        + ". Wagon number: " + ticket.getWagon().getNumber() + ". Train number: "
                        + route.getRouteNumber() + ", Ticket number: " + ticket.getIndex());
                request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
            } else {
                request.setAttribute(IServletResultInfo.INFO, "Seat occupied");
                request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
            }
        } else {
            request.setAttribute(IServletResultInfo.INFO, "Wagon not found");
            request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
        }
    }
}
