package Catalog.Entity;

import javax.persistence.*;

@Entity
@Table(name = "cartoon")
public class Cartoon {
    @Id
    @Column(name = "cartoon_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String title;
    @Column
    private String producer;
    @Column
    private String country;
    @Column
    private String released;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Cartoon(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cartoon: " + title + "\nProducer: " + producer + "\nCountry: " + country + "\nReleased: " + released
                + "\nDescription: " + description;
    }
}
