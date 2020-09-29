import java.awt.*;
import java.lang.Math;

public class Shape {

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
        System.out.println("!");
        return true;
    }
    public double euclideanDistance(Shape a, Shape b) {
        Point center_a = a.center();
        Point center_b = b.center();
        double euclideanDistance = Math.sqrt((center_a.x-center_b.x)^2+(center_a.y-center_b.y)^2);
        return euclideanDistance;
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
    public double areal(Point a, Point b, Point c) {
        double areal=0.5*(a.x*(b.y-c.y)+b.x*(c.y-a.y)+c.x*(a.y-b.y));

        return areal;
    }
    public boolean isPointInside(Point p) {
        double abc = areal(point1, point2, point3);
        double pab = areal(p, point1, point2);
        double pac = areal(p,point1, point3);
        double pbc = areal(p, point2, point3);

        return abc == (pac+pab+pbc);
    }

    public double circumference() {
         double dis_1_2= Math.sqrt((point2.x-point1.x)^2+(point2.y-point1.y)^2);
         double dis_2_3= Math.sqrt((point3.x-point2.x)^2+(point3.y-point2.y)^2);
         double dis_1_3= Math.sqrt((point3.x-point1.x)^2+(point3.y-point1.y)^2);
         double circumference= dis_1_2+ dis_1_3 + dis_2_3;

         return circumference;
    }


}


class Circle extends  Shape {
    Point center_point = new Point();
    double radius;
    public Circle(Point p, double radius){
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
        double disCP = Math.sqrt((p.x - center_point.x) ^ 2 + (p.y - center_point.y) ^ 2);
        return disCP < radius;
    }
}



class Rectangle extends Shape {
        Point point = new Point();
        int height = 0;
        int width = 0;
        public Rectangle (Point p, int h, int w){
            this.point = p;
            this.height = h;
            this.width = w;
        }
            public Point center() {
                Point center = new Point(point.x+width/2,point.y+height/2);
                return center;
            }
            public double areal() {
                double areal =height*width;
                return areal;
            }
            public double circumference() {
                double circumference =height*2+width*2;
                return circumference;
            }
    public boolean isPointInside(Point p) {
          return point.x < p.x && p.x < point.x+width
                  && point.y < p.y && p.y < point.y+height;
            }
}

