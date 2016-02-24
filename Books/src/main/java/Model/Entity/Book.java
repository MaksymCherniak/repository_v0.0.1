package Model.Entity;

import java.time.LocalDate;

public class Book {
    private static Integer id = 0;
    private String index;
    private String author;
    private String title;
    private String genre;
    private Double price;
    private LocalDate publish_date;
    private String description;

    public Book() {
    }

    public Book(String author, String title, String genre, Double price, LocalDate publish_date, String description) {
        incId();
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publish_date = publish_date;
        this.description = description;
    }

    private static synchronized void incId() {
        id++;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getPublish_date() {
        return publish_date;
    }

    public String getDescription() {
        return description;
    }

    public static void setId(Integer id) {
        Book.id = id;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPublish_date(LocalDate publish_date) {
        this.publish_date = publish_date;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Book other = (Book) obj;
        return author.equals(other.author) && title.equals(other.title) && genre.equals(other.genre);
    }

    @Override
    public String toString() {
        return "Book info:\n" + "Id: " + index + "\nAuthor: " + author + "\nTitle: " + title + "\nGenre: " + genre + "\nPrice: " + price
                + "\nPublish date: " + publish_date + "\nDescription: " + description;
    }
}
