package Model.LocalModel;

/**
 * Created by Max on 01.12.2015.
 */
public class User{
    private static volatile int index = 0;
    private String firstName;
    private String lastName;
    public User(String lastName, String firstName){
        incIndex();
        this.firstName = firstName;
        this.lastName = lastName;
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
    }
}
