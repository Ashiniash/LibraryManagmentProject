package org.example.model;
import javax.persistence.*;
import java.sql.Date;
@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "bookId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bookId;
    @Column(name = "bookTitle")
    String bookTitle;
    @Column(name = "authorName")
    String authorName;
    @Column(name = "publisher")
    String publisher;
    @Column(name = "publicationDate")
    Date publicationDate;
    @Column(name = "genre")
    String genre;



    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = this.bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
   public boolean equals(Object obj) {
        if (!(obj instanceof Book)) {
            return false;
        }
        Book otherMember = (Book) obj;
        return otherMember.getBookId() == (getBookId());
    }


}
