package scene;

import elements.*;
import geometries.*;

import java.util.*;
import java.awt.Color;

/**
 * Created by Gilad Weiss on 11/05/2017.
 */
public class Scene {
    ///////////////////////////
    //  Fields               //
    ///////////////////////////
    private String _sceneName; // name of scene
    private Color _background; // color of scene without geometries
    private AmbientLight _ambientLight; // ambient light of the scene
    private List<Geometry> _geometries; // geometries populating the scene
    private Camera _camera; // camera looking at the scene
    private double _screenDistance; // distance of screen from camera; camera sees scene through screen
    private List<LightSource> _lights; // light sources in the scene

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * default constructor
     */
    public Scene() {
        this._sceneName = "";
        this._background = new Color(0);
        this._ambientLight = new AmbientLight();
        this._geometries = new ArrayList<Geometry>();
        this._camera = new Camera();
        this._screenDistance = 1;
        this._lights = new ArrayList<LightSource>();
    }

    /**
     * parametric constructor
     *
     * @param _sceneName      name of scene
     * @param _background     background color
     * @param _ambientLight   basic lighting
     * @param _geometries     scene population
     * @param _camera         camera looking at the scene
     * @param _screenDistance distance of screen from camera
     */
    public Scene(String _sceneName, Color _background, AmbientLight _ambientLight, List<Geometry> _geometries, Camera _camera, double _screenDistance, List<LightSource> lights) {
        this._sceneName = new String(_sceneName);
        this._background = new Color(_background.getRGB());
        this._ambientLight = new AmbientLight(_ambientLight);
        this._geometries = new ArrayList<Geometry>(_geometries);
        this._camera = new Camera(_camera);
        this._screenDistance = _screenDistance;
        this._lights = new ArrayList<LightSource>(lights);
    }

    /**
     * copy constructor
     *
     * @param that scene to copy from
     */
    public Scene(Scene that) {
        this._sceneName = new String(that._sceneName);
        this._background = new Color(that._background.getRGB());
        this._ambientLight = new AmbientLight(that._ambientLight);
        this._geometries = new ArrayList<Geometry>(that._geometries);
        this._camera = new Camera(that._camera);
        this._screenDistance = that._screenDistance;
        this._lights = new ArrayList<LightSource>(that._lights);
    }

    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * get function for geometry iterator
     *
     * @return iterator of geometries list
     */
    public Iterator<Geometry> getGeometriesIterator() {
        return _geometries.iterator();
    }

    /**
     * get fuction for scene name
     *
     * @return name of scene
     */
    public String getSceneName() {
        return _sceneName;
    }

    /**
     * set function for scene name
     *
     * @param _sceneName name to set for scene
     */
    public void setSceneName(String _sceneName) {
        this._sceneName = _sceneName;
    }

    /**
     * get function for background color
     *
     * @return color of background
     */
    public Color getBackground() {
        return _background;
    }

    /**
     * aet function for background color
     *
     * @param _background color to set as background
     */
    public void setBackground(Color _background) {
        this._background = new Color(_background.getRGB());
    }

    /**
     * get function for the ambient lighting of the scene
     *
     * @return scene ambient light
     */
    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    /**
     * set function for the ambient light of the scene
     *
     * @param _ambientLight ambient light to set for scene
     */
    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    /**
     * get function for scenes geometries
     *
     * @return list of geometries populating the scene
     */
    public List<Geometry> getGeometries() {
        return _geometries;
    }

    /**
     * set function for geometries populating the scene
     *
     * @param _geometries list of geometries to populate the scene
     */
    public void setGeometries(List<Geometry> _geometries) {
        this._geometries = new ArrayList<Geometry>();
        for(Geometry g :_geometries)
            this._geometries.add(g);
        }

    /**
     * get function for the camera looking at the scene
     *
     * @return camera seeing the scene
     */
    public Camera getCamera() {
        return _camera;
    }

    /**
     * set function for camera seeing the scene
     *
     * @param _camera camera to see scene with
     */
    public void setCamera(Camera _camera) {
        this._camera = new Camera(_camera);
    }

    /**
     * return the distance between camera and screen
     *
     * @return distance of screen from camera
     */
    public double getScreenDistance() {
        return _screenDistance;
    }

    /**
     * sets the distance of the screen from the camera
     *
     * @param _screenDistance distance to place screen from camera
     */
    public void setScreenDistance(double _screenDistance) {
        this._screenDistance = _screenDistance;
    }

    public Iterator<LightSource> getLightsIterator() {
        return _lights.iterator();
    }

    ///////////////////////////
    //  Operation            //
    ///////////////////////////

    /**
     * add geometry to scene population
     *
     * @param that geometry to add
     */
    public void addGeometry(Geometry that) {
        _geometries.add(that);
    }

    public void addLight(LightSource that) {
        _lights.add(that);
    }


    ///////////////////////////
    //  Overriding Functions //
    ///////////////////////////

    /**
     * we were told to do this override function. we don't know what to return in this override, because there wasn't any instructions
     *
     * @return scene name
     */
    @Override
    public String toString() {
        return "Scene name: " + _sceneName;
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
        if (!(o instanceof Scene)) return false;

        Scene scene = (Scene) o;

        if (Double.compare(scene._screenDistance, _screenDistance) != 0) return false;
        if (_sceneName != null ? !_sceneName.equals(scene._sceneName) : scene._sceneName != null) return false;
        if (_background != null ? !_background.equals(scene._background) : scene._background != null) return false;
        if (_ambientLight != null ? !_ambientLight.equals(scene._ambientLight) : scene._ambientLight != null)
            return false;
        if (_geometries != null ? !_geometries.equals(scene._geometries) : scene._geometries != null) return false;
        return _camera != null ? _camera.equals(scene._camera) : scene._camera == null;
    }

}
