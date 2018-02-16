package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Created by Gilad Weiss on 3/20/2017.
 */
public class Cylinder extends RadialGeometry {
    ///////////////////////////
    //  Fields               //
    ///////////////////////////
    private Point3D _axisPoint; //middle of cylinder
    private Vector _axisDirection; //vector of cylinder center


    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * getter for axis point
     * @return axis point
     */
    public Point3D getAxisPoint() {
        return _axisPoint;
    }

    /**
     * setter for axis point
     * @param _axisPoint
     */
    public void setAxisPoint(Point3D _axisPoint) {
        this._axisPoint = new Point3D(_axisPoint);
    }

    /**
     * getter for axis direction
     * @return axis direction
     */
    public Vector getAxisDirection() {
        return _axisDirection;
    }

    /**
     * setter for axis direction
     * @param _axisDirection
     */
    public void setAxisDirection(Vector _axisDirection) {
        this._axisDirection = new Vector(_axisDirection);
    }

    ///////////////////////////
    //  Overriding Functions //
    ///////////////////////////

    /**
     * NOT IMPLEMENTED YET
     *
     * @param ray
     * @return NULL - - - - - NOT IMPLEMENTED YET
     */
    @Override
    public List<Point3D> FindIntersections(Ray ray) {
        return null;
    }

    /**
     * normal from cylinder is the vector that connects the closest point on the cylinder to param point.
     * cylinder axis vector and param point define a plane
     * intersection of cylinder axis ray (axis point, axis direction) with plane is the required point
     * as the vector from it to param point makes a 90 degree angle - normal.
     *
     * @param point
     * @return normal vector
     */
    @Override
    public Vector getNormal(Point3D point) {
        Plane plane = new Plane(this._axisDirection, point);
        Point3D onAxis = plane.FindIntersections(new Ray(this._axisPoint, this._axisDirection)).get(0);
        try {
            return Vector.normalize(new Vector(point, onAxis));
        } catch (Exception e) {
            return null;
        }
    }
}
