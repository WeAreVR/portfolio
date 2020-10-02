import java.awt.*;
import java.lang.Math;

public abstract class Shape {

    public Point center() {
        return null;
    }

    public double areal() {
        return 0;
    }

    public double circumference() {
        return 0;
    }

    public boolean isPointInside(Point p) {
        return true;
    }

    public double euclideanDistance(Shape a, Shape b) {
        Point center_a = a.center();
        Point center_b = b.center();
        double euclideanDistance = Math.sqrt((center_a.y - center_b.y) * (center_a.y - center_b.y) + (center_a.x - center_b.x) * (center_a.x - center_b.x));
        return euclideanDistance;
    }

}


class Triangle extends Shape {
    private Point point1 = new Point();
    private Point point2 = new Point();
    private Point point3 = new Point();

    public Triangle(Point point1, Point point2, Point point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }


    public Point center() {
        int formelForCentrumX = (point1.x + point2.x + point3.x) / 3;
        int formelForCentrumY = (point1.y + point2.y + point3.y) / 3;
        Point center = new Point(formelForCentrumX, formelForCentrumY);
        return center;
    }

    public double areal() {
        double areal = 0.5 * (point1.x * (point2.y - point3.y) + point2.x * (point3.y - point1.y) + point3.x * (point1.y - point2.y));

        return areal;
    }

    public double areal(Point a, Point b, Point c) {
        double areal = 0.5 * (a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y));

        return areal;
    }

    public boolean isPointInside(Point p) {
        double abc = areal(point1, point2, point3);
        double pab = areal(p, point1, point2);
        double pac = areal(p, point1, point3);
        double pbc = areal(p, point2, point3);

        return abc == (pac + pab + pbc);
    }

    public double circumference() {
        double dis_1_2 = Math.sqrt((point2.y - point1.y) * (point2.y - point1.y) + (point2.x - point1.x) * (point2.x - point1.x));
        double dis_2_3 = Math.sqrt((point3.y - point2.y) * (point3.y - point2.y) + (point3.x - point2.x) * (point3.x - point2.x));
        double dis_1_3 = Math.sqrt((point3.y - point1.y) * (point3.y - point1.y) + (point3.x - point1.x) * (point3.x - point1.x));
        double circumference = dis_1_2 + dis_1_3 + dis_2_3;


        return circumference;
    }
}


class Circle extends Shape {
    private Point center_point = new Point();
    private double radius;

    public Circle(Point p, double radius) {
        this.center_point = p;
        this.radius = radius;
    }

    public Point center() {
        return center_point;
    }

    public double areal() {
        double areal = Math.PI * radius * radius;
        return areal;
    }

    public double circumference() {
        double circumference = (Math.PI * 2) * radius;
        return circumference;
    }

    public boolean isPointInside(Point p) {
        double disCP = Math.sqrt((p.y - center_point.y) * (p.y - center_point.y) + (p.x - center_point.x) * (p.x - center_point.x));
        return disCP < radius;
    }
}


class Rectangle extends Shape {
    private Point point = new Point();
    private int height;
    private int width;

    public Rectangle(Point p, int h, int w) {
        this.point = p;
        this.height = h;
        this.width = w;
    }

    public Point center() {
        Point center = new Point(point.x + width / 2, point.y + height / 2);
        return center;
    }

    public double areal() {
        double areal = height * width;
        return areal;
    }

    public double circumference() {
        double circumference = height * 2 + width * 2;
        return circumference;
    }

    public boolean isPointInside(Point p) {
        return point.x < p.x && p.x < point.x + width
                && point.y < p.y && p.y < point.y + height;
    }
}

