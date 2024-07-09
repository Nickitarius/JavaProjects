package SpringBookLibrary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaBookLibrary implements BookLibrary {

    private List<Book> books;

    public JavaBookLibrary() {
        this.books = new ArrayList<Book>();
        books.add(new Book("The Pity of War"));
        books.add(new Book("Anything but Harry Potter. Guys, just read another book already."));
    }

    public Book[] search(String title) {
        Book[] result = new Book[0];
        for (Book b : books) {
            if (b.getTitle() == title) {
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = b;
            }
        }
        return result;
    }

}
