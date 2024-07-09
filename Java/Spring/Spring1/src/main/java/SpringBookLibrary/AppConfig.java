package SpringBookLibrary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class AppConfig {

    @Bean
    public JavaBookLibrary javaBookLibrary() {
        JavaBookLibrary jbl = new JavaBookLibrary();
        return jbl;
    }

    @Bean
    public BookReader bookReader() {
        return new BookReader(javaBookLibrary());
    }
}
