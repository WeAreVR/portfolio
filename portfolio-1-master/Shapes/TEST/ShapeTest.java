import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeTest {

    @Test
    void circleAreal() {
        Point testP = new Point(5,4);
        Shape test = new Circle(testP,3);
        assertEquals(28.27, test.areal(),0.2);
    }
    @Test
    void circleCircumference(){
        Point testP = new Point(5,4);
        Shape test = new Circle(testP,3);
        assertEquals(18.85, test.circumference(),0.2);
    }
    @Test
    void circleIsPointInside(){
        Point testP = new Point(5,4);
        Point testpp = new Point(5,6);
        Shape test = new Circle(testP,3);
        assertEquals(false, test.isPointInside(testpp));
    }


    @Test
    void rectangleIsPointInside(){
        Point p = new Point(1,1);
        Point pp = new Point( 3,4);
        Rectangle test = new Rectangle(p,5,4);
        assertEquals(true, test.isPointInside(pp));
    }

    @Test
    void rectangleAreal(){
        Point p = new Point(0,0);
        Rectangle test = new Rectangle(p,5,4);
        assertEquals(20, test.areal());
    }

    @Test
    void rectangleCircumference(){
        Point p = new Point(0,0);
        Rectangle test = new Rectangle(p,5,4);
        assertEquals(118, test.circumference());
    }

    @Test
    void rectangleCenter(){
        Point p = new Point(0,0);
        Point pp = new Point(5,5);
        Rectangle test = new Rectangle(p,10,10);
        assertEquals(pp, test.center());
    }



    @Test
    void triangleCenter(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(5,0);
        Point p3 = new Point(0,5);
        Point pp = new Point(1,1);
        Triangle test = new Triangle(p1,p2,p3);
        assertEquals(pp, test.center());

    }

    @Test
    void triangleAreal(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(5,0);
        Point p3 = new Point(0,5);

        Triangle test = new Triangle(p1,p2,p3);
        assertEquals(12.5, test.areal(), 0.1);

}

@Test
    void triangleIsPointInside(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(5,0);
        Point p3 = new Point(0,5);
        Point pp = new Point(0,2);

        Triangle test = new Triangle(p1,p2,p3);
        assertEquals(true, test.isPointInside(pp));

}
@Test
    void triangleCircumference(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(5,0);
        Point p3 = new Point(0,5);

        Triangle test = new Triangle(p1,p2,p3);
        assertEquals(17, test.circumference(),0.1);

}


@Test
    void euclideanDistance(){
        Point circlePoint = new Point(5,5);
        Circle circle = new Circle(circlePoint, 5);

        Point rectanglePoint = new Point(0,0);
        Rectangle rectangle = new Rectangle(rectanglePoint,10,10);

    assertEquals(0, circle.euclideanDistance(rectangle,circle),0.1);
}


}
