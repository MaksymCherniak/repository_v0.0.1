package BookingTool.DAO.Service;


import BookingTool.Entity.Ticket;
import BookingTool.Entity.Train;
import BookingTool.Entity.Wagon;

import java.util.List;

public interface IWagonDAO {
    boolean updateSeat(Ticket ticket);

    boolean updateWagon(Ticket ticket);

    boolean insertWagon(Wagon wagon);

    Wagon findByNumberAndTrainId(int wagonNumber, long train_id);

    List<Wagon> getWagonsByTrain(Train train);

    List getAllSeats(Wagon wagon);

    boolean checkSeatAvailable(Ticket ticket);
}