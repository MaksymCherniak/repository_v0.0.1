package PhoneBook.Entity;

import javax.persistence.*;

@Entity
@Table(name = "phone_book")
public class Contact {
    @Id
    @Column(name = "phone_book_id")
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
    private String mobilePhoneNumber;
    @Column
    private String homePhoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private PhoneBook.Entity.User user;

    public Contact() {
    }

    public Contact(String surname, String name, String patronym, String email, String address, String mobilePhoneNumber
            , String homePhoneNumber, PhoneBook.Entity.User user) {
        this.surname = surname;
        this.name = name;
        this.patronym = patronym;
        this.email = email;
        this.address = address;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.homePhoneNumber = homePhoneNumber;
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

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
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
                && mobilePhoneNumber.equals(other.getMobilePhoneNumber());
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + patronym + "\nMobile phone number: " + mobilePhoneNumber + (homePhoneNumber
                == null ? "" : "\nHome phone number: " + homePhoneNumber) + (address == null ? "" : "\nAddress: " + address)
                + (email == null ? "" : "\nE-mail: " + email);
    }

    public String printFullName() {
        return surname + " " + name + " " + patronym;
    }
}