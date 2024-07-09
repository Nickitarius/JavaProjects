package ShapesPackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.util.Scanner;

@Configuration
public class PointsConfig {

    @Bean
    public Point2d a1() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the left-bottom corner's x for a1: ");
        double a1x = in.nextDouble();
        System.out.print("Enter the left-bottom corner's y for a1: ");
        double a1y = in.nextDouble();
        Color c1 = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        return new Point2d(a1x, a1y, c1);
    }

    @Bean
    public Point2d a2() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the left-bottom corner's x for a2: ");
        double a2x = in.nextDouble();
        System.out.print("Enter the left-bottom corner's y for a2: ");
        double a2y = in.nextDouble();
        Color c2 = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        return new Point2d(a2x, a2y, c2);
    }
}
