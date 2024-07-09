package ShapesPackage;

public class Point3d {

    //Properties
    protected double x;
    protected double y;
    protected double z;

    //Constructor
    public Point3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //Default constructor
    public Point3d() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    //Cloning constructor
    public Point3d(Point3d JangoFett) {
        this.x = JangoFett.getX();
        this.y = JangoFett.getY();
        this.z = JangoFett.getZ();
    }

    //Getters
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    //Setters
    public void setX(double value) {
        this.x = value;
    }

    public void setY(double value) {
        this.y = value;
    }

    public void setZ(double value) {
        this.z = value;
    }

    //Checking equality of property values, not their references (captain Obvious here)
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
        Point3d n = (Point3d) other;
        return this.x == n.getX() && this.y == n.getY() && this.z == n.getZ();
    }

    //Do what their names say. Like, really
    public double getEuclideanDistanceTo(Point3d other) {
        return Math.sqrt((this.x - other.getX()) * (this.x - other.getX()) + (this.y - other.getY()) * (this.y - other.getY()) + (this.z - other.getZ()) * (this.z - other.getZ()));
    }

    public double getManhattanDistanceTo(Point3d other) {
        return Math.abs(this.x - other.getX()) + Math.abs(this.y - other.getY()) + Math.abs(this.z - other.getZ());
    }

    @Override
    public String toString() {
        return String.format("%f, %f, %f", this.x, this.y, this.z);
    }

}
