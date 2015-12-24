package Model.LocalModel;

import javax.persistence.*;

@Entity
@Table(name = "users2")
public class User2{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "id")
    private static Integer index;
    @Column (name = "name", length = 64)
    private String firstName;
    @Column (name = "surname", length = 64)
    private String lastName;
    @Column (name = "ticket")
    private Integer ticket;
    public User2(){}
    public User2(String lastName, String firstName){
        ticket = index;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getIndex() {
        return index;
    }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Integer getTicket() {return ticket;}

    public void setTicket(Integer ticket) { this.ticket = ticket; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public static synchronized void setIndex(Integer index) { User2.index = index; }

    private static synchronized void incIndex(){ index ++; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User2 other = (User2) obj;
        if (!firstName.equals(other.getFirstName())) {
            return false;
        }
        if (!lastName.equals(other.getLastName())) {
            return false;
        }
        if (!ticket.equals(other.ticket)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Full name: " + lastName + " " + firstName + ", ticket: " + ticket;
    }
}
