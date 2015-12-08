package Model.LocalModel;


import java.util.HashSet;
import java.util.Set;

/**
 * Created by Max on 01.12.2015.
 */
public class User{
    public static Set<User> usersData = new HashSet<User>();
    private static volatile int index = 0;
    private String firstName;
    private String lastName;
    public User(String lastName, String firstName){
        incIndex();
        this.firstName = firstName;
        this.lastName = lastName;
        usersData.add(this);
    }

    private synchronized static void incIndex(){ index ++; }

    public synchronized static int getIndex() {
        return index;
    }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public static void setIndex(String index) {
        int id = Integer.parseInt(index);
        User.index = id;
        System.out.println(User.index);
    }
}
