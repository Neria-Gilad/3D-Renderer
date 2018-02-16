package primitives;

/**
 * Created by Gilad Weiss on 3/20/2017.
 */
public class Ray {
    private Point3D _POO; //point of origin
    private Vector _direction; //direction in which ray goes

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * default constructor
     */
    public Ray() {
        this._POO = new Point3D();
        this._direction = new Vector();
    }

    /**
     * parameterial constructor
     *
     * @param _POO
     * @param _direction
     */
    public Ray(Point3D _POO, Vector _direction) {
        this._POO = new Point3D(_POO);
        this._direction = new Vector(_direction);
    }

    /**
     * copy constructor
     *
     * @param that
     */
    public Ray(Ray that) {
        this(that._POO, that._direction);
    }


    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /*
     * @return point of origin
     */
    public Point3D getPOO() {
        return _POO;
    }

    /**
     * setter for point of origin
     *
     * @param _POO
     */
    public void setPOO(Point3D _POO) {
        this._POO = new Point3D(_POO);
    }

    /*
     * @return direction
     */
    public Vector getDirection() {
        try {
            return Vector.normalize(_direction);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * setter for direction
     *
     * @param _direction
     * @throws ArithmeticException if cant normalize or something
     */
    public void setDirection(Vector _direction) throws ArithmeticException {
        this._direction = Vector.normalize(new Vector(_direction));
    }
}
