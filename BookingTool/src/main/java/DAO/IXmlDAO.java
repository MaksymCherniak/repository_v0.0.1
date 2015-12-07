package DAO;

import Model.LocalModel.User;

import java.util.List;

/**
 * Created by Max on 04.12.2015.
 */
public interface IXmlDAO {
    void create();
    void read();
    void update(User user);
    void delete(String id);
    List<IXmlDAO> getAll();
}
