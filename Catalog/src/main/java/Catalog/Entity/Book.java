package Catalog.Entity;

import Catalog.Entity.Enum.GenreBook;
import Catalog.Entity.Enum.GenreFilm;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String title;
    @Column
    private String author;
    @Enumerated(EnumType.STRING)
    private GenreBook genreBook;
    @Column
    private String released;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Book(){}

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public GenreBook getGenreBook() {
        return genreBook;
    }

    public void setGenreBook(GenreBook genreBook) {
        this.genreBook = genreBook;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
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
        return "Book: " + title + "\nAuthor: " + author + "\nGenre: " + genreBook + "\nReleased: " + released
                + "\nDescription: " + description;
    }
}
