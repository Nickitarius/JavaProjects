package ShapesPackage;

import java.util.Scanner;
import java.awt.Color;

/*Старая реализация без Spring*/
@Deprecated
public class ShapeTester {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //First rectangle
        System.out.print("Testing Shape.Rectangle\n\nEnter the left-bottom corner's x: ");
        double a1x = in.nextDouble();
        System.out.print("Enter the left-bottom corner's y: ");
        double a1y = in.nextDouble();
        System.out.print("Enter the width: ");
        double w1 = in.nextDouble();
        System.out.print("Enter the height: ");
        double h1 = in.nextDouble();
        System.out.print("Colors are random (I'm too lazy to read them all)");
        Color c1 = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        Color bc1 = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));

        //
        Point2d a1 = new Point2d(a1x, a1y, c1);

        Rectangle R = new Rectangle(a1, h1, w1, c1, bc1);
        R.draw();
        System.out.println("This rectangle's perimeter: " + R.getPerimetr());
        System.out.println("This rectangle's area: " + R.getArea());

        System.out.print("\nTesting Shape.Square\n\nEnter the left-bottom corner's x: ");
        double a2x = in.nextDouble();
        System.out.print("Enter the left-bottom corner's y: ");
        double a2y = in.nextDouble();
        System.out.print("Enter the side: ");
        double s = in.nextDouble();
        Color c2 = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        Color bc2 = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));

        Point2d a2 = new Point2d(a2x, a2y, c2);
        Square S = new Square(a2, s, c2, bc2);
        S.draw();
        System.out.println("This square's perimeter: " + S.getPerimetr());
        System.out.print("This square's area: " + S.getArea());
    }
}
