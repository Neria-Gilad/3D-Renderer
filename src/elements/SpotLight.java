package elements;

import elements.*;
import javafx.scene.effect.*;
import javafx.scene.effect.Light;
import primitives.*;

import java.awt.*;

/**
 * Created by Gilad Weiss on 11/05/2017.
 */
public class SpotLight extends PointLight {
    ///////////////////////////
    //  Fields               //
    ///////////////////////////
    private Vector _direction; //direction of light

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * parametric constructor
     *
     * @param color     color/intensity of light
     * @param position  point of light in space
     * @param direction direction in which the photons go
     *                  vvvv constants to define behavior of light at distances
     * @param kc        constant   ^
     * @param kl        linear     ^
     * @param kq        square     ^
     */
    public SpotLight(Color color, Point3D position, Vector direction, double kc, double kl, double kq) {
        super(color, position, kc, kl, kq);
        this._direction = Vector.normalize(direction);
    }

    /**
     * default constructor
     */
    public SpotLight() {
        super();
        this._direction = new Vector();
    }

    /**
     * copy constructor
     *
     * @param that spot light to copy
     */
    public SpotLight(SpotLight that) {
        super(that._color, that.getPosition(), that.getKc(), that.getKl(), that.getKq());
        this._direction = Vector.normalize(that._direction);
    }

    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    public Vector getDirection() {
        return _direction;
    }

    public void setDirection(Vector _direction) {
        this._direction = Vector.normalize(_direction);
    }

    /**
     * calculate intensity of light at a given point
     *
     * @param point point to measure from
     * @return intensity of light at given point
     */
    @Override
    public Color getIntensity(Point3D point) {
        Color a = super.getIntensity(point);
        //NTZ 8.6  float factor = (float) Math.abs(Vector.dotProduct(super.getL(point),_direction));
        double factor = Vector.dotProduct(super.getL(point), _direction);

        if (factor < 0) return new Color(0, 0, 0);
        int[] RGB = new int[3];
        RGB[0] = (int) (a.getRed() * factor); //ntz 9.6  | was (a.getRed() / factor)
        RGB[1] = (int) (a.getGreen() * factor);
        RGB[2] = (int) (a.getBlue() * factor);
        for (int i = 0; i < 3; i++)
            if (RGB[i] > 255) RGB[i] = 255;
        return new Color(RGB[0], RGB[1], RGB[2]);
    }


    /**
     * getter for light to point vector
     *
     * @param point point to measure from
     * @return direction fo light
     */
    @Override
    public Vector getL(Point3D point) { // light to point vector
        return  Vector.normalize(_direction);
    }

}
