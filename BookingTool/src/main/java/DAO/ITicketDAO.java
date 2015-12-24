package DAO;

import Model.LocalModel.Ticket;

import java.util.List;

/**
 * Created by Max on 24.12.2015.
 */
public interface ITicketDAO {
    int insertTicket(Ticket ticket);
    Ticket find(int number);
    boolean delete(Ticket ticket);
    List getAllTickets();
}
