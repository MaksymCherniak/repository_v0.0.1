package DAO;

import Model.LocalModel.Ticket;

import java.util.List;

public interface ITicketDAO {
    int insertTicket(Ticket ticket);

    Ticket find(Integer id);

    boolean delete(Integer id);

    List getAllTickets();
}