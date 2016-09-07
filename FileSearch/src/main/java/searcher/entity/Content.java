package searcher.entity;

import javax.persistence.*;

@Entity
@Table(name = "content")
public class Content {
    @Id
    @Column(name = "content_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String content;
    @Column
    private String creationDate;

    public Content() {
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Content: " + content + "\nCreation date: " + creationDate;
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
        Content other = (Content) obj;
        return content.equals(other.content) && creationDate.equals(other.creationDate);
    }
}
