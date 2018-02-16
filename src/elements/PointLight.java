package elements;

import primitives.*;

import java.awt.*;

/**
 * Created by Gilad Weiss on 11/05/2017.
 */
public class PointLight extends Light implements LightSource {
    ///////////////////////////
    //  Fields               //
    ///////////////////////////

    private Point3D _position;  //point of light in space
                    //vv constants to define how light behaves at a certain distance
    private double _Kc, Kl, Kq; //c-constant, l-linear, q-square

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * parametric constructor
     *
     * @param color    color/intensity of light
     * @param position point of light in space
     *                 vvvv constants to define behavior of light at distances
     * @param kc       constant   ^
     * @param kl       linear     ^
     * @param kq       square     ^
     */
    public PointLight(Color color, Point3D position, double kc, double kl, double kq) {
        this._color = new Color(color.getRGB());
        this._position = new Point3D(position);
        this._Kc = kc;
        this.Kl = kl;
        this.Kq = kq;
    }

    /**
     * default constructor
     */
    public PointLight() {
        super();
        this._position = new Point3D();
        this._Kc = 0;
        this.Kl = 0;
        this.Kq = 0;
    }

    /**
     * copy constructor
     *
     * @param that point light to copy
     */
    public PointLight(PointLight that) {
        this._color = new Color(that._color.getRGB());
        this._position = new Point3D(that._position);
        this._Kc = that._Kc;
        this.Kl = that.Kl;
        this.Kq = that.Kq;
    }

    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * get function for light position in space
     *
     * @return point which is position of the light
     */
    public Point3D getPosition() {
        return _position;
    }

    /**
     * set function for light position
     *
     * @param _position point where  light will be
     */
    public void setPosition(Point3D _position) {
        this._position = new Point3D(_position);
    }

    /**
     * get function for singular constant of light dissipation
     *
     * @return singular constant of light dissipation
     */
    public double getKc() {
        return _Kc;
    }

    /**
     * set function for singular constant of light dissipation
     *
     * @param kc singular constant for light dissipation
     */
    public void setKc(double kc) {
        this._Kc = kc;
    }

    /**
     * get function for linear constant of light dissipation
     *
     * @return singular constant of light dissipation
     */
    public double getKl() {
        return Kl;
    }

    /**
     * set function for linear constant of light dissipation
     *
     * @param kl linear constant for light dissipation
     */
    public void setKl(double kl) {
        Kl = kl;
    }

    /**
     * get function for square constant of light dissipation
     *
     * @return square constant of light dissipation
     */
    public double getKq() {
        return Kq;
    }

    /**
     * get function for square constant of light dissipation
     *
     * @param kq square constant of light dissipation
     */
    public void setKq(double kq) {
        Kq = kq;
    }

    /**
     * getter for light color/ intensity from a given point
     *
     * @param point point to measure from
     * @return light color/intensity at given point
     */
    @Override
    public Color getIntensity(Point3D point) {
        double d = point.distance(this._position);
        double factor = _Kc + Kl * d + Kq * d * d;

        if (factor < 0) return new Color(0, 0, 0);
        int[] RGB = new int[3];
        RGB[0] = (int) (this._color.getRed() / factor);
        RGB[1] = (int) (this._color.getGreen() / factor);
        RGB[2] = (int) (this._color.getBlue() / factor);
        for (int i = 0; i < 3; i++)
            if (RGB[i] > 255) RGB[i] = 255;
        return new Color(RGB[0], RGB[1], RGB[2]);
    }

    /**
     * getter for light to point vector
     *
     * @param point point to measure from
     * @return vector connecting the light to the point
     */
    @Override
    public Vector getL(Point3D point) { // light to point vector
        try {
            return Vector.normalize(new Vector(point, _position));
        } catch (ArithmeticException e) {
            return new Vector(0, 0, 0);
        }
    }
}
