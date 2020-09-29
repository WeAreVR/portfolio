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
    void circleisPointInside(){
        Point testP = new Point(5,4);
        Point testpp = new Point(9,12);
        Shape test = new Circle(testP,3);
        assertEquals(true, test.isPointInside(testpp));
    }

/*
    @Test
    void rectangleAreal(){
        Point testP = new Point(5,4);
        Point testpp = new Point(9,12);
        assertEquals(true, testP.isPointInside(testpp));
    }
    
 */
}
