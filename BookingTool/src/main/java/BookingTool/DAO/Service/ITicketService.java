package BookingTool.DAO.Service;

import BookingTool.Entity.Ticket;

import java.util.List;

public interface ITicketService {
    boolean insertTicket(Ticket ticket);

    Ticket findTicketByID(Long id);

    Ticket findTicket(Ticket ticket);

    boolean delete(Long id);

    List getAllTickets();
}