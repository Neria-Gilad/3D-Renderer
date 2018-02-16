package elements;

import primitives.*;

import java.awt.*;

/**
 * Created by Gilad Weiss on 11/05/2017.
 */
public class DirectionalLight extends Light implements LightSource {

    ///////////////////////////
    //  Fields               //
    ///////////////////////////
    private Vector _direction; //direction in which the photons travel

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * constructor for directional light
     *
     * @param color     the color/intensity of the light
     * @param direction direction of light
     */
    public DirectionalLight(Color color, Vector direction) {
        super(color); //ntz 9.6
        this._direction = Vector.normalize(direction); //ntz 9.6
        //this._direction = new Vector(direction);
    }

    /**
     * default constructor
     */
    public DirectionalLight() {
        super();
        _direction = new Vector();
    }

    /**
     * copy constructor
     *
     * @param that directional light to copy
     */
    public DirectionalLight(DirectionalLight that) {
        this._color = new Color(that._color.getRGB());
        this._direction = new Vector(that._direction);
    }
    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * get light intensity relative to a point in space
     *
     * @param point point of query
     * @return intenisty of light in given point
     */
    @Override
    public Color getIntensity(Point3D point) {
        return this._color;
    }

    /**
     * get direction of the light
     *
     * @return direction vector
     */
    public Vector getDirection() {
        return this._direction;
    }

    /**
     * change or set the direction of the light
     *
     * @param _direction direction to apply
     */
    public void setDirection(Vector _direction) {
        this._direction = Vector.normalize(_direction);
    }

    /**
     * gets the light to point vector
     *
     * @param point point of reference
     * @return vector - light to point
     */
    @Override
    public Vector getL(Point3D point) {
        try {
            return Vector.normalize(_direction);
        } catch (ArithmeticException e) {
            return new Vector(0,0,0);
        }
    }
}
