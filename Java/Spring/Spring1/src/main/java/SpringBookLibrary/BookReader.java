package SpringBookLibrary;

public class BookReader {

    private BookLibrary bookLibrary;

    public BookReader(BookLibrary bookLibrary) {
        this.bookLibrary = bookLibrary;
    }

    public Book[] read() {
        return bookLibrary.search("The Pity of War");
    }
}
