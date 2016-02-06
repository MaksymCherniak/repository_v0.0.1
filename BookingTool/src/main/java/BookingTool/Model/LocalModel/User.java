package BookingTool.Model.LocalModel;

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
    private String surname;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> ticket;

    public User() {
    }

    public User(String surname, String name) {
        this.name = name;
        this.surname = surname;
    }

    public User(String surname, String name, String email, String password) {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return name.equals(other.getName()) && surname.equals(other.getSurname()) && email.equals(other.getEmail())
                && password.equals(other.getPassword());
    }

    @Override
    public String toString() {
        return "User ID: " + id + ", Full name: " + surname + " " + name;
    }
}