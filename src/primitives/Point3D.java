package primitives;

//import java.util.SimpleTimeZone;//<- aliens?

/**
 * Created by Aliens on 6/9/2019.
 */
public class Point3D extends Point2D implements Comparable<Object> {
    private Coordinate _z; //(_,_,Z)

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * coordinate constructor
     *
     * @param _x X coordinate to set
     * @param _y Y coordinate to set
     * @param _z Z coordinate to set
     */
    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        super(_x, _y); //call Point2D coordinate constructor
        this._z = new Coordinate(_z);
    }

    /**
     * double constructor
     *
     * @param _x X double to set
     * @param _y Y double to set
     * @param _z Z double to set
     */
    public Point3D(double _x, double _y, double _z) {
        super(_x, _y);//call Point2D double constructor
        this._z = new Coordinate(_z);
    }

    public Point3D() {
        super();//call Point2D default constructor
        this._z = new Coordinate();
    }

    /**
     * copy constructor
     *
     * @param that point3D to copy
     */
    public Point3D(Point3D that) {
        this(that._x, that._y, that._z);
    }

    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * gets Z coordinate
     *
     * @return Z coordinate
     */
    public Coordinate getZ() {
        return _z;
    }

    /**
     * sets Z coordinate
     *
     * @param _z coordinate to set
     */
    public void setZ(Coordinate _z) {
        this._z = new Coordinate(_z);
    }


    ///////////////////////////
    //  Arithmetic Functions //
    ///////////////////////////

    /**
     * add vector to point - move point by vector
     *
     * @param that vector to move with
     */
    public void add(Vector that) {
        add(that.getHead());
    }

    /**
     * add point values to this point
     *
     * @param that point to add
     */
    public void add(Point3D that) {
        this._x.add(that._x);
        this._y.add(that._y);
        this._z.add(that._z);
    }

    /**
     * subtract vector from point - move point in opposite direction of vector
     *
     * @param that
     */
    public void subtract(Vector that) {
        subtract(that.getHead());
    }

    /**
     * subtract point values from this point
     *
     * @param that point to subtract
     */
    public void subtract(Point3D that) {
        this._x.subtract(that._x);
        this._y.subtract(that._y);
        this._z.subtract(that._z);
    }

    /**
     * multiply point values by double
     *
     * @param factor to multiply by
     * @return
     */
    public Point3D multiply(double factor) {

        this._x.multiply(factor);
        this._y.multiply(factor);
        this._z.multiply(factor);
        return this;

    }

    /**
     * calculate the distance between two points
     *
     * @param that point to compare to
     * @return distance
     */
    public double distance(Point3D that) {
        return Math.sqrt(
                Math.pow(Coordinate.subtract(this._x, that._x).getCoordinate(), 2) +
                        Math.pow(Coordinate.subtract(this._y, that._y).getCoordinate(), 2) +
                        Math.pow(Coordinate.subtract(this._z, that._z).getCoordinate(), 2)
        );
    }


    ///////////////////////////
    //  Static Functions     //
    ///////////////////////////

    /**
     * static add vector
     *
     * @param p point to add to
     * @param v vector to add
     * @return modified point
     */
    public static Point3D add(Point3D p, Vector v) {
        Point3D tmp = new Point3D(p);
        tmp.add(v);
        return tmp;
    }

    /**
     * static add point
     *
     * @param p1 point to add to
     * @param p2 point to add
     * @return modified point
     */
    public static Point3D add(Point3D p1, Point3D p2) {
        Point3D temp = new Point3D(p1);
        temp.add(p2);
        return p1;
    }

    /**
     * static subtract
     *
     * @param p1 point to subtract from
     * @param p2 point to subtract
     * @return modified point
     */
    public static Point3D subtract(Point3D p1, Point3D p2) {
        return Point3D.add(p1, Point3D.multiply(p2, -1.0));
    }

    /**
     * static calculate distance between two points
     *
     * @param p1
     * @param p2
     * @return the distance between two points
     */
    public static double distance(Point3D p1, Point3D p2) {
        return p1.distance(p2);
    }

    /**
     * static multiply of point and double factor
     *
     * @param p
     * @param factor
     * @return result of the multiply
     */
    public static Point3D multiply(Point3D p, Double factor) {
        Point3D temp = new Point3D(p);
        temp.multiply(factor);
        return temp;
    }


    ///////////////////////////
    //  Overriding Functions //
    ///////////////////////////

    /**
     * @param o the other point
     * @return point2d's compareTo and z coordinate's compareTo
     */
    @Override
    public int compareTo(Object o) {
        if (o.getClass() != Point3D.class) return -1;
        Point3D that = new Point3D((Point3D) o);

        int tmp = super.compareTo(new Point2D(that));
        if (tmp == 0) tmp = this._z.compareTo(that._z);
        return tmp;

        /*if (this._z.getCoordinate() < that._z.getCoordinate()) return -1;
        if (this._z.getCoordinate() > that._z.getCoordinate()) return +1;
        return 0;*/
    }

    /**
     * overridden toString function
     * @return Point3D formatted like we know in linear algebra a class
     */
    @Override
    public String toString() {
        return String.format("(%1$s, %2$s, %3$s)", this._x.toString(), this._y.toString(), this._z.toString());
        //return "(" + this._x.toString() + "," + this._y.toString() + "," + this._z.toString() + ")";
    }
}
