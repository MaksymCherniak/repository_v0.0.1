package BookingTool.DAO;


import BookingTool.Model.LocalModel.Ticket;
import BookingTool.Model.LocalModel.Wagon;

import java.util.List;

public interface IWagonDAO {
    boolean updateSeat(Ticket ticket);

    boolean updateWagon(Ticket ticket);

    boolean insertWagon(Wagon wagon);

    Wagon findWagon(int wagonNumber);

    List getAllWagons();

    List getAllSeats(Wagon wagon);

    boolean checkSeatAvailable(Ticket ticket);

}