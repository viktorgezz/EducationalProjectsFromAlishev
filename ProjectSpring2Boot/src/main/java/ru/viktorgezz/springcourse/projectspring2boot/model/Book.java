package ru.viktorgezz.springcourse.projectspring2boot.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name = "title")
    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 120, message = "Название книги должно быть от 2 до 120 символов длиной")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "Автор не должен быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора должно быть от 2 до 100 символов длиной")
    private String author;

    @Column(name = "date_creation")
    @Min(value = 0, message = "Год должен быть больше, чем 0")
    private int dateCreation;

    @Column(name = "date_take")
    private LocalDate dateTake;

    @ManyToOne
    @JoinColumn(name = "people_id")
    private People owner;

    @Transient
    private boolean expired;

    public Book() {
    }

    public Book(String title, String author, int dateCreation) {
        this.title = title;
        this.author = author;
        this.dateCreation = dateCreation;
    }

    public Book(String title, String author, int dateCreation, LocalDate dateTake, boolean expired) {
        this.title = title;
        this.author = author;
        this.dateCreation = dateCreation;
        this.dateTake = dateTake;
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", dateCreation=" + dateCreation +
                ", dateTake=" + dateTake +
                '}';
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int book_id) {
        this.bookId = book_id;
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

    public int getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(int dateCreation) {
        this.dateCreation = dateCreation;
    }

    public People getOwner() {
        return owner;
    }

    public void setOwner(People owner) {
        this.owner = owner;
    }

    public LocalDate getDateTake() {
        return dateTake;
    }

    public void setDateTake(LocalDate dateTake) {
        this.dateTake = dateTake;
    }

    public boolean getExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
