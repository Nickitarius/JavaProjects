package SpringBookLibrary;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Book {

    private String title;

    public Book(String title) {
        this.title = title;
    }

}
