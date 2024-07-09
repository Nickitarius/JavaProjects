package SpringBookLibrary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookReader bookReader = context.getBean("bookReader", BookReader.class);
        Book books[] = bookReader.read();
        System.out.println("Client read: " + books.length + " books");
    }
}
