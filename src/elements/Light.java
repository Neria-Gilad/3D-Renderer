package elements;

import java.awt.*;

/**
 * Created by Gilad Weiss on 11/05/2017.
 */
public abstract class Light {

    ///////////////////////////
    //  Fields               //
    ///////////////////////////

    protected Color _color; // color/intensity of light

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * default constructor
     */
    public Light() {
        this._color = new Color(0,0,0);
    }

    /**
     * parametric constructor for light
     *
     * @param color color/intensity of the light
     */
    public Light(Color color) {
        this._color = new Color(color.getRGB());
    }

    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * gets color/ intensity of te light
     *
     * @return light intensity
     */
    public Color getIntensity() {
        return _color;
    }

}
