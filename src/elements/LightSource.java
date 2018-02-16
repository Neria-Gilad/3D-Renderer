package elements;

import primitives.*;

import java.awt.*;

/**
 * Created by Gilad Weiss on 11/05/2017.
 * This interface describes basic functionality every source of light should have.
 * <p>
 * The interface demand every light's source implement getIntensity and getL
 */
public interface LightSource {
    ///////////////////////////
    //  Abstract  Functions  //
    ///////////////////////////
    public Color getIntensity(Point3D point); //return color/intensity of the light
    public Vector getL(Point3D point); // light to point vector

}
