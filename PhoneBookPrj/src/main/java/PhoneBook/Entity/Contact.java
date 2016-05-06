package PhoneBook.Entity;

import javax.persistence.*;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String patronym;
    @Column
    private String email;
    @Column
    private String address;
    @Column(nullable = false)
    private String mobile;
    @Column
    private String home;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Contact() {
    }

    public Contact(String surname, String name, String patronym, String email, String address, String mobile
            , String home, PhoneBook.Entity.User user) {
        this.surname = surname;
        this.name = name;
        this.patronym = patronym;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
        this.home = home;
        this.user = user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public long getId() {
        return id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronym() {
        return patronym;
    }

    public void setPatronym(String patronym) {
        this.patronym = patronym;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        Contact other = (Contact) obj;
        return surname.equals(other.getSurname()) && name.equals(other.getName()) && patronym.equals(other.getPatronym())
                && mobile.equals(other.getMobile());
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + patronym + "\nMobile phone number: " + mobile + (home
                == null ? "" : "\nHome phone number: " + home) + (address == null ? "" : "\nAddress: " + address)
                + (email == null ? "" : "\nE-mail: " + email);
    }

    public String printFullName() {
        return surname + " " + name + " " + patronym;
    }
}