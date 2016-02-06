package BookingTool.DAO;

import BookingTool.Model.LocalModel.Ticket;

import java.util.List;

public interface ITicketDAO {
    boolean insertTicket(Ticket ticket);

    Ticket findTicketByID(Integer id);

    Ticket findTicket(Ticket ticket);

    boolean delete(Integer id);

    List getAllTickets();
}