package DAO;

import Model.LocalModel.LocalData;
import Model.LocalModel.User;

import java.util.List;

/**
 * Created by Max on 01.12.2015.
 */
public interface ILocalData {
    void create();
    void read();
    void update(int seatNumber, User user);
    void delete();
    List<LocalData> getAll();
}
