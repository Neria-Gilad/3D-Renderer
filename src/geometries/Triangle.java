package geometries;

import jdk.nashorn.internal.runtime.ECMAException;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Gilad Weiss on 3/20/2017.
 *
 * a triangle is composed of three points. these are them
 */
public class Triangle extends Geometry implements FlatGeometry {
    ///////////////////////////
    //  Fields               //
    ///////////////////////////
    private Point3D _p1; //point A of a triangle
    private Point3D _p2; //point B of a triangle
    private Point3D _p3; //point C of a triangle

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * default constructor
     */
    public Triangle() {
        this._p1 = new Point3D();
        this._p2 = new Point3D();
        this._p3 = new Point3D();
    }

    /**
     * parametric constructor
     * set three points to define a triangle
     * @param _p1 //point A of a triangle
     * @param _p2 //point B of a triangle
     * @param _p3 //point C of a triangle
     */
    public Triangle(Point3D _p1, Point3D _p2, Point3D _p3) {
        this._p1 = new Point3D(_p1);
        this._p2 = new Point3D(_p2);
        this._p3 = new Point3D(_p3);
    }

    /**
     * copy constructor
     *
     * @param that triangle to copy
     */
    public Triangle(Triangle that) {
        this(that._p1, that._p2, that._p3);
    }

    /**
     * a constructor we didn't implemented, nobody knows why we included this
     *
     * @param attributes
     */
    public Triangle(Map<String, String> attributes) {
        // We shel not do this messed-up func
    }

    /**
     * getter for P1
     *
     * @return P1 point A
     */
    public Point3D getP1() {
        return _p1;
    }

    /**
     * setter for P1
     *
     * @param _p1 point A
     */
    public void setP1(Point3D _p1) {
        this._p1 = new Point3D(_p1);
    }

    /**
     * getter for P2
     *
     * @return P2 point B
     */
    public Point3D getP2() {
        return _p2;
    }

    /**
     * setter for P2
     *
     * @param _p2 point B
     */
    public void setP2(Point3D _p2) {
        this._p2 = new Point3D(_p2);
    }

    /**
     * getter for P3
     *
     * @return P3 point C
     */
    public Point3D getP3() {
        return _p3;
    }

    /**
     * setter for P3
     *
     * @param _p3 point C
     */
    public void setP3(Point3D _p3) {
        this._p3 = new Point3D(_p3);
    }

    /**
     * help function , therefore private
     *
     * @return 1 if prod positive, else -1
     * @throws ArithmeticException , probably never, but if cant normalize, program throw up
     */
    private static int getIntersectSign(Vector P_P0, Point3D P0, Point3D p1, Point3D p2) throws ArithmeticException {
        Vector N = Vector.normalize(Vector.crossProduct(new Vector(p2, P0), new Vector(p1, P0)));
        double prod = Vector.dotProduct(P_P0, N);
        //return (prod > 0) ? 1 : (prod < 0) ? -1 : 0;
        if (prod > 0) return 1;
        if (prod < 0) return -1;
        return 0;
    }

    /**
     * treat triangle as a plane and find intersection points.
     * then check if points are within triangle boundaries
     */
    @Override
    public List<Point3D> FindIntersections(Ray ray) {
        Point3D P0 = new Point3D(ray.getPOO());
        Vector normal = getNormal(null);//point is irrelevant
        Plane plane = new Plane(normal, _p3);
        List<Point3D> lst = plane.FindIntersections(ray);
        if (lst.size() == 0) return lst;

        List<Point3D> emptyList = new ArrayList<Point3D>();
        Vector P_P0 = new Vector(lst.get(0), P0);
        try {
            //all these return 1, -1 or 0 (+,-,neither)
            int sign1 = getIntersectSign(P_P0, P0, _p1, _p2);
            int sign2 = getIntersectSign(P_P0, P0, _p2, _p3);
            int sign3 = getIntersectSign(P_P0, P0, _p3, _p1);
            //if all signs (+/-) are the same, point is in triangle
            //we treat 0 as both + and - so we count how many there are
            int howManyO = 0;
            if (sign1 == 0) howManyO++;
            if (sign2 == 0) howManyO++;
            if (sign3 == 0) howManyO++;

            //if abs of all the signs is 3, all signs are the same.
            //if 0s are present, they will reduce the abs by the amount of 0s
            //so by counting the number of 0s we get back to 3 if rest of signs are equal
            if ((Math.abs(sign1 + sign2 + sign3) + howManyO == 3))
                return lst;
            else return emptyList; // the intersection point is out of the triangle
        } catch (Exception e) {
            return emptyList;
        }
    }

    /**
     * make two vectors out of two of the triangle's points
     * then apply cross product to get normal vector
     */
    @Override
    public Vector getNormal(Point3D point) {
        Vector v1 = new Vector(this._p2,this._p1);
        Vector v2 = new Vector(this._p3,this._p1);
        try {
            return Vector.scale(Vector.normalize(Vector.crossProduct(v1, v2)), -1);
        } catch (Exception e) {
            return null;
        }
    }
}
