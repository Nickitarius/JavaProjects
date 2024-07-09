package ru.isu.bookmanager.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;

@Getter
@Setter
public class Book {
    private Integer id;

    @NotEmpty(message = "Empty title not allowed")
    private String title;
    private String type;
    private String authors;
    private Integer pages;
    private Integer year;
    private String description;
    private Boolean rarity;
    private String genre;
    private LocalDate pubDate;


    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" + "bookId=" + id
                + ", title=" + title
                + ", type=" + type
                + ", authors=" + authors
                + ", pages=" + pages
                + ", year=" + year
                + ", description=" + description
                + ", rarity=" + rarity
                + ", genre=" + genre
                + ", pubDate=" + pubDate + '}';
    }
}
