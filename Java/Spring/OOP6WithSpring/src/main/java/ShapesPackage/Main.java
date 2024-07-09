package ShapesPackage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/*Новая реализация на Spring*/
public class Main {

    public static void main(String[] args) {
        System.out.println("Testing Shape.Rectangle");
        //Scanner in = new Scanner(System.in);
        //System.out.println("Enter first");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Shape rectangle = context.getBean("rectangle", Shape.class);
        rectangle.draw();
        System.out.println("This rectangle's perimeter: " + rectangle.getPerimetr());
        System.out.println("This rectangle's area: " + rectangle.getArea());

        System.out.println("\nTesting Shape.Square");
        Shape square = context.getBean("square", Shape.class);
        System.out.println("This square's perimeter: " + square.getPerimetr());
        System.out.print("This square's area: " + square.getArea());
        square.draw();
    }
}
