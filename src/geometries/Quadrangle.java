package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neria Tzidkani on 12/06/2017.
 */
public class Quadrangle extends Geometry implements FlatGeometry {
    private Triangle T1, T2;

    public Quadrangle(Triangle t1, Triangle t2) {
        T1 = new Triangle(t1);
        T2 = new Triangle(t2);
    }

    public Quadrangle(Point3D P1, Point3D P2, Point3D P3, Point3D P4) {
        T1 = new Triangle(P1, P2, P4);
        T2 = new Triangle(P2, P3, P4);
    }

    public Quadrangle(Quadrangle that) {
        T1 = new Triangle(that.T1);
        T2 = new Triangle(that.T2);
    }


    /**
     * treat triangle as a plane and find intersection points.
     * then check if points are within quadrangle boundaries
     */
    @Override
    public List<Point3D> FindIntersections(Ray ray) {
        List t1 = T1.FindIntersections(ray);
        t1.addAll(T2.FindIntersections(ray));
        return t1;
    }

    /**
     * make two vectors out of two of the quadrangle's points
     * then apply cross product to get normal vector
     */
    @Override
    public Vector getNormal(Point3D point) {
        return T1.getNormal(point);
    }
}
