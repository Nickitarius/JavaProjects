package ShapesPackage;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.awt.*;
import java.util.Scanner;

@Configuration
@Import(PointsConfig.class)
public class AppConfig {

    @Bean
    public Rectangle rectangle(Point2d a1) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the width for rectangle: ");
        double w1 = in.nextDouble();
        System.out.print("Enter the height for rectangle: ");
        double h1 = in.nextDouble();
        Color bc1 = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        return new Rectangle(a1, h1, w1, a1.getColor(), bc1);
    }

    @Bean
    public Square square(Point2d a2) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the side for square: ");
        double s = in.nextDouble();
        Color bc2 = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        return new Square(a2, s, a2.getColor(), bc2);
    }
}
