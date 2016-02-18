package BookingTool.DAO.Service;

import BookingTool.Model.LocalModel.Ticket;

import java.util.List;

public interface ITicketDAO {
    boolean insertTicket(Ticket ticket);

    Ticket findTicketByID(Long id);

    Ticket findTicket(Ticket ticket);

    boolean delete(Long id);

    List getAllTickets();
}