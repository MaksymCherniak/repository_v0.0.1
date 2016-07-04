package Catalog.Entity;

import Catalog.Entity.Enum.GenreFilm;

import javax.persistence.*;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String title;
    @Column
    private String producer;
    @Column
    private String country;
    @Enumerated(EnumType.STRING)
    private GenreFilm genreFilm;
    @Column
    private String released;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Film(){}

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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public GenreFilm getGenreFilm() {
        return genreFilm;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setGenreFilm(GenreFilm genreFilm) {
        this.genreFilm = genreFilm;
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
        return "Film: " + title + "\nProducer: " + producer + "\nCountry: " + country + "\nGenre: " + genreFilm
                + "\nReleased: " + released + "\nDescription: " + description;
    }
}
