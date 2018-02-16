package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.*;
import org.junit.Test;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Gilad Weiss on 16/05/2017.
 */
public class RenderTest {
    @Test
    public void renderImage() throws Exception {

        ImageWriter imageWriter = new ImageWriter("A Render Test 3D", 500, 500, 2, 2);
        Scene scene = new Scene();
        // scene.setAmbientLight(new AmbientLight(Color.white));
        scene.setBackground(Color.black);
        scene.setCamera(new Camera());

        Geometry geo = new Triangle(new Point3D(0, 30, -50), new Point3D(-30, 0, -50), new Point3D(-30, 30, -35));
        geo.setEmission(new Color(0, 100, 0));
        scene.addGeometry(geo);

        geo = new Triangle(new Point3D(0, -30, -50), new Point3D(-30, 0, -50), new Point3D(-30, -30, -35));
        geo.setEmission(new Color(0, 0, 100));

        scene.addGeometry(geo);

        geo = new Triangle(new Point3D(0, -30, -50), new Point3D(30, 0, -50), new Point3D(30, -30, -35));
        geo.setEmission(new Color(100, 0, 0));

        scene.addGeometry(geo);

        geo = new Triangle(new Point3D(0, 30, -50), new Point3D(30, 0, -50), new Point3D(30, 30, -35));
        // geo.setEmission(Color.black);
        scene.addGeometry(geo);

        geo = new Sphere(15, new Point3D(0, 0, -45));
        //  geo.setEmission(Color.red);
        //Material m = new Material();
        //m.setKs(0.1);
        //geo.setMaterial(m);
        geo.setShininess(500);

        scene.addGeometry(geo);


        scene.addLight(new PointLight(Color.blue, new Point3D(-10, -20, -20), 1.5, 0.01, 0.001));
        scene.addLight(new PointLight(Color.green, new Point3D(-25, 5, -20), 1, 0.01, 0.001));
        scene.addLight(new PointLight(Color.yellow, new Point3D(-10, 35, -20), 1, 0.01, 0.001));

          /*scene.addGeometry(new Triangle(new Point3D(-75,75,-200),new Point3D(75,75,-200),new Point3D(75,65,-200)));
            scene.addGeometry(new Triangle(new Point3D(-75,75,-200),new Point3D(-75,65,-200),new Point3D(75,65,-200)));
            scene.addGeometry(new Triangle(new Point3D(-75,-75,-200),new Point3D(75,-75,-200),new Point3D(75,-65,-200)));
            scene.addGeometry(new Triangle(new Point3D(-75,-75,-200),new Point3D(-75,-65,-200),new Point3D(75,-65,-200)));
            scene.addGeometry(new Triangle(new Point3D(-30,16,-200),new Point3D(0,-30,-200),new Point3D(30,16,-200)));
            scene.addGeometry(new Triangle(new Point3D(30,-16,-200),new Point3D(0,30,-200),new Point3D(-30,-16,-200)));*/
        //scene.addGeometry(new Sphere(50,new Point3D(25,25,-200)));
        //scene.addGeometry(new Sphere(25,new Point3D(-30,-30,-200)));
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        //render.printGrid(imageWriter.getWidth() / 10);
        render.writeToImage();


    }

    @Test
    public void recursiveTest() {
        Scene scene = new Scene();
        scene.setScreenDistance(300);

        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmission(new Color(0, 0, 100));
        sphere.setKt(0.5);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(250, new Point3D(0.0, 0.0, -1000));
        sphere2.setShininess(20);
        sphere2.setEmission(new Color(100, 20, 20));
        sphere2.setKt(0);
        scene.addGeometry(sphere2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Recursive Test 11", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }


    @Test
    public void homework() throws Exception {
        Scene scene = new Scene();
        scene.setScreenDistance(100);

       /* scene.addGeometry(new Plane(new Vector(0, 0, -1), new Point3D(0, 0, 120)));
        scene.addGeometry(new Plane(new Vector(0, 0, -1), new Point3D(0, 0, 140)));
        scene.addGeometry(new Plane(new Vector(0, 0, -1), new Point3D(0, 0, 160)));
        scene.addGeometry(new Plane(new Vector(0, 0, -1), new Point3D(0, 0, 170)));
        scene.addGeometry(new Plane(new Vector(0, 0, -1), new Point3D(0, 0, 180)));
        scene.addGeometry(new Plane(new Vector(0, 0, -1), new Point3D(0, 0, 190)));
        scene.addGeometry(new Plane(new Vector(0, 0, 1), new Point3D(0, 0, -170)));
        scene.addGeometry(new Plane(new Vector(0, 0, 1), new Point3D(0, 0, -180)));
        scene.addGeometry(new Plane(new Vector(0, 0, 1), new Point3D(0, 0, -190)));
        scene.addGeometry(new Plane(new Vector(0, 0, 1), new Point3D(0, 0, -120)));
        scene.addGeometry(new Plane(new Vector(0, 0, 1), new Point3D(0, 0, -140)));*/
        scene.addGeometry(new Plane(new Vector(0, 0, 1), new Point3D(0, 0, -160)));

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));
        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-20, -200, 150),
                new Vector(2, 2, 3), 0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("shit_af", 25, 25, 1, 1);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }

}

