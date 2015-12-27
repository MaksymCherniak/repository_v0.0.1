package Model.LocalModel;

import javax.persistence.*;

@Entity
@Table(name = "users2")
public class User2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private static Integer id;
    @Column(name = "name", length = 64)
    private String firstName;
    @Column(name = "surname", length = 64)
    private String lastName;
    @Column(name = "ticket")
    private Integer ticket;

    public User2() {
    }

    public User2(String lastName, String firstName) {
        ticket = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static synchronized void setIndex(Integer index) {
        User2.id = index;
    }

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
        return firstName.equals(other.getFirstName()) && lastName.equals(other.getLastName()) && ticket.equals(other.ticket);
    }

    @Override
    public String toString() {
        return "Full name: " + lastName + " " + firstName + ", ticket: " + ticket;
    }
}
