package elements;

import primitives.*;

/**
 * Created by Gilad Weiss on 3/21/2017.
 */
public class Camera {

    ///////////////////////////
    //  Fields               //
    ///////////////////////////

    private Point3D _p0; //position of camera
    private Vector _vUp; //vector pointing up from camera - perpendicular to where camera is facing
    private Vector _vTo; //vector pointing where camera is facing
    private Vector _vRight; //vector pointing to right of csmera - perpendicular to where camera is facing

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * default constructor for camera
     */
    public Camera() {
        _p0 = new Point3D();
        _vUp = new Vector(0, 1, 0);
        _vTo = new Vector(0, 0, -1);
        _vRight = new Vector(1, 0, 0);
    }

    /**
     * parametric constructor for camera
     *
     * @param _p0  camera position in space
     * @param _vUp vector pointing up from camera - perpendicular to where camera is facing
     * @param _vTo vector pointing where camera is facing
     */
    public Camera(Point3D _p0, Vector _vUp, Vector _vTo) {
        this._p0 = new Point3D(_p0);
        this._vUp = new Vector(_vUp);
        this._vTo = new Vector(_vTo);
        this._vRight = Vector.normalize(Vector.crossProduct(_vTo, _vUp));
    }

    /**
     * copy constructor
     *
     * @param that Camera to copy
     */
    public Camera(Camera that) {
        this(that._p0, that._vUp, that._vTo);
    }


    /**
     * The function Construct Ray Through Pixel
     * <p>
     * The actual formula is:
     * P = Pc + [[( x - #pixelsX/2)*Rx +Rx/2]*Vright - [( y - #pixelsY/2)*Ry +Ry/2]*Vup ]
     * where R[] is width/#pixels[] (aka ratio)
     *
     * @param Nx             is number of pixels in x axis
     * @param Ny             is number of pixels in y axis
     * @param x              the index of x axis
     * @param y              the index of y axis
     * @param screenDistance the distance from the camera to the plane
     * @param screenWidth    is the length of the x axis
     * @param screenHeight   is the length of the y axis
     * @return the ray
     * @throws ArithmeticException when vector cannot be normalized
     */
    public Ray constructRayThroughPixel(
            int Nx, int Ny, double x, double y,
            double screenDistance, double screenWidth, double screenHeight) {
        Point3D pc = Point3D.add(_p0, Vector.scale(_vTo, screenDistance));
        Vector moveRight = getMovement(Nx, x, getRatio(screenWidth, Nx), _vRight);
        Vector moveUp = getMovement(Ny, y, getRatio(screenHeight, Ny), _vUp);
        Point3D p = Point3D.add(pc, Vector.subtract(moveRight, moveUp));
        try {
            return new Ray(p, Vector.normalize(new Vector(p, _p0)));
        } catch (Exception e) {
            return null;
        }
    }

    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * getter for camera point of origin
     *
     * @return _p0
     */
    public Point3D get_p0() {
        return _p0;
    }

    /**
     * setter for camera point of origin
     *
     * @param _p0
     */
    public void set_p0(Point3D _p0) {
        this._p0 = _p0;
    }

    /**
     * getter for y component in camera direction
     *
     * @return directional vector
     */
    public Vector get_vUp() {
        return _vUp;
    }

    /**
     * setter for y component in camera direction
     *
     * @return directional vector
     */
    public void set_vUp(Vector _vUp) {
        this._vUp = _vUp;
    }

    /**
     * getter for z component in camera direction
     *
     * @return directional vector
     */
    public Vector get_vTo() {
        return _vTo;
    }

    /**
     * setter for z component in camera direction
     *
     * @return directional vector
     */
    public void set_vTo(Vector _vTo) {
        this._vTo = _vTo;
    }

    /**
     * getter for x component in camera direction
     *
     * @return directional vector
     */
    public Vector get_vRight() {
        return _vRight;
    }

    /**
     * setter for x component in camera direction
     *
     * @return directional vector
     */
    public void set_vRight(Vector _vRight) {
        this._vRight = _vRight;
    }

    /**
     * gets size of each pixel
     *
     * @param length
     * @param numOfPixels
     * @return size of each pixel
     */
    private double getRatio(double length, int numOfPixels) {
        return length / (numOfPixels * 1.0);
    }

    /**
     * gets movement of center dot in a direction, using formula from ConstructRayThroughPixel:
     * P = Pc + [[( x - #pixelsX/2)*Rx +Rx/2]*Vright - [( y - #pixelsY/2)*Ry +Ry/2]*Vup ]
     * where R[] is width/#pixels[] (aka ratio)
     *
     * @param N
     * @param index
     * @param ratio
     * @param direction
     * @return movement
     */
    private Vector getMovement(int N, double index, double ratio, Vector direction) {
        double scale = (((index - (N / 2.0)) * ratio) + (ratio / 2.0));
        return Vector.scale(direction, scale);
    }

    ///////////////////////////
    //  Overriding Functions //
    ///////////////////////////

    /**
     * toStrong overridden function
     *
     * @return formetted string as we required, we don't know why this format.
     */
    @Override
    public String toString() {
        return "Vto: " + _vTo + "\n" + "Vup: " + _vUp + "\n" + "Vright:" + _vRight + ".";
    }


    /**
     * checks if all fields are equal
     *
     * @param o to compare with
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Camera)) return false;

        Camera camera = (Camera) o;

        if (get_p0() != null ? !(get_p0().compareTo(camera.get_p0()) == 0) : camera.get_p0() != null) return false;
        if (get_vUp() != null ? !(get_vUp().compareTo(camera.get_vUp()) == 0) : camera.get_vUp() != null) return false;
        if (get_vTo() != null ? !(get_vTo().compareTo(camera.get_vTo()) == 0) : camera.get_vTo() != null) return false;
        return get_vRight() != null ? (get_vRight().compareTo(camera.get_vRight()) == 0) : camera.get_vRight() == null;
    }

}
