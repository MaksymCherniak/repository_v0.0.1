package DAO;

import Model.LocalModel.Seat;
import Model.LocalModel.SeatStatus;
import Model.LocalModel.Ticket;
import Model.LocalModel.Wagon;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLWagonDAO implements IWagonDAO {
    private static final String UPDATE_SEAT = "UPDATE Seat c set c.status=:status WHERE wagon_id LIKE :wagon AND c.number LIKE :number";
    private static final String UPDATE_WAGON = "UPDATE Wagon c set c.freeSeats= :seats WHERE c.number LIKE :number";
    private static final String FIND_WAGON = "SELECT w FROM Wagon w WHERE w.number LIKE :number";
    private static final String GET_ALL_SEATS = "SELECT s FROM Seat s WHERE wagon_id LIKE :wagon";
    private static final String GET_FREE_SEATS = "SELECT s FROM Seat s WHERE wagon_id LIKE :wagon AND s.status='FREE'";
    private static final String CHECK_SEAT = "SELECT s FROM Seat s WHERE wagon_id LIKE :wagon AND s.number LIKE :number";
    private static final String NUMBER = "number";
    private static final String WAGON = "wagon";
    private static final String STATUS = "status";
    private static final String SEATS = "seats";
    private static EntityManager entityManager;
    private static Logger log = Logger.getLogger(MySQLWagonDAO.class.getName());

    public MySQLWagonDAO() {
        entityManager = HibernateUtil.getEm();
    }

    public static void setEntityManager(EntityManager entityManager) {
        MySQLWagonDAO.entityManager = entityManager;
    }

    public boolean updateSeat(Ticket ticket) {
        try {
            if (checkSeatAvailable(ticket)) {
                entityManager.getTransaction().begin();
                entityManager.createQuery(UPDATE_SEAT).setParameter(WAGON, ticket.getWagon().getId())
                        .setParameter(STATUS, SeatStatus.OCCUPIED).setParameter(NUMBER, ticket.getSeat()).executeUpdate();
                entityManager.createQuery(UPDATE_WAGON).setParameter(NUMBER, ticket.getWagon().getNumber())
                        .setParameter(SEATS, getFreeSeats(ticket.getWagon())).executeUpdate();
                entityManager.getTransaction().commit();
                entityManager.clear();
                return true;
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
        return false;
    }

    public boolean updateWagon(Ticket ticket) {
        try {
            if (!checkSeatAvailable(ticket)) {
                entityManager.getTransaction().begin();
                entityManager.createQuery(UPDATE_SEAT).setParameter(WAGON, ticket.getWagon().getId())
                        .setParameter(STATUS, SeatStatus.FREE).setParameter(NUMBER, ticket.getSeat()).executeUpdate();
                entityManager.createQuery(UPDATE_WAGON).setParameter(NUMBER, ticket.getWagon().getNumber())
                        .setParameter(SEATS, getFreeSeats(ticket.getWagon())).executeUpdate();
                entityManager.getTransaction().commit();
                entityManager.clear();
                return true;
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
        return false;
    }

    public Wagon findWagon(int wagonNumber) {
        List<Wagon> wagon = entityManager.createQuery(FIND_WAGON).setParameter(NUMBER, wagonNumber).getResultList();
        if (wagon.size() != 0) {
            return wagon.get(0);
        } else {
            log.info("Wagon not found");
            return null;
        }
    }

    public boolean insertWagon(Wagon wagon) {
        List<Integer> seats = new ArrayList<Integer>();
        Integer wagonNumber = wagon.getNumber();
        switch (wagon.getWagonType()) {
            case COMFORTABLE:
                seats = Wagon.getComfortableWagonSeats();
                break;
            case COMPARTMENT:
                seats = Wagon.getCompartmentWagonList();
                break;
            case ECONOMY:
                seats = Wagon.getEconomyWagonList();
                break;
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
                seats.clear();
                return true;
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
        return entityManager.createQuery(GET_ALL_SEATS)
                .setParameter(WAGON, wagon.getId()).getResultList();
    }

    public List<Wagon> getAllWagons() {
        return entityManager.createNamedQuery("Wagon.getAll", Wagon.class).getResultList();
    }

    private int getFreeSeats(Wagon wagon) {
        return entityManager.createQuery(GET_FREE_SEATS)
                .setParameter(WAGON, wagon.getId()).getResultList().size();
    }

    public boolean checkSeatAvailable(Ticket ticket) {
        try {
            List<Seat> seat = entityManager.createQuery(CHECK_SEAT).setParameter(WAGON, ticket.getWagon().getId())
                    .setParameter(NUMBER, ticket.getSeat()).getResultList();
            if (seat.size() != 0) {
                if (String.valueOf(seat.get(0).getStatus()).equalsIgnoreCase("OCCUPIED")) {
                    log.info("Seat number " + ticket.getSeat() + " is occupied");
                } else {
                    return true;
                }
            } else {
                log.info("Wrong seat number");
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
        return false;
    }
}