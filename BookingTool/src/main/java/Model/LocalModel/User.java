package Model.LocalModel;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@NamedQuery(name = "User.getAll", query = "SELECT c from User c")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Ticket> ticket;

    public User() {
    }

    public User(String surname, String name) {
        this.name = name;
        this.surname = surname;
    }

    public Set<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(Set<Ticket> ticket) {
        this.ticket = ticket;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(int id) {
        this.id = id;
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
        User other = (User) obj;
        return name.equals(other.getName()) && surname.equals(other.getSurname());
    }

    @Override
    public String toString() {
        return "User ID: " + id + ", Full name: " + surname + " " + name;
    }
}