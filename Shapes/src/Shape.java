import java.awt.*;
import java.awt.Rectangle;

public class Shape {

    public Point center() {
        return null;
    }

    public double areal() {
        return 0;
    }
    public void circumference() {
        System.out.println("!");
    }

    public void isPointInside() {
        System.out.println("!");
    }
    public void euclideanDistance() {
        System.out.println("!");
    }

}


class Triangle extends  Shape {
    Point point1 = new Point();
    Point point2 = new Point();
    Point point3 = new Point();

    public Point center() {
        int formelForCentrumX = (point1.x+ point2.x+ point3.x)/3;
        int formelForCentrumY = (point1.y+ point2.y+ point3.y)/3;
        Point center = new Point(formelForCentrumX,formelForCentrumY);
        return center;
    }
    public double areal() {
        double areal=0.5*(point1.x*(point2.y-point3.y)+point2.x*(point3.y-point1.y)+point3.x*(point1.y-point2.y));

        return areal;
    }


}
class Circle extends  Shape {
    Point center_point = new Point();
    float radius;

    public double areal() {
        double areal =3.14 * radius * radius;
        return areal;


    }

    class Rectangle extends Shape {
        Point point = new Point();
        int height = 0;
        int width = 0;


    }
}

