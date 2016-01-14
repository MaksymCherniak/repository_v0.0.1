package DAO;

import Model.LocalModel.Ticket;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLTicketDAO implements ITicketDAO {
    private static final String FIND_TICKET = "SELECT t FROM Ticket t WHERE t.train LIKE :train AND wagon_id LIKE :wagon " +
            "AND t.seat LIKE :seat";
    private static final String TRAIN = "train";
    private static final String WAGON = "wagon";
    private static final String SEAT = "seat";
    private static Logger log = Logger.getLogger(MySQLTicketDAO.class.getName());
    private static EntityManager entityManager;

    public MySQLTicketDAO() {
        entityManager = HibernateUtil.getEm();
    }

    public static void setEntityManager(EntityManager entityManager) {
        MySQLTicketDAO.entityManager = entityManager;
    }

    public boolean insertTicket(Ticket ticket) {
        try {
            if (findTicket(ticket) == null) {
                entityManager.getTransaction().begin();
                entityManager.persist(ticket);
                entityManager.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception: ", e);
            entityManager.getTransaction().rollback();
        }
        return false;
    }

    public Ticket findTicketByID(Integer id) {
        Ticket ticket = entityManager.find(Ticket.class, id);
        if (ticket != null) {
            return ticket;
        } else {
            log.info("Ticket not found");
            return null;
        }
    }

    public boolean delete(Integer id) {
        Ticket ticket = findTicketByID(id);
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

    public Ticket findTicket(Ticket ticket) {
        List<Ticket> userList = entityManager.createQuery(FIND_TICKET).setParameter(TRAIN, ticket.getTrain())
                .setParameter(WAGON, ticket.getWagon().getId()).setParameter(SEAT, ticket.getSeat()).getResultList();
        if (userList.size() != 0) {
            return userList.get(0);
        } else {
            log.info("Ticket not found");
            return null;
        }
    }
}