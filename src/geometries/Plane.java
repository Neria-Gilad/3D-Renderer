package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gilderrr Weiss on 3/20/2017.
 */
public class Plane extends Geometry implements FlatGeometry {
    ///////////////////////////
    //  Fields               //
    ///////////////////////////
    private Vector _normal;
    private Point3D _Q;

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////
    /**
     * default constructor
     * default constructor set _normal to (0,0,0) which isn't a direction-vector
     */
    public Plane() {
        this._normal = new Vector();
        this._Q = new Point3D();
    }

    /**
     * copy constructor
     *
     * @param that
     */
    public Plane(Plane that) {
        this(that._normal, that._Q);
    }


    /**
     * parameterial constructor
     * use 3 points in space to construct a plane
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        Vector v1 = new Vector(p1, p2);
        Vector v2 = new Vector(p1, p3);
        this._normal = Vector.normalize(Vector.crossProduct(v1, v2));
        this._Q = new Point3D(p1);
    }

    /**
     * parameterial constructor
     *
     * @param normal
     * @param q
     */
    public Plane(Vector normal, Point3D q) {
        this._normal = new Vector(normal);
        try {
            _normal.normalize();
        } catch (Exception e) {
        }
        this._Q = new Point3D(q);
    }
    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * PI: aX+bY+cZ+d=0
     * _Q is (a,b,c) point
     * _normal is (X,Y,Z) vector
     *
     * @return d of plane cartesian form
     * @throws ArithmeticException if the _normal property cannot be normalized
     */
    public double getD() throws ArithmeticException {
        return -(Vector.dotProduct(_normal, new Vector(_Q)));
    }

    /**
     * @param point
     * @return normal direction of plane
     */
    @Override
    public Vector getNormal(Point3D point) {
        //if (isOnGeometry(point))
        return _normal;
        //return null;
    }

    /**
     * getter for Q
     *
     * @return Q
     */
    public Point3D getQ() {
        return this._Q;
    }

    /**
     * setter for normal
     *
     * @param normal
     * @throws ArithmeticException if vector cannot be normilized
     */
    public void setNormal(Vector normal) throws ArithmeticException {
        this._normal = Vector.normalize(normal);
    }

    /**
     * setter for Q
     *
     * @param d
     */
    public void setQ(Point3D d) {
        this._Q = new Point3D(d);
    }

    ///////////////////////////
    //  Operation            //
    ///////////////////////////
    /**
     * if the ray is parallel to the normal of the plane, there are no intersections, returns NULL.
     * <p>
     * if ray presented like that: L: (Pa,Pb,Pc)+ t (Ta,Tb,Tc)
     * and plane like: PI: aX+bY+cZ+d=0
     * coefficient of intersection point is (named as CO):
     * ,       -d-Pa*a-Pb*b-Pc*c
     * CO =   ------------------(divide by)
     * ,        Ta*a+Tb*b+Tc*c
     * and the point is  P=( Pa+Ta*CO , Pb+Tb*CO , Pc+Tc*CO )
     */
    @Override
    public List<Point3D> FindIntersections(Ray ray) {
        ArrayList<Point3D> lst = new ArrayList<Point3D>();
        List<Point3D> emptyList = new ArrayList<Point3D>();
        if (Vector.dotProduct(ray.getDirection(), this._normal) == 0) return emptyList;
        try {
            double CO =
                    (-this.getD() - (new Vector(ray.getPOO()).dotProduct(this._normal)))
                            /
                            (ray.getDirection().dotProduct(this._normal));
            Vector toAdd = Vector.scale(ray.getDirection(), CO);
            Point3D tmp = Point3D.add(ray.getPOO(), toAdd);

            if (CO > 0) lst.add(tmp);
        } catch (Exception e) {
            return emptyList;
        }
        return lst;
    }

    /**
     * @param point
     * @return true if the point is on the geometry, false if not.
     * <p>
     * plane eq' is aX+bY+cZ+d=0.
     * a is _normal.x
     * b is _normal.y
     * c is _normal.z
     * d is negative of (a*_Q.x + b*_Q.y + c*_Q.z)
     */
    boolean isOnGeometry(Point3D point) {
        double a = this._normal.getHead().getX().getCoordinate();
        double b = this._normal.getHead().getY().getCoordinate();
        double c = this._normal.getHead().getZ().getCoordinate();
        double d = -(a * _Q.getX().getCoordinate() + b * _Q.getY().getCoordinate() + c * _Q.getZ().getCoordinate());
        double d2 = -(a * point.getX().getCoordinate() + b * point.getY().getCoordinate() + c * point.getZ().getCoordinate());

        if (d == d2) return true;
        return false;
    }

}
