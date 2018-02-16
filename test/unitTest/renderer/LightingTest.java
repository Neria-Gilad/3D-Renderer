package tests;

import java.awt.Color;

import elements.AmbientLight;
import elements.Camera;
import geometries.Quadrangle;
import org.junit.Test;

import elements.PointLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class LightingTest {


    @Test
    public void EmissionTest() {

        Scene scene = new Scene();
        scene.setScreenDistance(50);
        scene.addGeometry(new Sphere(50, new Point3D(0.0, 0.0, -50)));

        Triangle triangle = new Triangle(new Point3D(100, 0, -49),
                new Point3D(0, 100, -49),
                new Point3D(100, 100, -49));

        Triangle triangle2 = new Triangle(new Point3D(100, 0, -49),
                new Point3D(0, -100, -49),
                new Point3D(100, -100, -49));
        triangle2.setEmission(new Color(50, 200, 50));

        Triangle triangle3 = new Triangle(new Point3D(-100, 0, -49),
                new Point3D(0, 100, -49),
                new Point3D(-100, 100, -49));
        triangle3.setEmission(new Color(50, 50, 200));

        Triangle triangle4 = new Triangle(new Point3D(-100, 0, -49),
                new Point3D(0, -100, -49),
                new Point3D(-100, -100, -49));
        triangle4.setEmission(new Color(200, 50, 50));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter = new ImageWriter("Emission test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(50);
        render.writeToImage();
    }


    @Test
    public void spotLightTest2() {

        Scene scene = new Scene();
        scene.setScreenDistance(200);
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Point3D(-125, -225, -260),
                new Point3D(-225, -125, -260),
                new Point3D(-225, -225, -270));

        triangle.setEmission(new Color(0, 0, 100));
        triangle.setShininess(4);
        scene.addGeometry(triangle);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Spot test 2", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }


    @Test
    public void spotLightTest() {

        Scene scene = new Scene();
        scene.setScreenDistance(100);

        Sphere sphere = new Sphere(800, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);
        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -100),
                new Vector(2, 2, -3), 0, 0.00001, 0.000005));
        scene.setScreenDistance(100);
        ImageWriter imageWriter = new ImageWriter("Spot test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }


    @Test
    public void pointLightTest() {

        Scene scene = new Scene();
        scene.setScreenDistance(100);

        Sphere sphere = new Sphere(800, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);
        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(-200, -200, -100),
                0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Point test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();


    }

    @Test
    public void spotLightTest3() {

        Scene scene = new Scene();
        scene.setScreenDistance(100);

        Triangle triangle = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, -3500, -1000),
                new Point3D(3500, -3500, -2000));

        Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, 3500, -1000),
                new Point3D(-3500, -3500, -1000));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter("Spot test 3", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void pointLightTest2() {

        Scene scene = new Scene();
        scene.setScreenDistance(100);
//scene.setAmbientLight(new AmbientLight(Color.white));
        Sphere sphere = new Sphere(800, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmission(new Color(0, 0, 100));

        Triangle triangle = new Triangle(new Point3D(200, 280, -200),
                new Point3D(80, 140, -100),
                new Point3D(200, 140, -150));
        triangle.setEmission(new Color(100, 0, 0));
        Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, 3500, -1000),
                new Point3D(-3500, -3500, -1000));

        scene.addGeometry(triangle);
        //scene.addGeometry(triangle2);
        scene.addGeometry(sphere);
        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                0, 0.000001, 0.0000005));
        scene.addLight(new PointLight(new Color(0, 150, 0), new Point3D(-200, -140, -100),
                0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter("OMG", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void shadowTest() {

        Scene scene = new Scene();
        scene.setScreenDistance(100);
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        // scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255)));
        sphere.setEmission(new Color(0, 0, 100));

        //scene.addGeometry(RenderSphere);

       /* Triangle triangle1 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, -3500, -2000),
                new Point3D(3500, -3500, -2000));
        Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, 3500, -2000),
                new Point3D(-3500, -3500, -2000));*/
        //triangle1.setEmission(Color.red);
        //triangle2.setEmission(Color.red);
        //scene.addGeometry(triangle1);
        //scene.addGeometry(triangle2);

        Quadrangle quadrangle = new Quadrangle(
                new Triangle(
                        new Point3D(3500, 3500, -2000),
                        new Point3D(-3500, -3500, -2000),
                        new Point3D(3500, -3500, -2000)
                ),
                new Triangle(
                        new Point3D(3500, 3500, -2000),
                        new Point3D(-3500, 3500, -2000),
                        new Point3D(-3500, -3500, -2000)));

        quadrangle.setEmission(Color.red);
        scene.addGeometry(quadrangle);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100), new Vector(-2, -2, -3), 0, 0.000001, 0.0000005 * 0));


        ImageWriter imageWriter = new ImageWriter("Shadow test", 1500, 1500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }
}

	


