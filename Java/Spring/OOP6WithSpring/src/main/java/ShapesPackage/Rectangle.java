package ShapesPackage;

import java.awt.Color;

public class Rectangle extends Shape implements IScale, IMove, IContainer {

    //Properties
    protected Point2d A;
    protected double height;
    protected double width;

    //Default constructor
    public Rectangle() {
        this.A = new Point2d();
        this.height = 1;
        this.width = 0.5;
        this.color = Color.BLACK;
        this.backgroundColor = Color.WHITE;
    }

    public Rectangle(Point2d A, double height, double width, Color color, Color backgroundColor) {
        this.A = A;
        this.height = height;
        this.width = width;
        this.color = color;
        this.backgroundColor = backgroundColor;
    }

    //Cloning constructor
    public Rectangle(Rectangle other) {
        this.A = other.getA();
        this.height = other.getHeight();
        this.width = other.getWidth();
        this.color = other.getColor();
        this.backgroundColor = other.getBackgroundColor();
    }

    //Getters
    public Point2d getA() {
        return this.A;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    public Color getColor() {
        return this.color;
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    //toString&equals
    @Override
    public String toString() {
        return String.format("(%s), (%f), (%f),(%s)", A, height, width, color, backgroundColor);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Rectangle n = (Rectangle) other;
        return this.A.equals(n.getA()) && this.height == n.getHeight() && this.width == n.getWidth()
                && this.color.equals(n.getColor()) && this.backgroundColor.equals(n.getBackgroundColor());
    }

    //Implementation of abstract methods form Shape class
    @Override
    public double getPerimetr() {
        return this.width * 2 + this.height * 2;
    }

    @Override
    public double getArea() {
        return this.width * this.height;
    }

    @Override
    public void draw() {
        StdDraw.setPenColor(this.backgroundColor);
        StdDraw.filledRectangle(A.getX() + this.getWidth() / 2,
                A.getY() + this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
        StdDraw.setPenColor(this.color);
        StdDraw.rectangle(A.getX() + this.getWidth() / 2,
                A.getY() + this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
    }

    //Implementation of IScale class methods

    /**
     * Changes shape's size proportionally by the given value
     *
     * @param value coefficient of change
     */
    @Override
    public void resize(double value) {
        this.height *= value;
        this.width *= value;
    }

    //Implementation of IMove class method
    @Override
    public void move(double dx, double dy) {
        this.A.move(dx, dy);
    }

    //Implementations of IContainer class method
    @Override
    public boolean contains(Point2d p) {
        return (p.getX() >= this.A.getX()) && (p.getX() <= this.A.getX() + width) && (p.getY() >= this.A.getY()) && (p.getY() <= this.A.getY() + height);
    }

}
