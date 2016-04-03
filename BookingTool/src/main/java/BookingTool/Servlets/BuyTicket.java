package BookingTool.Servlets;

import BookingTool.DAO.Service.IRouteDAO;
import BookingTool.DAO.Service.ITicketDAO;
import BookingTool.DAO.Service.IUserDAO;
import BookingTool.DAO.Service.IWagonDAO;
import BookingTool.Entity.*;
import BookingTool.Model.LocalModel.Wagon;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long seatId = Long.parseLong(request.getParameter("seat"));
        Seat seat = iWagonDAO.getSeatById(seatId);
        request.setAttribute("seat", seat);
        request.getRequestDispatcher("bookSeat.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long seatId = Long.parseLong(request.getParameter("seat"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        Seat seat = iWagonDAO.getSeatById(seatId);
        Ticket ticket = new Ticket();
        ticket.setWagon(seat.getWagon());
        ticket.setSeat(seat.getNumber());
        ticket.setTrain(seat.getWagon().getTrain().getRoute().getRouteNumber());
        User user = new User(name, surname);
        iUserDAO.insertUser(user);
        ticket.setUser(user);
        iTicketDAO.insertTicket(ticket);
        iWagonDAO.updateSeat(ticket);

        log.info("Thanks for your order. Your seat is number " + request.getParameter("seatNumber")
                + ", Ticket number: " + ticket.getIndex());
        request.setAttribute(IServletResultInfo.INFO, "Thanks for your order. Your seat is number " + ticket.getSeat()
                + ". Wagon number: " + ticket.getWagon().getNumber() + ". Train number: "
                + seat.getWagon().getTrain().getRoute().getRouteNumber() + ", Ticket number: " + ticket.getIndex());
        request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
    }
}
