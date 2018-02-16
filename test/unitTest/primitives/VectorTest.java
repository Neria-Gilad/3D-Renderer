package primitives;

import org.junit.Assert;
import org.junit.Before;
import static java.lang.System.out;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Neria Tzidkani on 28/03/2017.
 */
public class VectorTest {

    /************************************** Point3D tests **************************************************************/
    @Test
    public void Test01() {
        out.println("Test01: Point3D compareTo");
        Point3D point3D = new Point3D(2.0, -7.5, 9.25);
        Point3D instance = new Point3D(2.0, -7.5, 9.25);
        int expResult = 0;
        int result = instance.compareTo(point3D);
        assertEquals(expResult, result);
    }

    @Test
    public void Test02() {
        out.println("Test02: Point3D toString");
        Point3D instance = new Point3D(1.123, 2.569, 3.999);
        String expResult = "(1.12, 2.57, 4.00)";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void Test03() {
        out.println("Test03: Point3D add");
        Vector vector = new Vector(1.25, -2.0, 3.0);
        Point3D instance = new Point3D(4.75, -5.0, 6.0);
        instance.add(vector);
        assertTrue("Add failed! ", instance.compareTo(new Point3D(6.0, -7.0, 9.0)) == 0);
    }

    @Test
    public void Test04() {
        out.println("Test04: Point3D subtract");
        Vector vector = new Vector(1.0, 2.0, 3.0);
        Point3D instance = new Point3D(4.0, 5.0, 6.0);
        instance.subtract(vector);
        assertTrue("Substruct failed! ", instance.compareTo(new Point3D(3.0, 3.0, 3.0)) == 0);
    }

    @Test
    public void Test05() {
        out.println("Test05: Point3D distance");
        Point3D point = new Point3D(-20.5, 55, 9.25);
        Point3D instance = new Point3D(75, -10, -100);
        double expResult = 159.0;
        double result = instance.distance(point);
        assertEquals("Worng distance", expResult, result, 0.01);
    }
    /************************************** Vector tests **************************************************************/
    @Test
    public void Test06(){
        out.println("Test06: Vector Add test");

        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-1.0, -1.0, -1.0);

        v1.add(v2);
        assertTrue(v1.compareTo(new Vector(0.0,0.0,0.0)) == 0);

        v2.add(v1);
        assertTrue(v2.compareTo(v2) == 0);
    }

    @Test
    public void Test07(){
        System.out.println("Test07: Vector Substruct test");

        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-1.0, -1.0, -1.0);

        v1.subtract(v2);
        assertTrue(v1.compareTo(new Vector(2.0,2.0,2.0)) == 0);

        v2.subtract(v1);
        assertTrue(v2.compareTo(new Vector(-3.0,-3.0,-3.0)) == 0);
    }

    @Test
    public void Test08(){
        System.out.println("Test08: Vector Scaling test");

        Vector v1 = new Vector(1.0, 1.0, 1.0);

        v1.scale(1);
        assertTrue(v1.compareTo(v1) == 0);

        v1.scale(2);
        assertTrue(v1.compareTo(new Vector(2.0,2.0,2.0)) == 0);

        v1.scale(-2);
        assertTrue(v1.compareTo(new Vector(-4.0,-4.0,-4.0)) == 0);
    }

    @Test
    public void Test09(){
        System.out.println("Test09: Vector Dot product test");


        Vector v1 = new Vector(3.5,-5,10);
        Vector v2 = new Vector(2.5,7,0.5);

        assertTrue(Double.compare(v1.dotProduct(v2), (8.75 + -35 + 5)) == 0);

    }

    @Test
    public void Test10() {
        System.out.println("Test10: Vector Length test");

        Vector v = new Vector(3.5,-5,10);
        assertTrue(v.length() ==  Math.sqrt(12.25 + 25 + 100));
    }

    @Test
    public void Test11(){
        System.out.printf("Test11: Vector Normalize test -> ");

        Vector v = new Vector(100,-60.781,0.0001);
        System.out.printf("Length = %f  ", v.length());
        v.normalize();
        System.out.printf("Length = %f\n", v.length());

        assertEquals("Incorrect length after normalize! ", 1, v.length(), 1e-10);

        v = new Vector(0,0,0);

        try {
            v.normalize();
            fail("Didn't throw divide by zero exception!");
        } catch (ArithmeticException e) {
            assertTrue(true);
        }

    }

    @Test
    public void Test12(){
        System.out.println("Test12: Vector Cross product test");

        Vector v1 = new Vector(3.5, -5.0, 10.0);
        Vector v2 = new Vector(2.5, 7, 0.5);
        Vector v3 = v1.crossProduct(v2);

        assertEquals("", 0, v3.dotProduct(v2), 1e-10);
        assertEquals("", 0, v3.dotProduct(v1), 1e-10);

        Vector v4 = v2.crossProduct(v1);
        v3.add(v4);
        assertEquals("", 0, v3.length(), 1e-10);
    }

 /*   Vector v1;
    Vector v2;
    Vector v3;
    Vector v4;
    Vector v5;
    Vector v6;
    Vector v7;
    Vector v8;
    Vector v9;
    Vector v10;
    Vector v11;
    Vector v12;
    Vector v13;

    @Before
    public void setUp() throws ArithmeticException  {
        v1 = new Vector();
        v2 = new Vector(2, 3, 4);
        v3 = new Vector(new Point3D(-2, 0, 1));
        v4 = new Vector(new Point3D(new Coordinate(3), new Coordinate(3), new Coordinate(-3)));
        v5 = new Vector(10, -10, 1);
        v6 = new Vector(3, 5, 7);
        v7 = new Vector(1, -2, 1);
        v8 = new Vector(0, 1, 2);
        v9 = new Vector(0, 1, 2);
        v11 = new Vector(v1);
        v12 = new Vector(v2);
        v13 = new Vector(v3);
    }

    @Test
    public void testCopyConstructor() {
        //assertEquals(v1.hashCode(),v11.hashCode());
        Vector test1 = new Vector(2, 3, 4);
        Vector test2 = new Vector(test1);
        assertEquals(test1.toString(), test2.toString());
        test1.subtract(new Vector(3, 5, 8));
        test1.add(new Vector(1, 2, 3));
        test1.scale(2);
        assertNotEquals(test1.toString(), test2.toString());

        test1 = new Vector(new Point3D(2,3,4));
        test2 = new Vector(2,3,4);

        assertEquals(test1.toString(), test2.toString());
        test1.subtract(new Vector(3, 5, 8));
        test1.add(new Vector(1, 2, 3));
        test1.scale(2);
        assertNotEquals(test1.toString(), test2.toString());



    }

    @Test
    public void getHead() throws ArithmeticException {
        Vector v = new Vector(new Point3D(2, 3, 4));
        assertEquals(new Point3D(2.00, 3.00, 4.00).toString(), v.getHead().toString());
        assertTrue(v.getHead().compareTo(new Point3D(2, 3, 4)) == 0);
        assertFalse(v.getHead().compareTo(new Point3D(3, 3, 4)) == 0);
    }

    @Test
    public void setHead() throws ArithmeticException {
        Point3D pt = new Point3D(1, 6, 6);
        Vector vt = v1;
        vt.setHead(pt);
        assertTrue(v1.getHead().compareTo(vt.getHead()) == 0);
        assertTrue(v1.getHead().compareTo(pt) == 0);
    }

    @Test
    public void add() throws ArithmeticException {
        Vector vt = new Vector(v2);
        double a = Math.PI, b = Math.E, c = Math.pow(Math.E, Math.PI);
        Vector addV = new Vector(a, b, c);
        Vector solution = new Vector(2 + a, 3 + b, 4 + c);
        vt.add(addV);
        assertEquals(vt.toString(), solution.toString());

        vt = new Vector(v3);
        solution = new Vector(-2 + a, 0 + b, 1 + c);
        vt.add(addV);
        assertEquals(vt.toString(), solution.toString());

        vt = new Vector(v5);
        solution = new Vector(10 + a, -10 + b, 1 + c);
        vt.add(addV);
        assertEquals(vt.toString(), solution.toString());

        vt = new Vector(v6);
        solution = new Vector(3 + a, 5 + b, 7 + c);
        vt.add(addV);
        assertEquals(vt.toString(), solution.toString());
    }

    @Test
    public void subtract() throws ArithmeticException {
        Vector vt = new Vector(v2);
        double a = Math.PI, b = Math.E, c = Math.pow(Math.E, Math.PI);
        Vector subtractV = new Vector(a, b, c);
        Vector solution = new Vector(2 - a, 3 - b, 4 - c);
        vt.subtract(subtractV);
        assertEquals(vt.toString(), solution.toString());

        vt = new Vector(v3);
        solution = new Vector(-2 - a, 0 - b, 1 - c);
        vt.subtract(subtractV);
        assertEquals(vt.toString(), solution.toString());

        vt = new Vector(v5);
        solution = new Vector(10 - a, -10 - b, 1 - c);
        vt.subtract(subtractV);
        assertEquals(vt.toString(), solution.toString());

        vt = new Vector(v6);
        solution = new Vector(3 - a, 5 - b, 7 - c);
        vt.subtract(subtractV);
        assertEquals(vt.toString(), solution.toString());
    }

    @Test
    public void length() throws ArithmeticException {

        Vector vt = new Vector(v2);
        assertEquals(String.format("%.2f", vt.length()), "5.39");

        vt = new Vector(v3);
        assertEquals(String.format("%.2f", vt.length()), "2.24");

        vt = new Vector(v5);
        assertEquals(String.format("%.2f", vt.length()), "14.18");

        vt = new Vector(v6);
        assertEquals(String.format("%.2f", vt.length()), "9.11");


    }

    @Test
    public void normalize() throws ArithmeticException {
        Vector vt = new Vector(v2);
        Vector solution = new Vector(0.37, 0.56, 0.74);
        vt.normalize();
        assertEquals(vt.toString(), solution.toString());

        vt = new Vector(v3);
        solution = new Vector(-0.89, 0, 0.45);
        vt.normalize();
        assertEquals(vt.toString(), solution.toString());

        vt = new Vector(v5);
        solution = new Vector(0.71, -0.71, 0.07);
        vt.normalize();
        assertEquals(vt.toString(), solution.toString());

        vt = new Vector(v6);
        solution = new Vector(0.33, 0.55, 0.77);
        vt.normalize();
        assertEquals(vt.toString(), solution.toString());

    }

    @Test
    public void scale() throws ArithmeticException {
        double scl = Math.PI;
        Vector vt = new Vector(v2);
        Vector solution = new Vector(2 * scl, 3 * scl, 4 * scl);
        vt.scale(scl);
        assertEquals(vt.toString(), solution.toString());

        vt = new Vector(v6);
        solution = new Vector(3 * scl, 5 * scl, 7 * scl);
        vt.scale(scl);
        assertEquals(vt.toString(), solution.toString());

        vt = new Vector(v7);
        solution = new Vector(1 * scl, -2 * scl, 1 * scl);
        vt.scale(scl);
        assertEquals(vt.toString(), solution.toString());

////////////////

        scl = -Math.E;
        vt = new Vector(v2);
        solution = new Vector(2 * scl, 3 * scl, 4 * scl);
        vt.scale(scl);
        assertEquals(vt.toString(), solution.toString());

        vt = new Vector(v6);
        solution = new Vector(3 * scl, 5 * scl, 7 * scl);
        vt.scale(scl);
        assertEquals(vt.toString(), solution.toString());

        vt = new Vector(v7);
        solution = new Vector(1 * scl, -2 * scl, 1 * scl);
        vt.scale(scl);
        assertEquals(vt.toString(), solution.toString());

////////////////


    }

    @Test
    public void dotProduct() throws ArithmeticException {
        assertTrue(v2.dotProduct(v3) == 0);
        assertTrue(v6.dotProduct(v7) == 0);
        assertTrue(v2.dotProduct(v9) == 11);
    }

    @Test
    public void crossProduct() throws ArithmeticException {

        Vector cross = v2.crossProduct(v3);
        Vector solution = new Vector(3, -10, 6);
        assertNotEquals(cross.toString(), v1.toString());
        assertEquals(cross.toString(), solution.toString());

        cross = v2.crossProduct(v4);
        solution = new Vector(-21, 18, -3);
        assertEquals(cross.toString(), solution.toString());

        cross = v3.crossProduct(v4);
        solution = new Vector(-3, -3, -6);
        assertEquals(cross.toString(), solution.toString());

        cross = v5.crossProduct(v6);
        solution = new Vector(-75, -67, 80);
        assertEquals(cross.toString(), solution.toString());

        cross = v6.crossProduct(v5);
        solution = new Vector(75, 67, -80);
        assertEquals(cross.toString(), solution.toString());
    }


    @Test
    public void Test_toString() throws ArithmeticException {
        Vector v = new Vector(new Point3D(2, 3, 4));
        assertEquals("(2.00, 3.00, 4.00)", v.toString());
    }*/
}