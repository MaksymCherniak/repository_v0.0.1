package DAO;


import Model.LocalModel.Ticket;
import Model.LocalModel.Wagon;

import java.util.List;

public interface IWagonDAO {
    boolean updateSeat(Ticket ticket);

    boolean insertWagon(Wagon wagon);

    Wagon findWagon(int wagonNumber);

    List getAllWagons();

    List getAllSeats(Wagon wagon);
}