package ShapesPackage;

import java.awt.Color;

public  abstract class Shape {

    protected Color color;
    protected Color backgroundColor;

    public abstract double getPerimetr();

    public abstract double getArea();

    public abstract void draw();

    public void setColor(Color c) {
        this.color = c;
    }

    public void setBackgroundColor(Color c) {
        this.backgroundColor = c;
    }
}
