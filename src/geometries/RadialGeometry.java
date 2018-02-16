package geometries;

/**
 * Created by Gilad Weiss on 3/20/2017.
 */
public abstract class RadialGeometry extends Geometry implements Comparable<RadialGeometry> {
    ///////////////////////////
    //  Fields               //
    ///////////////////////////
    protected double _radius;

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * parameter constructor
     *
     * @param _radius radius to apply to geometry
     */
    public RadialGeometry(double _radius) {
        this._radius = _radius;
    }

    /**
     * default constructor
     */
    public RadialGeometry() {
        this._radius = 0;
    }


    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * getter for radius
     *
     * @return radius of geometry
     */
    public double getRadius() {
        return _radius;
    }

    /**
     * setter for radius
     *
     * @param _radius new radius for geometry
     */
    public void setRadius(double _radius) {
        this._radius = _radius;
    }


    ///////////////////////////
    //  Overriding Functions //
    ///////////////////////////

    /**
     * @param o geometry to compare to
     * @return double's compareTo on radius
     */
    @Override
    public int compareTo(RadialGeometry o) {
        return Double.compare(this._radius, o._radius);
    }

    /**
     * @return double to string of radius
     */
    @Override
    public String toString() {
        return Double.toString(_radius);
    }
}
