package Catalog.Entity;

import Catalog.Entity.Enum.GenreSound;

import javax.persistence.*;

@Entity
@Table(name = "sound")
public class Sound {
    @Id
    @Column(name = "sound_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String title;
    @Column
    private String singer;
    @Enumerated(EnumType.STRING)
    private GenreSound genreSound;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Sound() {
    }

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

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public GenreSound getGenreSound() {
        return genreSound;
    }

    public void setGenreSound(GenreSound genreSound) {
        this.genreSound = genreSound;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Sound: " + title + "\nSinger: " + singer + "\nGenre: " + genreSound;
    }
}
