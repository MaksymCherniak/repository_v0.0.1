package DAO;


import Model.LocalModel.User;
import Model.LocalModel.Wagon;

/**
 * Created by Max on 10.12.2015.
 */
public interface IWagonDAO {
    boolean insertSeat(int seatNumber, int userId);
    boolean insertWagon(Wagon wagon);
    void deleteWagon(String number);
    Wagon findWagon(int number);
    boolean updateWagon(int userId);
    void printWagon();
}
