package ShapesPackage;

import java.awt.Color;

public class Square extends Rectangle {

    //Constructor with arguments, A is left-bottom corner
    public Square(Point2d A, double side, Color color, Color backgroundColor) {
        super(A, side, side, color, backgroundColor);
    }

    //Cloning constructor
    public Square(Square other) {
        super();
    }

}
