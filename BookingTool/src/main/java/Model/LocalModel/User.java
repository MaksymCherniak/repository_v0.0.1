package Model.LocalModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users2")
public class User{
    private static volatile Integer index = 0;
    private String firstName;
    private String lastName;
    private String ticket = "0";
    public User(){}
    public User(String lastName, String firstName){
        incIndex();
        synchronized (ticket) {
            setTicket(String.valueOf(index));
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private synchronized static void incIndex(){ index ++; }

    @Id
    @Column(name = "id")
    public synchronized static Integer getIndex() {
        return index;
    }
    @Column(name = "name", length = 64)
    public String getFirstName() { return firstName; }
    @Column(name = "surname", length = 64)
    public String getLastName() { return lastName; }
    @Column(name = "ticket", length = 64)
    public String getTicket() {return ticket;}

    public void setTicket(String ticket) { this.ticket = ticket; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public static void setIndex(String index) {
        int id = Integer.parseInt(index);
        User.index = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            System.out.println("1");
            return true;
        }
        if (obj == null) {
            System.out.println("2");
            return false;
        }
        if (getClass() != obj.getClass()) {
            System.out.println("3");
            return false;
        }
        User other = (User) obj;
        if (!firstName.equals(other.getFirstName())) {
            System.out.println("4");
            return false;
        }
        if (!lastName.equals(other.getLastName())) {
            System.out.println("5");
            return false;
        }
        if (!ticket.equals(other.ticket)) {
            System.out.println("6");
            return false;
        }
        return true;
    }
}
