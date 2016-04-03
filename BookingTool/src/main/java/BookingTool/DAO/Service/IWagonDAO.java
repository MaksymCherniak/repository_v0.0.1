package BookingTool.DAO.Service;


import BookingTool.Entity.Seat;
import BookingTool.Entity.Ticket;
import BookingTool.Entity.Train;
import BookingTool.Model.LocalModel.Wagon;

import java.util.List;

public interface IWagonDAO {
    boolean updateSeat(Ticket ticket);

    boolean updateWagon(Ticket ticket);

    boolean insertWagon(Wagon wagon);

    Wagon getWagonById(long id);

    Wagon findByNumberAndTrainId(int wagonNumber, long train_id);

    List<Wagon> getWagonsByTrain(Train train);

    Seat getSeatById(long id);

    List getAllSeats(Wagon wagon);

    boolean checkSeatAvailable(Ticket ticket);
}