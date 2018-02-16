package primitives;

/**
 * Created by Gilad Weiss on 3/20/2017.
 */
public class Coordinate implements Comparable<Coordinate> {

    private double _coordinate; //real number to represent a coordinate

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * Basic constructor for coordinate
     *
     * @param _coordinate
     */
    public Coordinate(double _coordinate) {
        this._coordinate = _coordinate;
    }

    /**
     * Default constructor for coordinate
     */
    public Coordinate() {
        this._coordinate = 0;
    }

    /**
     * Copy constructor for coordinate
     *
     * @param that
     */
    public Coordinate(Coordinate that) {
        this(that.getCoordinate());
    }

    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * Getter function for coordinate
     *
     * @return coordinate
     */
    public double getCoordinate() {
        return _coordinate;
    }

    /**
     * Setter function for coordinate
     *
     * @param _coordinate
     */
    public void setCoordinate(double _coordinate) {
        this._coordinate = _coordinate;
    }

    ///////////////////////////
    //  Arithmetic Functions //
    ///////////////////////////

    /**
     * adding a coordinate to another coordinate
     *
     * @param c
     */
    public void add(Coordinate c) {
        this._coordinate += c._coordinate;
    }

    /**
     * subtracting a coordinate to another coordinate
     *
     * @param c
     */
    public void subtract(Coordinate c) {
        this._coordinate -= c._coordinate;
    }

    /**
     * factor a coordinate
     *
     * @param factor
     */
    public double multiply(double factor) {
        this._coordinate *= factor;
        return this._coordinate;
    }


    ///////////////////////////
    //  Static Functions     //
    ///////////////////////////

    /**
     * Static multiply
     *
     * @param c
     * @param factor
     * @return factored coordinate
     */
    public static Coordinate multiply(Coordinate c, double factor) {
        return new Coordinate(c._coordinate * factor);
    }

    /*public static Coordinate multiply(Coordinate c1,Coordinate c2){
        return Coordinate.multiply(c1,c2.getCoordinate());
    }*/

    /**
     * Static add function
     *
     * @param c1
     * @param c2
     * @return sum of 2 coordinates
     */
    public static Coordinate add(Coordinate c1, Coordinate c2) {
        Coordinate temp = new Coordinate(c1);
        temp.add(c2);
        return temp;
    }

    /**
     * Static subtract function
     *
     * @param c1
     * @param c2
     * @return sub of 2 coordinates
     */
    public static Coordinate subtract(Coordinate c1, Coordinate c2) {
        return Coordinate.add(c1, Coordinate.multiply(c2, -1));
    }

    ///////////////////////////
    //  Overriding Functions //
    ///////////////////////////

    /**
     * override compare function
     *
     * @param that
     * @return double compareTo function
     */
    @Override
    public int compareTo(Coordinate that) {
        return Double.compare(this._coordinate, that._coordinate);
        /*if (this._coordinate > that._coordinate) return 1;
        if (this._coordinate < that._coordinate) return -1;
        return 0;*/
    }

    /**
     * @return coordinate tha formatted to 2 point after nekuda
     */
    @Override
    public String toString() {
        return String.format("%.2f", _coordinate);
    }
}
