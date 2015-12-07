package Model.LocalModel;

import DAO.IUserDAO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.*;

/**
 * Created by Max on 01.12.2015.
 */
@XmlRootElement
public class User implements IUserDAO {
    public static Set<User> usersData = new HashSet<User>();
    private static volatile int index = 0;
    private String firstName;
    private String lastName;
    public User(){
        index++;
    }
    public synchronized User create(String lastName, String firstName) {
        this.firstName = firstName;
        this.lastName = lastName;
        usersData.add(this);
        new XmlData().update(this);
        return this;
    }
    @XmlAttribute
    public synchronized static int getIndex() {
        return index;
    }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public static void setIndex(String index) {
        int id = Integer.parseInt(index);
        User.index = id;
    }

    public User delete(String id) {
        new LocalData().delete();
        new XmlData().delete(id);
        return this;
    }
    public User read(int key) {
        return null;
    }
    public User update(User user) {
        return null;
    }
    public List<User> getAll() {
        return null;
    }

}
