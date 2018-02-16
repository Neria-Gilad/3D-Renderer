package primitives;

/**
 * Created by Gilad Weiss on 3/20/2017.
 */
public class Vector implements Comparable<Vector> {

    ///////////////////////////
    //  Fields               //
    ///////////////////////////
    private Point3D _head; //direction of vector in relation to (0,0,0)

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * Basic constructor
     *
     * @param _head
     */
    public Vector(Point3D _head) {
        this._head = new Point3D(_head);
    }

    /**
     * Subtract constructor
     * target - source = distance vector
     */
    public Vector(Point3D target, Point3D source) {
        this._head = new Point3D(target);
        this._head.subtract(source);
    }

    /**
     * constractor with doubles
     *
     * @param xHead
     * @param yHead
     * @param zHead
     */
    public Vector(double xHead, double yHead, double zHead) {
        this._head = new Point3D(xHead, yHead, zHead);
    }

    /**
     * coordinate constructor
     *
     * @param xHead
     * @param yHead
     * @param zHead
     */
    public Vector(Coordinate xHead, Coordinate yHead, Coordinate zHead) {
        this._head = new Point3D(xHead, yHead, zHead);
    }

    /**
     * default constructor
     */
    public Vector() {
        _head = new Point3D();
    }

    /**
     * copy constructor
     */
    public Vector(Vector that) {
        this(that._head);
    }


    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * getter for head field
     *
     * @return head
     */
    public Point3D getHead() {
        return _head;
    }

    /**
     * setter for head field
     *
     * @param _head
     */
    public void setHead(Point3D _head) {
        this._head = new Point3D(_head);
    }


    ///////////////////////////
    //  Static Functions     //
    ///////////////////////////

    /**
     * static dot product
     *
     * @param v1
     * @param v2
     * @return the dot product between 2 vectors
     */
    public static double dotProduct(Vector v1, Vector v2) {
        return v1.dotProduct(v2);
    }

    /**
     * static cross product
     *
     * @param v1
     * @param v2
     * @return the cross product between 2 vectors
     */
    public static Vector crossProduct(Vector v1, Vector v2) {
        return v1.crossProduct(v2);
    }

    /**
     * static normilize
     *
     * @param v
     * @return normalized vector, if possiburu
     * @throws ArithmeticException if the vecturu is (0,0,0)
     */
    public static Vector normalize(Vector v) throws ArithmeticException {
        Vector tempV = new Vector(v);
            tempV.normalize();
        return tempV;
    }

    /**
     * static scaling
     *
     * @param v      vector to scale
     * @param factor double to scale by
     * @return scaled vector
     */
    public static Vector scale(Vector v, double factor) {
        Vector tmp = new Vector(v);
        tmp.scale(factor);
        return tmp;
    }

    /**
     * static vector subtract
     *
     * @param v1 vector to subtract from
     * @param v2 vector to subtract
     * @return the subtract between 2 vectors
     */
    public static Vector subtract(Vector v1, Vector v2) {
        Vector tmp = new Vector(v1);
        tmp.subtract(v2);
        return tmp;
    }

    ///////////////////////////
    //  Arithmetic Functions //
    ///////////////////////////

    /**
     * add vector to another vector
     *
     * @param that vector to add
     */
    public void add(Vector that) {
        this._head.add(that._head);
    }

    /**
     * subtract vector with another vector
     *
     * @param that vector to subtract
     */
    public void subtract(Vector that) {
        this._head.subtract(that._head);
    }

    /**
     * @return length of the vector from reshit hatzirim
     */
    public double length() {
        return this._head.distance(new Point3D(0, 0, 0)); //0's for clarity
    }

    /**
     * factoring the vector with Q grouped number, discrieeeeet
     *
     * @param factor
     */
    public void scale(double factor) {
        this._head.multiply(factor);
    }

    /**
     * (a,b,c)•(x,y,z) = a*x + b*y + c*z (scalar)
     *
     * @param that vector to multiply with
     * @return dot product of the two vectors
     */
    public double dotProduct(Vector that) {
        return Coordinate.multiply(this._head.getX(), that._head.getX().getCoordinate()).getCoordinate() +
                Coordinate.multiply(this._head.getY(), that._head.getY().getCoordinate()).getCoordinate() +
                Coordinate.multiply(this._head.getZ(), that._head.getZ().getCoordinate()).getCoordinate();
    }

    /**
     * (a,b,c)x(x,y,z) = (b*z - c*y , c*x - a*z , a*y - b*x) (vector)
     *
     * @param that vector to multiply with
     * @return cross product of the two vectors
     */
    public Vector crossProduct(Vector that) {

        Vector v1 = new Vector(
                Coordinate.multiply(this._head.getY(), that._head.getZ().getCoordinate()),
                Coordinate.multiply(this._head.getZ(), that._head.getX().getCoordinate()),
                Coordinate.multiply(this._head.getX(), that._head.getY().getCoordinate())
        );
        Vector v2 = new Vector(
                Coordinate.multiply(this._head.getZ(), that._head.getY().getCoordinate()),
                Coordinate.multiply(this._head.getX(), that._head.getZ().getCoordinate()),
                Coordinate.multiply(this._head.getY(), that._head.getX().getCoordinate())
        );

        v1.subtract(v2);

        return v1;
    }

    /**
     * normal = vector/length
     *
     * @throws ArithmeticException if length is zero,
     *                             thereby dividing by the number of fudges we give, if you think the 0 vector can be normalized
     */
    public void normalize() throws ArithmeticException {
        double length = this.length();
        if (length == 0)
            throw new ArithmeticException("cannot normalize ya, u stpid");
        this.scale(1 / length);
    }

    ///////////////////////////
    //  Overriding Functions //
    ///////////////////////////

    /**
     * @return vector string format like with know from linear algebra A class
     */
    @Override
    public String toString() {
        return _head.toString();
    }

    /**
     * @param that vector to compare with
     * @return point3D compareTo
     */
    @Override
    public int compareTo(Vector that) {
        return this._head.compareTo(that._head);
    }
}
