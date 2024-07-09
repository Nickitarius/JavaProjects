package oop4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "oop4")
public class AppConfig {

    @Bean
    public Reader reader() {
        Reader r = new Reader();
        return r;
    }
}
