package ShapesPackage;

import java.awt.Color;

public class Point2d extends Point3d {

    private Color color;

    //Default constructor
    public Point2d() {
        super();
        this.color = Color.BLACK;
    }

    //Constructor with arguments
    public Point2d(double x, double y, Color color) {
        super(x, y, 0);
        this.color = color;
    }

    //Cloning constructor
    public Point2d(Point2d other) {
        super(other);
        this.color = other.getColor();
    }

    //Gets color
    public Color getColor() {
        return this.color;
    }

    public void draw() {
        StdDraw.setPenColor(this.color);
        StdDraw.point(this.x, this.y);
    }

    public void move(double dx, double dy) {
        this.setX(this.x + dx);
        this.setY(this.x + dy);
    }

    public void turn(double alpha) {
        this.setX(this.x * Math.cos(alpha) + this.y * Math.sin(alpha));
        this.setY(this.x * Math.sin(alpha) + this.y * Math.cos(alpha));
    }

}
