package week_07.abstraction_interface.assigment_problems;

abstract class Shape {
    private static int nextId = 1000;
    private final String shapeId;
    protected double scaleX = 1.0;
    protected double scaleY = 1.0;
    protected Shape() { shapeId = "SH-" + (++nextId); }
    public abstract double calculateArea();
    public void scale(double factor) { scaleX *= factor; scaleY *= factor; }
    public void scale(double xFactor, double yFactor) { scaleX *= xFactor; scaleY *= yFactor; }
    public String getShapeId() { return shapeId; }
}

class CircleShape extends Shape {
    private final double radius;
    public CircleShape(double radius) { this.radius = radius; }
    @Override public double calculateArea() { return Math.PI * radius * radius * scaleX * scaleY; }
}

class SquareShape extends Shape {
    private final double side;
    public SquareShape(double side) { this.side = side; }
    @Override public double calculateArea() { return side * side * scaleX * scaleY; }
}

public class BasicDrawingCanvas {
    static void printArea(Shape s) { System.out.println(s.calculateArea()); }
    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        SquareShape sq = new SquareShape(4.0);
        System.out.println(c.calculateArea());
        System.out.println(sq.calculateArea());
        sq.scale(2.0);
        System.out.println(sq.calculateArea());
        printArea(c);
        // new Shape(); // Does not compile because Shape is abstract.
    }
}