package DAO;

import Model.LocalModel.Seat;
import Model.LocalModel.Ticket;
import Model.LocalModel.Wagon;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLWagonDAO implements IWagonDAO {
    private static EntityManager entityManager;
    private static Logger log = Logger.getLogger(MySQLWagonDAO.class.getName());

    public MySQLWagonDAO() {
        entityManager = HibernateUtil.getEm();
    }

    public boolean updateSeat(Ticket ticket) {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("UPDATE Seat c set c.status='OCCUPIED' WHERE wagon_id LIKE :wagon AND c.number LIKE :number")
                    .setParameter("wagon", ticket.getWagon().getId()).setParameter("number", ticket.getSeat()).executeUpdate();
            entityManager.createQuery("UPDATE Wagon c set c.freeSeats=" + getFreeSeats(ticket.getWagon()) + " WHERE c.number LIKE :number")
                    .setParameter("number", ticket.getWagon().getNumber()).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
        return false;
    }

    public boolean updateWagon(Ticket ticket) {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("UPDATE Seat c set c.status='FREE' WHERE wagon_id LIKE :wagon AND c.number LIKE :number")
                    .setParameter("wagon", ticket.getWagon().getId()).setParameter("number", ticket.getSeat()).executeUpdate();
            entityManager.createQuery("UPDATE Wagon c set c.freeSeats=" + getFreeSeats(ticket.getWagon()) + " WHERE c.number LIKE :number")
                    .setParameter("number", ticket.getWagon().getNumber()).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
        return false;
    }

    public Wagon findWagon(int wagonNumber) {
        List<Wagon> wagons = entityManager.createQuery("SELECT w FROM Wagon w WHERE w.number LIKE :number")
                .setParameter("number", wagonNumber).getResultList();
        if (wagons.size() == 0) {
            return null;
        } else {
            return wagons.get(0);
        }
    }

    public boolean insertWagon(Wagon wagon) {
        List<Integer> seats = new ArrayList<Integer>();
        Integer wagonNumber = wagon.getNumber();
        if (String.valueOf(wagon.getWagonType()).equalsIgnoreCase("COMFORTABLE")) {
            seats = Wagon.getComfortableWagonSeats();
        } else if (String.valueOf(wagon.getWagonType()).equalsIgnoreCase("COMPARTMENT")) {
            seats = Wagon.getCompartmentWagonList();
        } else if (String.valueOf(wagon.getWagonType()).equalsIgnoreCase("ECONOMY")) {
            seats = Wagon.getEconomyWagonList();
        }
        if (findWagon(wagonNumber) == null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(wagon);
                for (Integer s : seats) {
                    Seat seat = new Seat();
                    seat.setStatus("FREE");
                    seat.setWagon(wagon);
                    seat.setNumber(s + 1);
                    entityManager.persist(seat);
                }
                entityManager.getTransaction().commit();
                log.info("Wagon " + wagonNumber + " is initialize");
            } catch (Exception e) {
                log.log(Level.WARNING, "Exception: ", e);
                entityManager.getTransaction().rollback();
            }
        } else {
            log.info("Wagon " + wagonNumber + " already initialized.");
        }
        seats.clear();
        return false;
    }

    public List<Seat> getAllSeats(Wagon wagon) {
        return entityManager.createQuery("SELECT s FROM Seat s WHERE wagon_id LIKE :wagon")
                .setParameter("wagon", wagon.getId()).getResultList();
    }

    public List<Wagon> getAllWagons() {
        return entityManager.createNamedQuery("Wagon.getAll", Wagon.class).getResultList();
    }

    private int getFreeSeats(Wagon wagon) {
        return entityManager.createQuery("SELECT s FROM Seat s WHERE wagon_id LIKE :wagon AND s.status='FREE'")
                .setParameter("wagon", wagon.getId()).getResultList().size();
    }

    public boolean checkSeatAvailable(Ticket ticket) {
        try {
            List<Seat> seat = entityManager.createQuery("SELECT s FROM Seat s WHERE wagon_id LIKE :wagon AND s.number LIKE :number")
                    .setParameter("wagon", ticket.getWagon().getId()).setParameter("number", ticket.getSeat()).getResultList();
            System.out.println(seat.size());
            if (seat.size() != 0) {
                if (String.valueOf(seat.get(0).getStatus()).equalsIgnoreCase("OCCUPIED")) {
                    log.warning("Seat number " + ticket.getSeat() + " is occupied");
                    return false;
                } else {
                    return true;
                }
            } else {
                log.warning("Wrong seat number");
                return false;
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
        return false;
    }
}