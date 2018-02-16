package primitives;

/**
 * Created by Gilad Weiss on 3/20/2017.
 */
public class Point2D implements Comparable<Object> {

    protected Coordinate _x; //(X,_)
    protected Coordinate _y;//(_,Y)


    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * construct using coordinates
     * @param _x X coordinate to set
     * @param _y Y coordinate to set
     */
    public Point2D(Coordinate _x, Coordinate _y) {
        this._x = new Coordinate(_x);
        this._y = new Coordinate(_y);
    }

    /**
     * construct using double
     * @param _x X double to set
     * @param _y Y double to set
     */
    public Point2D(double _x, double _y) {
        this._x = new Coordinate(_x);
        this._y = new Coordinate(_y);
    }

    /**
     * default constructor, (X,Y) = (0,0)
     */
    public Point2D() {
        this._x = new Coordinate();
        this._y = new Coordinate();
    }

    /**
     * copy constructor
     * @param that point2D to copy
     */
    public Point2D(Point2D that) {
        this(that._x, that._y);
    }


    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * sets the x coordinate of point
     * @param _x x to set
     */
    public void setX(Coordinate _x) {
        this._x = new Coordinate(_x);
    }

    /**
     * aets the y coordinate of point
     * @param _y y to set
     */
    public void setY(Coordinate _y) {
        this._y = new Coordinate(_y);
    }

    /**
     * getter for Y
     * @return Y coordinate
     */
    public Coordinate getY() {
        return _y;
    }

    /**
     * getter for X
     * @return X coordinate
     */
    public Coordinate getX() {
        return _x;
    }


    ///////////////////////////
    //  Arithmetic Functions //
    ///////////////////////////

    /**
     * add values of a point to this point (X+x,Y+y)
     * @param that point to add
     */
    public void add(Point2D that) {
        this._x.add(that._x);
        this._y.add(that._y);
    }

    /**
     * subtract values of a point from this point (X-x,Y-y)
     * @param that point to subtract
     */
    public void subtract(Point2D that) {
        this._x.subtract(that._x);
        this._y.subtract(that._y);
    }


    ///////////////////////////
    //  Overriding Functions //
    ///////////////////////////

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point (x1, y1)
     * if and only if either {@code y0 < y1} or if {@code y0 == y1} and {@code x0 < x1}.
     *
     * @param o the other point
     * @return the value {@code 0} if this string is equal to the argument
     * string (precisely when {@code equals()} returns {@code true});
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    @Override
    public int compareTo(Object o) {
        if (!o.getClass().equals(Point2D.class)) return -1;
        Point2D that = new Point2D((Point2D) o);

        int tmp = this._x.compareTo(that._x);
        if (tmp == 0) tmp = this._y.compareTo(that._y);
        return tmp;
    }

}
