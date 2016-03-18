package BookingTool.Servlets;

import BookingTool.DAO.Service.ITicketDAO;
import BookingTool.DAO.Service.IUserDAO;
import BookingTool.DAO.Service.IWagonDAO;
import BookingTool.Entity.Ticket;
import BookingTool.Entity.User;
import BookingTool.Entity.Wagon;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class BuyTicket extends HttpServlet {
    private static Logger log = Logger.getLogger(BuyTicket.class.getName());
    private WebApplicationContext webApplicationContext;
    private IWagonDAO iWagonDAO;
    private IUserDAO iUserDAO;
    private ITicketDAO iTicketDAO;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iWagonDAO = (IWagonDAO) webApplicationContext.getBean("IWagonDAO");
        iUserDAO = (IUserDAO) webApplicationContext.getBean("IUserDAO");
        iTicketDAO = (ITicketDAO) webApplicationContext.getBean("ITicketDAO");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Wagon wagon = iWagonDAO.findByNumberAndTrainId(Integer.parseInt(request.getParameter("wagonNumber")), 9);
        if (wagon != null) {
            Ticket ticket = new Ticket();
            ticket.setWagon(wagon);
            ticket.setSeat(Integer.parseInt(request.getParameter("seatNumber")));
            ticket.setTrain(Integer.parseInt(request.getParameter("trainNumber")));
            User user = new User(request.getParameter("lastName"), request.getParameter("firstName"));
            if (iWagonDAO.checkSeatAvailable(ticket)) {
                iUserDAO.insertUser(user);
                ticket.setUser(user);
                iTicketDAO.insertTicket(ticket);
                iWagonDAO.updateSeat(ticket);
                log.info("Thanks for your order. Your seat is number " + request.getParameter("seatNumber")
                        + ", Ticket number: " + ticket.getIndex());
                request.setAttribute(IServletResultInfo.INFO, "Thanks for your order. Your seat is number " + request.getParameter("seatNumber")
                        + ". Wagon number: " + request.getParameter("wagonNumber") + ". Train number: "
                        + request.getParameter("trainNumber") + ", Ticket number: " + ticket.getIndex());
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
