package ru.isu.bookmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.isu.bookmanager.model.Book;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    public String showBook(@PathVariable("bookId") Integer bookId, Model model) {
        Book b = bookService.find(bookId);
        if (b != null) {
            model.addAttribute("book", b);
            return "book";
        } else {
            return "error";
        }
    }

    @RequestMapping("/findall")
    public String findall(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "all_books";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBook(Model model) {
        Book b = new Book();
        model.addAttribute("book", b);
        return "add_book";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveBook(@Valid @ModelAttribute("book") Book book,
                           Errors errors, Model model, RedirectAttributes attr) {
        if (errors.hasErrors()) {
            model.addAttribute("book", book);
            return "add_book";
        } else {
            book.setId(123);
            attr.addFlashAttribute("book", book);
            bookService.save(book);
            return "redirect:/books/book";
        }
        //System.out.println(book);
        //return "home";
    }

    @RequestMapping("/book")
    public String showNewBook(@ModelAttribute("book") Book b,
                              Model model) {
        if (b.getId() != null) {
            model.addAttribute("book", b);
            return "book";
        } else {
            return "error";
        }
    }


}
