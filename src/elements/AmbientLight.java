package elements;

import java.awt.*;
import java.util.Map;

/**
 * Created by Gilad Weiss on 11/05/2017.
 */

/**
 * defines a light given by no particular source
 */
public class AmbientLight extends Light {
    ///////////////////////////
    //  Fields               //
    ///////////////////////////
    private final double _Ka = 0.2; //0-1 light intensity multiplier

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * default constructor
     */
    public AmbientLight() {
        super();
    }

    /**
     * copy constructor
     *
     * @param that ambient light to copy
     */
    public AmbientLight(AmbientLight that) {
        super(that._color);
    }

    /**
     * rgb constructor
     * light intensity is based on 3 colors
     *
     * @param r red
     * @param g green
     * @param b blue
     */
    public AmbientLight(int r, int g, int b) {
        _color = new Color(r, g, b);
    }

    /**
     * color constructor
     *
     * @param c color/intensity light should be
     */
    public AmbientLight(Color c) {
        _color = new Color(c.getRGB());
    }

    /**
     * map constructor
     * @param attributes map to construct by
     */
    // public AmbientLight(Map<String, String> attributes);

    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * getter function for color of light
     *
     * @return color of light
     */
    public Color getColor() {
        return this._color;
    }

    /**
     * setter for color
     *
     * @param color color to set  for light
     */
    public void setColor(Color color) {
        this._color = new Color(color.getRGB());
    }

    /**
     * gets intensity multiplier
     *
     * @return intensity multiplier
     */
    public double getKa() {
        return _Ka;
    }

    /**
     * returnns light intensity
     *
     * @return light intensity
     */
    @Override
    public Color getIntensity() {
        return new Color(
                (int) (_Ka * _color.getRed()),
                (int) (_Ka * _color.getGreen()),
                (int) (_Ka * _color.getBlue())
        );
    }

    ///////////////////////////
    //  Overriding Functions //
    ///////////////////////////

    /**
     * we were told to do this override function. we don't know what to return in this override, because there wasn't any instructions
     *
     * @return color and multiplier
     */
    @Override
    public String toString() {
        return "Ambient light." +
                "\ncolor: " + _color.toString() +
                "\nmultiplier: " + _Ka;
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
        if (!(o instanceof AmbientLight)) return false;

        AmbientLight that = (AmbientLight) o;

        return Double.compare(that._Ka, _Ka) == 0;
    }


}
