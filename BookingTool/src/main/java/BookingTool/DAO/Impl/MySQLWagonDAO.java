package BookingTool.DAO.Impl;

import BookingTool.DAO.Repository.SeatRepository;
import BookingTool.DAO.Repository.WagonRepository;
import BookingTool.DAO.Service.IWagonDAO;
import BookingTool.Entity.Seat;
import BookingTool.Entity.Enums.SeatStatus;
import BookingTool.Entity.Ticket;
import BookingTool.Entity.Train;
import BookingTool.Entity.Wagon;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySQLWagonDAO implements IWagonDAO {
    @Autowired
    private WagonRepository wagonRepository;
    @Autowired
    private SeatRepository seatRepository;
    private static Logger log = Logger.getLogger(MySQLWagonDAO.class.getName());

    public MySQLWagonDAO() {
    }

    public boolean updateSeat(Ticket ticket) {
        if (checkSeatAvailable(ticket)) {
            seatRepository.updateSeat(ticket.getWagon().getId(), ticket.getSeat(), SeatStatus.OCCUPIED);
            wagonRepository.updateWagon(getFreeSeats(ticket.getWagon()), ticket.getWagon().getNumber());
            return true;
        }
        return false;
    }

    public boolean updateWagon(Ticket ticket) {
        if (!checkSeatAvailable(ticket)) {
            seatRepository.updateSeat(ticket.getWagon().getId(), ticket.getSeat(), SeatStatus.FREE);
            wagonRepository.updateWagon(getFreeSeats(ticket.getWagon()), ticket.getWagon().getNumber());
            return true;
        }
        return false;
    }

    public Wagon findByNumberAndTrainId(int wagonNumber, long train_id) {
        Wagon wagon = wagonRepository.findByNumberAndTrainId(train_id, wagonNumber);
        if (wagon != null) {
            return wagon;
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
        if (findByNumberAndTrainId(wagonNumber, wagon.getTrain().getId()) == null) {
            wagonRepository.saveAndFlush(wagon);
            for (Integer s : seats) {
                Seat seat = new Seat();
                seat.setStatus("FREE");
                seat.setWagon(wagon);
                seat.setNumber(s + 1);
                seatRepository.saveAndFlush(seat);
            }
            log.info("Wagon " + wagonNumber + " is initialize");
            seats.clear();
            return true;
        } else {
            log.info("Wagon " + wagonNumber + " already initialized.");
        }
        seats.clear();
        return false;
    }

    public List<Seat> getAllSeats(Wagon wagon) {
        return seatRepository.getAllSeats(wagon.getId());
    }

    public List<Wagon> getWagonsByTrain(Train train) {
        return wagonRepository.getWagonsByTrain(train.getId());
    }

    private int getFreeSeats(Wagon wagon) {
        return seatRepository.getFreeSeats(wagon.getId()).size();
    }

    public boolean checkSeatAvailable(Ticket ticket) {
        Seat seat = seatRepository.getSeat(ticket.getWagon().getId(), ticket.getSeat());
        if (seat != null) {
            if (String.valueOf(seat.getStatus()).equalsIgnoreCase("OCCUPIED")) {
                log.info("Seat number " + ticket.getSeat() + " is occupied");
            } else {
                return true;
            }
        } else {
            log.info("Wrong seat number");
        }
        return false;
    }
}