package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by non-rasist asian wwe fan on 3/20/2017.
 */
public class Sphere extends RadialGeometry {

    ///////////////////////////
    //  Fields               //
    ///////////////////////////
    private Point3D _center;

    /**
     * parameterial constructor for RenderSphere
     *
     * @param _radius radius of RenderSphere
     * @param _center of RenderSphere, to surround wit radius
     */
    public Sphere(double _radius, Point3D _center) {
        super(_radius);
        this._center = new Point3D(_center);
    }

    /**
     * getter for center
     *
     * @return center of RenderSphere
     */
    public Point3D getCenter() {
        return _center;
    }

    /**
     * setter for center
     *
     * @param _center
     */
    public void setCenter(Point3D _center) {
        this._center = new Point3D(_center);
    }

    /*public Sphere(Point3D _center) {
        this._center = _center;
    }*/

    /**
     * find point using distance fro center and dotProduct
     * use pythagorean theorem to find distance of intersecting ray.
     * if larger than radius- no intersection.
     * then, check if in direction of ray or opposite.
     * if in direction, add point to list.
     */
    @Override
    public List<Point3D> FindIntersections(Ray ray) {
        ArrayList<Point3D> lst = new ArrayList<Point3D>();
        Point3D p0 = new Point3D(ray.getPOO());
        Vector L = new Vector(_center, p0);
        Vector V = ray.getDirection();
        double tm = Vector.dotProduct(L, V);
        double d = Math.sqrt(
                Math.pow(L.length(), 2) -
                        Math.pow(tm, 2)
        );
        if (d > this._radius) return lst; // no intersection
        double th = Math.sqrt(
                Math.pow(this._radius, 2)
                        - Math.pow(d, 2)
        );
        double t1 = tm - th;
        double t2 = tm + th;
        if (t1 > 0)
            lst.add(Point3D.add(p0, Vector.scale(V, t1)));
        if (t2 > 0)
            lst.add(Point3D.add(p0, Vector.scale(V, t2)));
        return lst;
    }

    /**
     * normal from RenderSphere to reference point is the vector that connects the center to the point
     *
     * @param point reference point
     * @return normal from RenderSphere to reference point
     */
    @Override
    public Vector getNormal(Point3D point) {
        try {
            return Vector.normalize(new Vector(point, this._center));
        } catch (Exception e) {
            return null;
        }
    }
}

    //region alternative function to find intersections
   /*

   public List<Point3D> ALTFindIntersections(Ray ray) {
        double x1 = ray.getPOO().getX().getCoordinate();
        double x2 = ray.getDirection().getHead().getX().getCoordinate() - x1;
        double x3 = this._center.getX().getCoordinate();
        double y1 = ray.getPOO().getY().getCoordinate();
        double y2 = ray.getDirection().getHead().getY().getCoordinate() - y1;
        double y3 = this._center.getY().getCoordinate();
        double z1 = ray.getPOO().getZ().getCoordinate();
        double z2 = ray.getDirection().getHead().getZ().getCoordinate() - z1;
        double z3 = this._center.getZ().getCoordinate();
        double r = this._radius;

        double a = Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2);
        double b = 2 * ((x2 - x1) * (x1 - x3) + (y2 - y1) * (y1 - y3) + (z2 - z1) * (z1 - z3));
        double c = Math.pow(x3, 2) + Math.pow(y3, 2) + Math.pow(z3, 2) + Math.pow(x1, 2) + Math.pow(y1, 2) + Math.pow(z1, 2) - 2 * (x3 * x1 + y3 * y1 + z3 * z1) - Math.pow(r, 2);

        ArrayList<Point3D> emptyList = new ArrayList<Point3D>();
        ArrayList<Point3D> list = new ArrayList<Point3D>();
        double delta = Math.sqrt(Math.pow(b, 2) - 4 * a * c);
        if (delta < 0) return emptyList;
        if (delta == 0) {
            double t = -b / (2 * a);
            Point3D p = new Point3D(
                    x1 + (x2 - x1) * t,
                    y1 + (y2 - y1) * t,
                    z1 + (z2 - z1) * t
            );
            list.add(p);
        } else {
            double t1 = (-b + delta) / (2 * a);
            double t2 = (-b - delta) / (2 * a);

            Point3D p1 = new Point3D(
                    x1 + (x2 - x1) * t1,
                    y1 + (y2 - y1) * t1,
                    z1 + (z2 - z1) * t1
            );
            list.add(p1);
            Point3D p2 = new Point3D(
                    x1 + (x2 - x1) * t2,
                    y1 + (y2 - y1) * t2,
                    z1 + (z2 - z1) * t2
            );
            list.add(p2);
        }

        return list;
    }*/
    //endregion
