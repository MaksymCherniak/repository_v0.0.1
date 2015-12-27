package DAO;


import Model.LocalModel.Ticket;
import Model.LocalModel.Wagon;

import java.util.List;

/**
 * Created by Max on 10.12.2015.
 */
public interface IWagonDAO {
    boolean insertSeat(Ticket ticket);

    boolean insertWagon(Wagon wagon);

    boolean updateWagon(Ticket ticket);

    List getAllWagons();

    List getAllSeats(int wagonNumber);
}
