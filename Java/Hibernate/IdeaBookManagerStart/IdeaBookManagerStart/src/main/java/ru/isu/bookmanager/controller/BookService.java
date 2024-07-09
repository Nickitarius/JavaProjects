package ru.isu.bookmanager.controller;


import org.springframework.stereotype.Service;
import ru.isu.bookmanager.model.Book;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@Service("bookService")
public class BookService {

    private List<Book> books = new LinkedList();

    public BookService() {
        this.books.addAll(Arrays.asList(new Book[]{
                this.createBook(1, "Java",
                        "Herbert Schildt", 2015),
                this.createBook(2, "Pro Spring",
                        "Chris Schaefer", 2014),
                this.createBook(3, "JavaFX Essentials",
                        "Mohamed Taman", 2015),
                this.createBook(4, "Pro Spring Boot 2",
                        "Felipe Gutierrez", 2020)
        }));
    }

    public List<Book> findAll() {
        return this.books;
    }

    public Book find(Integer bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public void save(Book book) {
        System.out.println("----" + book + "----");
        this.books.add(book);
    }

    private Book createBook(Integer bookId, String title,
                            String authors, Integer year) {
        Book book = new Book();
        book.setId(bookId);
        book.setTitle(title);
        book.setAuthors(authors);
        book.setYear(year);
        return book;
    }

}
