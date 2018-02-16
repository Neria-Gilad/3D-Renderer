package renderer;

import java.util.*;
import java.util.Map.Entry;
import java.awt.Color;

import elements.LightSource;
import primitives.*;
import geometries.*;
import primitives.Vector;
import scene.*;

/**
 * Created by Gilad Weiss on 11/05/2017.
 */
public class Render {
    ///////////////////////////
    //  Fields               //
    ///////////////////////////
    private final int RECURSION_LEVEL = 5; //level of recursion for reflected ray etc.
    private Scene _scene; // scene containing all the geometries to render
    private ImageWriter _imageWriter; // turns a render into a jpg


    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * parametric constructor
     *
     * @param imageWriter imageWriter to use to create jpg
     * @param scene       scene to render
     */
    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;//new ImageWriter(imageWriter);
        this._scene = new Scene(scene);
    }

    ///////////////////////////
    //  Color Math Operators //
    ///////////////////////////

    /**
     * mult a color and a factor (double) to one color
     * over 255 is 255
     * negative factor returns black color
     *
     * @param a
     * @param factor
     * @return multed color
     */
    private Color multColor(Color a, double factor) {
        if (factor < 0) return new Color(0, 0, 0);
        int[] RGB = new int[3];
        RGB[0] = (int) (a.getRed() * factor);
        RGB[1] = (int) (a.getGreen() * factor);
        RGB[2] = (int) (a.getBlue() * factor);
        for (int i = 0; i < 3; i++)
            if (RGB[i] > 255) RGB[i] = 255;

        return new Color(RGB[0], RGB[1], RGB[2]);
    }

    /**
     * add two colors values.
     * over 255 is 255
     *
     * @param a color to add
     * @param b color to add
     * @return addition of both colors
     */
    private Color addColors(Color a, Color b) {

        int[] RGB = new int[3];
        RGB[0] = a.getRed() + b.getRed();
        RGB[1] = a.getGreen() + b.getGreen();
        RGB[2] = a.getBlue() + b.getBlue();
        for (int i = 0; i < 3; i++)
            if (RGB[i] > 255) RGB[i] = 255;
        return new Color(RGB[0], RGB[1], RGB[2]);
    }

    /**
     * sum list of colors to one color, followed by the sum rules explained above
     *
     * @param lst
     * @return final result
     */
    private Color sumColorList(List<Color> lst) {
        Color sum = new Color(0);
        for (Color c : lst)
            sum = addColors(sum, c);
        return sum;
    }

    ///////////////////////////
    //  Phong Model Calc'    //
    ///////////////////////////

    /**
     * @param ks             specular parameter
     * @param v              direct vector from light source
     * @param normal         normal from geometry
     * @param l              vector from light source to geometry
     * @param shininess      material coefficient
     * @param lightIntensity
     * @return specular color
     */
    private Color calcSpecularComp(double ks, Vector v, Vector normal, Vector l, double shininess, Color lightIntensity) {
        //v.scale(-1); //ntz 9.6
        l.scale(-1);
        // R = L - 2(L DOT N)N
        Vector R = Vector.subtract(l, Vector.scale(normal, Vector.dotProduct(l, normal) * 2));
        double DP = Vector.dotProduct(v, R);
        if (DP < 0) return Color.black;
        double factor = Math.pow(DP, shininess) * ks;

        return multColor(lightIntensity, factor);

    }

    /**
     * @param kd             diffusive parameter
     * @param normal         normal from geometry
     * @param l              intersected vector
     * @param lightIntensity
     * @return diffusive color
     */
    private Color calcDiffusiveComp(double kd, Vector normal, Vector l, Color lightIntensity) {
        l.scale(-1);
        double factor = kd * Vector.dotProduct(normal, l);
        return multColor(lightIntensity, factor);

    }

    /**
     * default recursion level parameter function of calcColor
     *
     * @param geometry
     * @param point
     * @param inRay
     * @return
     */
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay) {
        return calcColor(geometry, point, inRay, 0);
    }

    /**
     * calculate intensity of RGB in a point by combining the lights in the scene with the ambient light
     * <p>
     * basically, the idea is array tha holds every color,
     * and in the end, we'll sum the array (ps another new function)
     *
     * @param geometry geometry the point belongs to
     * @param point    point to calculate color of
     * @return intensity of RGB in the given point
     */
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level) {
        if (level == RECURSION_LEVEL) return new Color(0, 0, 0);

        List<Color> lst = new ArrayList<Color>();  // list of colors
        lst.add(_scene.getAmbientLight().getIntensity());
        lst.add(geometry.getEmission());

        Color diffuseLight, specularLight;
        Iterator<LightSource> lights = _scene.getLightsIterator();
        while (lights.hasNext()) {
            LightSource light = lights.next();
            if (!occluded(light, point, geometry)) {
                diffuseLight = calcDiffusiveComp(
                        geometry.getMaterial().getKd(),
                        geometry.getNormal(point),
                        light.getL(point),
                        light.getIntensity(point));
                specularLight = calcSpecularComp(
                        geometry.getMaterial().getKs(),
                        Vector.normalize(new Vector(point, _scene.getCamera().get_p0())),
                        geometry.getNormal(point),
                        light.getL(point),
                        geometry.getShininess(),
                        light.getIntensity(point));
                lst.add(diffuseLight);
                lst.add(specularLight);
            }
        }

        //      <!-- Reflection -->
        double kr = geometry.getMaterial().getKr();
        if (kr > 0) { //else, no need to calculate nothing
            Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
            Entry<Geometry, Point3D> reflectedEntry = findClosestIntersection(reflectedRay);
            if (reflectedEntry != null) {
                Color reflectedColor = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
                Color reflectedLight = multColor(reflectedColor, kr);


                lst.add(reflectedLight);
            }
        }

        //      <!-- Refraction -->
        double kt = geometry.getMaterial().getKt();
        if (kt > 0) { //else, no need to calculate nothing
            Ray refractedRay = constructRefractedRay(geometry, point, inRay);
            Entry<Geometry, Point3D> refractedEntry = findClosestIntersection(refractedRay);
            if (refractedEntry != null) {
                Color refractedColor = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
                Color refractedLight = multColor(refractedColor, kt);

                lst.add(refractedLight);
            }
        }
        return sumColorList(lst);
    }


    ///////////////////////////
    //  advanced lighting    //
    ///////////////////////////

    /**
     * construct refracted ray from intersected ray
     *
     * @param geometry
     * @param point
     * @param inRay
     * @return refracted ray
     */
    private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay) {
        return new Ray(point, inRay.getDirection());

        //region Snell - not in this course
        /*
        Vector normal = geometry.getNormal(point);
        Vector d = Vector.scale(inRay.getDirection(), -1);

        //snell law
        double n1 = 1; //air
        double n2 = geometry.getMaterial().getN();

        double cosO1 = Vector.dotProduct(d, normal);
        double sinO1 = Math.sin(Math.acos(cosO1));
        double sinO2 = (n1 / n2) * sinO1;
        double cosO2 = Math.cos(Math.asin(sinO2));


        // R = (n1/n2)(cosO1-cosO2)N - (n1/n2)D
        Vector R = Vector.subtract(Vector.scale(normal, (n1 / n2) * (cosO1 - cosO2)), Vector.scale(d, (n1 / n2)));
        return new Ray(point, R);
        */
        //endregion
    }

    /**
     * construct reflected ray from intersected ray
     *
     * @param normal
     * @param point
     * @param inRay
     * @return reflected ray
     */
    private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay) {
        // R = L - 2(L DOT N)N
        Vector d = inRay.getDirection();
        Vector R = Vector.subtract(d, Vector.scale(normal, Vector.dotProduct(d, normal) * 2));

        return new Ray(point, R);
    }

    /**
     * check of a geometry hides the light from reaching another geometry
     *
     * @param light
     * @param point    the point we check if hidden on geometry
     * @param geometry the geometry we check if hidden
     * @return true if hidden, false overwise
     */
    private boolean occluded(LightSource light, Point3D point, Geometry geometry) {
        Vector lightDirection = light.getL(point);
        lightDirection.scale(-1);

        Point3D geometryPoint = new Point3D(point);
        Vector epsVector = new Vector(geometry.getNormal(point));
        epsVector.scale(2);

        geometryPoint.add(epsVector);
        Ray lightRay = new Ray(geometryPoint, lightDirection);
        Map<Geometry, List<Point3D>> intersectionPoints =
                getSceneRayIntersections(lightRay);

        // Flat geometry cannot self intersect
        if (geometry instanceof FlatGeometry)
            intersectionPoints.remove(geometry);


        for (Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet())
            if (entry.getKey().getMaterial().getKt() == 0)
                return true;
        return false;
    }
    ///////////////////////////
    //  help functions       //
    ///////////////////////////

    /**
     * find the nearest point to the camera using the "apple tree" method;
     * hold closest point and keep until a closer one is found
     *
     * @param intersectionPoints all the points that are intersections of ray and geometries
     * @return map with one entry- closest point with its geometry
     */
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints) {
        double distance = Double.MAX_VALUE;
        Point3D P0 = _scene.getCamera().get_p0();
        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();
        for (Map.Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet())
            for (Point3D point : entry.getValue()) {
                double P0Distance = P0.distance(point);
                if (P0Distance < distance) {
                    minDistancePoint.clear();
                    minDistancePoint.put(entry.getKey(), new Point3D(point));
                    distance = P0Distance;
                }
            }
        return minDistancePoint;
    }

    /**
     * @param ray
     * @return closest intersection point and geometry of ray
     */
    private Entry<Geometry, Point3D> findClosestIntersection(Ray ray) {

        Point3D epsPoint = new Point3D(ray.getPOO()); //floating point operation problem
        Vector epsVector = new Vector(ray.getDirection());
        epsVector.scale(2);

        epsPoint.add(epsVector);
        Ray lightRay = new Ray(epsPoint, ray.getDirection());

        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);
        double distance = Double.MAX_VALUE;
        Point3D P0 = epsPoint;
        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();
        for (Map.Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet())
            for (Point3D point : entry.getValue()) {
                double P0Distance = P0.distance(point);
                if (P0Distance < distance) {
                    minDistancePoint.clear();
                    minDistancePoint.put(entry.getKey(), new Point3D(point));
                    distance = P0Distance;
                }
            }
        Map<Geometry, Point3D> closestPoint = minDistancePoint;
        if (!intersectionPoints.isEmpty())
            return closestPoint.entrySet().iterator().next();
        else return null;
    }


    /**
     * this function prints a fixed grid over the image at a specified interval
     *
     * @param interval size of side of each square in the grid
     */
    public void printGrid(int interval) {
        int xSize = _imageWriter.getWidth();
        int ySize = _imageWriter.getHeight();
        for (int i = 0; i < ySize; i++)
            for (int j = 0; j < xSize; j++)
                if (i % interval == 0 || j % interval == 0 || i == ySize - 1 || j == xSize - 1)
                    _imageWriter.writePixel(j, i, 255, 255, 255);  // Black
    }

    /**
     * go over all geometries in the scene and find the intersection points with the given ray
     *
     * @param ray magical sight beam that allows camera to see
     * @return map containing all intersection points coupled with their respective geometry
     */
    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {

        Iterator<Geometry> geometries = _scene.getGeometriesIterator();
        Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();
        Geometry geometry;
        while (geometries.hasNext()) {
            geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.FindIntersections(ray);
            if (!geometryIntersectionPoints.isEmpty())
                intersectionPoints.put(geometry, geometryIntersectionPoints);
        }
        return intersectionPoints;
    }


    ///////////////////////////
    //  I/O Functions        //
    ///////////////////////////

    /**
     * take all the information about the scene and render it:
     * make camera rays and find intersections with the geometries
     * calculate the color of the closest intersection point and send to imageWriter buffer.
     */
    public void renderImage() {
        for (int i = 0; i < _imageWriter.getHeight(); i++) {
            double p = (double) i / _imageWriter.getHeight() * 10000;
            int tmp = (int) p;
            p = (double) (tmp / 100.0);
            System.out.println(p + "%");

            for (int j = 0; j < _imageWriter.getWidth(); j++) {
                Ray ray = _scene.getCamera()
                        .constructRayThroughPixel(
                                _imageWriter.getWidth(), _imageWriter.getHeight(),
                                j, i,
                                _scene.getScreenDistance(),
                                _imageWriter.getNx(), _imageWriter.getNy()
                        );
                Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
                if (intersectionPoints.isEmpty())
                    _imageWriter.writePixel(j, i, _scene.getBackground());
                else {
                    Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(j, i, calcColor(closestPoint.keySet().iterator().next(), closestPoint.values().iterator().next(), ray)); //not sure wpf, but gilad wrote it once upon a time
                }
            }
        }
        System.out.println("\n****************************\n");
        System.out.println("Image rendered successfully!\n");
        System.out.println("****************************");
    }

    /**
     * write to image by sending to imageWriter's writeToImage function
     */
    public void writeToImage() {
        _imageWriter.writeToimage();
    }

    ///////////////////////////
    //  Overriding Functions //
    ///////////////////////////

    /**
     * we were told to do this override function. we don't know what to return in this override, because there wasn't any instructions
     *
     * @return rendered scene details
     */
    @Override
    public String toString() {
        return "Renderer for scene. details:\n" + this._scene.toString();
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
        if (!(o instanceof Render)) return false;

        Render render = (Render) o;

        if (RECURSION_LEVEL != render.RECURSION_LEVEL) return false;
        if (_scene != null ? !_scene.equals(render._scene) : render._scene != null) return false;
        return _imageWriter != null ? _imageWriter.equals(render._imageWriter) : render._imageWriter == null;
    }
}
