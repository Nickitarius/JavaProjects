package ShapesPackage;

public interface IOperation {

    public boolean contains(IOperation other);

    public IOperation getUnion(IOperation other);

    public IOperation getIntersection(IOperation other);
}
