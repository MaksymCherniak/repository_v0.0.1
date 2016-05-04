package BookingTool.Controller;

import BookingTool.DAO.Service.ITicketDAO;
import BookingTool.DAO.Service.IUserDAO;
import BookingTool.DAO.Service.IWagonDAO;
import BookingTool.Entity.Seat;
import BookingTool.Entity.Ticket;
import BookingTool.Model.LocalModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
public class TicketController implements IControllerStaticValues {
    private static Logger log = Logger.getLogger(TicketController.class.getName());
    @Autowired
    private IWagonDAO iWagonDAO;
    @Autowired
    private IUserDAO iUserDAO;
    @Autowired
    private ITicketDAO iTicketDAO;

    @RequestMapping(value = "/buyTicket.do", method = RequestMethod.POST)
    public ModelAndView buyTicket(@RequestParam(value = SEAT) String seat_id,
                                  @RequestParam(value = NAME) String name,
                                  @RequestParam(value = SURNAME) String surname) {

        ModelAndView modelAndView = new ModelAndView();
        Seat seat = iWagonDAO.getSeatById(Long.parseLong(seat_id));
        Ticket ticket = new Ticket();
        ticket.setWagon(seat.getWagon());
        ticket.setSeat(seat.getNumber());
        ticket.setTrain(seat.getWagon().getTrain().getRoute().getRouteNumber());
        User user = new User(name, surname);
        iUserDAO.insertUser(user);
        ticket.setUser(user);
        iTicketDAO.insertTicket(ticket);
        iWagonDAO.updateSeat(ticket);

        log.info("Thanks for your order. Your seat is number " + seat.getNumber() + ", Ticket number: " + ticket.getIndex());
        modelAndView.addObject("info", "Thanks for your order. Your seat is number " + ticket.getSeat()
                + ". Wagon number: " + ticket.getWagon().getNumber() + ". Train number: "
                + seat.getWagon().getTrain().getRoute().getRouteNumber() + ", Ticket number: " + ticket.getIndex());
        modelAndView.setViewName(PAGE_INFO);
        return modelAndView;
    }

    @RequestMapping(value = "/buyTicket.do", method = RequestMethod.GET)
    public ModelAndView buyTicket(@RequestParam(value = SEAT) String seat_id) {
        ModelAndView modelAndView = new ModelAndView();
        Seat seat = iWagonDAO.getSeatById(Long.parseLong(seat_id));
        modelAndView.addObject(SEAT, seat);
        modelAndView.setViewName(PAGE_BOOK_SEAT);
        return modelAndView;
    }
}
