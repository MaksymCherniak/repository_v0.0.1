package DAO;


import Model.LocalModel.User;
import Model.LocalModel.Wagon;

import java.util.List;

/**
 * Created by Max on 10.12.2015.
 */
public interface IWagonDAO {
    boolean insertSeat(int seatNumber, User user);
    boolean insertWagon(Wagon wagon);
    boolean updateWagon(String id);
    List getAllSeats();
}
