package BookingTool.DAO.Impl;

import BookingTool.DAO.Repository.TicketRepository;
import BookingTool.DAO.Service.ITicketService;
import BookingTool.Entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;

public class MySQLTicketImpl implements ITicketService {
    @Autowired
    private TicketRepository ticketRepository;
    private static Logger log = Logger.getLogger(MySQLTicketImpl.class.getName());

    public MySQLTicketImpl() {
    }

    public boolean insertTicket(Ticket ticket) {
        if (findTicket(ticket) == null) {
            ticketRepository.saveAndFlush(ticket);
            return true;
        }
        return false;
    }

    public Ticket findTicketByID(Long id) {
        Ticket ticket = ticketRepository.getOne(id);
        if (ticket != null) {
            return ticket;
        } else {
            log.info("Ticket not found");
            return null;
        }
    }

    public boolean delete(Long id) {
        Ticket ticket = findTicketByID(id);
        if (ticket != null) {
            ticketRepository.delete(id);
            log.info("Ticket " + id + " removed");
            return true;
        } else {
            return false;
        }
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket findTicket(Ticket ticket) {
        Ticket ticket1 = ticketRepository.findTicket(ticket.getTrain(), ticket.getWagon().getId(), ticket.getSeat());
        if (ticket1 != null) {
            return ticket1;
        } else {
            log.info("Ticket not found");
            return null;
        }
    }
}