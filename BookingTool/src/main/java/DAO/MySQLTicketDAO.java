package DAO;

import Model.LocalModel.Ticket;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLTicketDAO implements ITicketDAO {
    private static Logger log = Logger.getLogger(MySQLTicketDAO.class.getName());
    private static EntityManager entityManager;

    public MySQLTicketDAO() {
        entityManager = HibernateUtil.getEm();
    }

    public int insertTicket(Ticket ticket) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(ticket);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception: ", e);
            entityManager.getTransaction().rollback();
        }
        return 0;
    }

    public Ticket find(Integer id) {
        Ticket ticket = entityManager.find(Ticket.class, id);
        if (ticket != null) {
            return ticket;
        } else {
            log.warning("Ticket " + id + " not found");
            return ticket;
        }
    }

    public boolean delete(Integer id) {
        Ticket ticket = find(id);
        if (ticket != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(ticket);
            entityManager.getTransaction().commit();
            log.info("Ticket " + id + " removed");
            return true;
        } else {
            return false;
        }
    }

    public List<Ticket> getAllTickets() {
        return entityManager.createNamedQuery("Ticket.getAll", Ticket.class).getResultList();
    }
}