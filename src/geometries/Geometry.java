package geometries;

import primitives.*;
import primitives.Vector;

import java.awt.*;

import java.util.List;

/**
 * Apparently created by Gilad Weiss on 3/20/2017.
 */
public abstract class Geometry {

    ///////////////////////////
    //  Fields               //
    ///////////////////////////
    private Material _material = new Material(); //material geometry is made of
    private double _nShininess = 1; // how much light reflects off geometry
    private Color _emission = new Color(0, 0, 0); // color of geometry

    ///////////////////////////
    //  Abstract  Functions  //
    ///////////////////////////

    /**
     * find all intersection points of a ray with the geometry
     *
     * @param ray intersecting ray
     * @return list of intersection points
     */
    public abstract List<Point3D> FindIntersections(Ray ray);


    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * get normal vector of geometry in relation to a point
     *
     * @param point point which normal has to go through
     * @return normal vector
     */
    public abstract Vector getNormal(Point3D point);

    /**
     * getter for shininess
     *
     * @return shininess
     */
    public double getShininess() {
        return this._nShininess;
    }

    /**
     * getter for material
     *
     * @return material
     */
    public Material getMaterial() {
        return _material;
    }

    /**
     * getter emission
     *
     * @return emission
     */
    public Color getEmission() {
        return this._emission;
    }

    /**
     * setter for shininess
     *
     * @param n
     */
    public void setShininess(double n) {
        this._nShininess = n;
    }

    /**
     * setter for material
     *
     * @param that
     */
    public void setMaterial(Material that) {
        this._material = new Material(that);
    }

    /**
     * setter for emission
     *
     * @param that
     */
    public void setEmission(Color that) {
        this._emission = new Color(that.getRGB());
    }

    /**
     * setter for Ks
     *
     * @param ks
     */
    public void setKs(double ks) {
        this._material.setKs(ks);
    }

    /**
     * setter for Kd
     *
     * @param kd
     */
    public void setKd(double kd) {
        this._material.setKd(kd);
    }

    /**
     * setter for Kr
     *
     * @param kr
     */
    public void setKr(double kr) {
        this._material.setKr(kr);
    }

    /**
     * setter for Kt
     *
     * @param kt
     */
    public void setKt(double kt) {
        this._material.setKt(kt);
    }
}
