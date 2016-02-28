package Entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Book")
@XmlType(propOrder = {"author", "title", "genre", "price", "publishDate", "description"})
public class Book {
    private String index;
    private String author;
    private String title;
    private String genre;
    private String price;
    private String publishDate;
    private String description;

    public Book() {
    }

    public Book(String index, String author, String title, String genre, String price, String publishDate, String description) {
        this.index = index;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publishDate = publishDate;
        this.description = description;
    }

    @XmlAttribute(name = "id")
    public String getIndex() {
        return index;
    }

    @XmlElement
    public String getAuthor() {
        return author;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    @XmlElement
    public String getGenre() {
        return genre;
    }

    @XmlElement
    public String getPrice() {
        return price;
    }

    @XmlElement
    public String getPublishDate() {
        return publishDate;
    }

    @XmlElement
    public String getDescription() {
        return description;
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

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
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
                + "\nPublish date: " + publishDate + "\nDescription: " + description;
    }
}
