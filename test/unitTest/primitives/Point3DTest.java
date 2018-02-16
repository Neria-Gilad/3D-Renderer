package primitives;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Gilad Weiss on 28/03/2017.
 */
public class Point3DTest {
    @Test
    public void add() throws ArithmeticException {
        Point3D p1 = new Point3D(2, 3, 4);
        Point3D p2 = new Point3D(new Coordinate(2), new Coordinate(3), new Coordinate(4));
        Point3D addP = new Point3D(Math.PI, Math.E, Math.pow(Math.E, Math.PI));
        assertEquals(p1.toString(), p2.toString());
        p1.add(addP);
        p2.add(addP);
        assertEquals(p1.toString(), p2.toString());
        assertEquals(p1.toString(), new Point3D(Math.PI + 2, Math.E + 3, Math.pow(Math.E, Math.PI) + 4).toString());
        assertEquals(p2.toString(), new Point3D(Math.PI + 2, Math.E + 3, Math.pow(Math.E, Math.PI) + 4).toString());


    }

    @Test
    public void subtract() throws ArithmeticException  {
        Point3D p1 = new Point3D(2, 3, 4);
        Point3D p2 = new Point3D(new Coordinate(2), new Coordinate(3), new Coordinate(4));
        Point3D subtractP = new Point3D(Math.PI, Math.E, Math.pow(Math.E, Math.PI));
        assertEquals(p1.toString(), p2.toString());
        p1.subtract(subtractP);
        p2.subtract(subtractP);
        assertEquals(p1.toString(), p2.toString());
        assertEquals(p1.toString(), new Point3D(2 - Math.PI, 3 - Math.E, 4 - Math.pow(Math.E, Math.PI)).toString());
        assertEquals(p2.toString(), new Point3D(2 - Math.PI, 3 - Math.E, 4 - Math.pow(Math.E, Math.PI)).toString());
    }


    @Test
    public void zeroLovely() throws ArithmeticException  {
        Point3D p1 = new Point3D(1, 0, 2);
        Point3D p2 = new Point3D(1, -0, 2);
        assertTrue(p1.compareTo(p2)==0);
    }

    @Test
    public void distance() throws ArithmeticException  {
        Point3D p1 = new Point3D(3, 4, 5);
        Point3D p2 = new Point3D(4, -3, -7.33);
        assertEquals(Double.toString(p1.distance(p2)), "14.213687065642047");

        p1 = new Point3D(0, 0, 0);
        p2 = new Point3D(-0, -0, -0);
        assertEquals(Double.toString(p1.distance(p2)), "0.0");


    }

    @Test
    public void multiply() throws ArithmeticException  {

    }

}