package DAO;

import Model.LocalModel.Ticket;

import java.util.List;

public interface ITicketDAO {
    boolean insertTicket(Ticket ticket);

    Ticket find(Integer id);

    boolean delete(Integer id);

    List getAllTickets();
}