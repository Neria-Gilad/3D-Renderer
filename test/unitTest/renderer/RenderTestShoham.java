package renderer;

import elements.*;
import geometries.*;
import org.junit.Test;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Shoham on 16/05/2017.
 */
public class RenderTestShoham {
    @Test
    public void renderImage() throws Exception {
        ImageWriter imageWriter = new ImageWriter(" A Render Test", 1500, 1500, 1, 1);
        Scene scene = new Scene();
        scene.setAmbientLight(new AmbientLight());
        scene.setBackground(Color.BLACK);
        scene.setCamera(new Camera());
        SpotLight sp = new SpotLight(Color.green, new Point3D(35, 35, 35), new Vector(new Point3D(35, 35, 35), new Point3D(0, 0, -200)), 1, 1, 1);
        scene.addLight(sp);
        Sphere s = new Sphere(20, new Point3D(0, 0, -200));
        scene.addGeometry(s);
        Triangle t1 = new Triangle(new Point3D(35, 0, -200), new Point3D(0, 35, -200), new Point3D(35, 35, -200));
        scene.addGeometry(t1);
        Triangle t2 = new Triangle(new Point3D(35, 0, -200), new Point3D(0, -35, -200), new Point3D(35, -35, -200));
        t2.setEmission(Color.BLUE);
        scene.addGeometry(t2);
        Triangle t3 = new Triangle(new Point3D(-35, 0, -200), new Point3D(0, -35, -200), new Point3D(-35, -35, -200));
        t3.setEmission(Color.RED);
        scene.addGeometry(t3);
        Triangle t4 = new Triangle(new Point3D(-35, 0, -200), new Point3D(0, 35, -200), new Point3D(-35, 35, -200));
        t4.setEmission(Color.green);
        scene.addGeometry(t4);

        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.printGrid(50);
        render.writeToImage();


    }

    @Test
    public void PointTest1() throws Exception {
        ImageWriter imageWriter = new ImageWriter("PointTest111", 500, 500, 1, 1);
        Scene scene = new Scene();
        scene.setAmbientLight(new AmbientLight());
        scene.setBackground(Color.BLACK);
        scene.setCamera(new Camera());
        LightSource sp = new PointLight(Color.red, new Point3D(30, 50, -20), 0.1, 0.05, 0);
        scene.addLight(sp);
        Point3D PL = new Point3D(15, 22,-20);
        sp = new SpotLight(new Color(69, 100, 0), PL, new Vector(new Point3D(-20, 5, -90), PL),0.1,0.05,0);
        scene.addLight(sp);

        Geometry s = new Sphere(5, new Point3D(0, -4.2, -50));
        s.setEmission(new Color(53, 3, 28));
        s.setShininess(4);
        s.setKd(2);
        s.setKs(1);
        scene.addGeometry(s);

        s = new Sphere(1, new Point3D(3, 6, -41));
        s.setEmission(new Color(53, 3, 28));
        s.setShininess(4);
        s.setKd(2);
        s.setKs(1);
        scene.addGeometry(s);

        s = new Sphere(20, new Point3D(-20, 5, -90));
        //s.setEmission(new Color(53, 3, 28));
        s.setShininess(500);
        s.setKd(0.2);
        s.setKs(2);
        Material m = s.getMaterial();
        m.setKt(1);
        m.setKr(0.2);
        s.setMaterial(m);
        scene.addGeometry(s);

        s = new Quadrangle(new Point3D(25, -10, -85), new Point3D(5, -10, -90),new Point3D(5, 10, -90),new Point3D(25, 10, -85));
       // s.setEmission(Color.white);
        s.setShininess(400);
        s.setKd(0.2);
        s.setKs(1);
        m = s.getMaterial();
        //m.setKt(1);
        m.setKr(1);
        s.setMaterial(m);
        scene.addGeometry(s);

        s = new Sphere(2, new Point3D(2, 3, -45));
        s.setEmission(new Color(53, 3, 28));
        s.setShininess(4);
        s.setKd(2);
        s.setKs(1);
        scene.addGeometry(s);
        Plane p = new Plane(new Vector(0, 100, 3), new Point3D(0, -15, -10));
        p.setKd(2);
        m = p.getMaterial();
        //m.setKt(1);
        //m.setKr(1);
        p.setMaterial(m);
        //p.setEmission(new Color(68, 6, 0));
        scene.addGeometry(p);



        Render render = new Render(imageWriter, scene);
        render.renderImage();

        render.writeToImage();


    }

    @Test
    public void renderImageTest_with_lighting() throws Exception {
        ImageWriter imageWriter = new ImageWriter("lighting's_", 1500, 1500, 100, 100);

        Scene scene = new Scene();//(new AmbientLight(new Color(255,255,255),0.2),new Color(0,0,0),new Camera(),9);
        scene.setAmbientLight(new AmbientLight(Color.white));
        scene.setScreenDistance(9);

        Sphere sphere = new Sphere(490, new Point3D(0, 0, -500));
        sphere.setEmission((Color.blue));
        sphere.setKd(1);
        sphere.setKs(0.5);
        sphere.setShininess(3000000);
        scene.addGeometry(sphere);

        Plane plane = new Plane(new Vector(-0, -0, 1), new Point3D(0, 0, -500));
        plane.setEmission(Color.red);
        //plane.setKd(1);
        //plane.setKs(1);
        plane.setShininess(5);
        scene.addGeometry(plane);

        Triangle triangle = new Triangle(new Point3D(-55, -20, -10), new Point3D(-30, -35, -10), new Point3D(-50, -70, -10));
        triangle.setKd(1);
        triangle.setKs(0.5);
        triangle.setShininess(10);
        triangle.setEmission(Color.ORANGE);
        scene.addGeometry(triangle);

        PointLight pointLight = new PointLight(new Color(238, 238, 0), new Point3D(-10, -50, 0), 0.1, 0.00000001, 0.001);
        scene.addLight(pointLight);

        SpotLight spotLight = new SpotLight(new Color(0, 100, 0), new Point3D(50, 0, 0), new Vector(0, 0, -1), 0.1, 0.00000000001, 0);
        scene.addLight(spotLight);

        DirectionalLight directionalLight = new DirectionalLight(Color.cyan, new Vector(-1, 0, 0));
        scene.addLight(directionalLight);

        Render renderer = new Render(imageWriter, scene);
        renderer.renderImage();
        renderer.writeToImage();
    }

    @Test
    public void SpotLightTest() throws Exception {
        String s = System.getProperty("user.dir");
        ImageWriter imageWriter = new ImageWriter("SpotLightTest", 500, 500, 500, 500);

        Scene scene = new Scene();
        scene.setAmbientLight(new AmbientLight(Color.BLUE));
        scene.setBackground(new Color(0, 0, 0));
        scene.setScreenDistance(100);

        Sphere sph = new Sphere(900, new Point3D(0, 0, -1300));
        sph.setShininess(200);
        sph.setEmission(new Color(40, 60, 11));
        scene.addGeometry(sph);

//        SpotLight spotLight = new SpotLight(new Color(0, 100, 0), new Point3D(50, 0, 0), new Vector(0, 0, -1), 0.1, 0.00000000001, 0);
//        scene.addLight(spotLight);
        SpotLight spl = new SpotLight(
                new Color(255, 0, 0),
                new Point3D(-300, -300, -90),
                new Vector(-200, -200, -1280),
                0.00001, 0.00000000000000002, 0.000001
        );


        SpotLight spotLight = new SpotLight(
                new Color(0, 100, 0),
                new Point3D(50, 0, 0),
                new Vector(0, 0, -1),
                0.1, 0.00000000001, 0);
        scene.addLight(spotLight);


        scene.addLight(spl);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void test3D() throws Exception {
        ImageWriter imageWriter = new ImageWriter(" 3D Test", 500, 500, 500, 500);
        Scene scene = new Scene();

        //scene.setAmbientLight(new AmbientLight());
        //scene.setBackground(Color.BLACK);
        //scene.setCamera(new Camera());
        //SpotLight sp = new SpotLight(Color.white, new Point3D(0, 100, -200), new Vector(new Point3D(0, 100, -200), new Point3D(0, 0, -200)), 5, 5, 5);
        PointLight sp = new PointLight(Color.white, new Point3D(0, 100, -20), 0.00001, 0.00001, 0.00001);
        scene.addLight(sp);
        Sphere s = new Sphere(50, new Point3D(0, 0, -100));
        s.setEmission(Color.blue);
        scene.addGeometry(s);

        Render render = new Render(imageWriter, scene);
        render.renderImage();
        //render.printGrid(50);
        render.writeToImage();


    }

    @Test
    public void basicRendering() throws Exception {


        Camera camera = new Camera(new Point3D(0.0, 0.0, 0.0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        Scene scene = new Scene();//(new AmbientLight(), new Color(0,0,0), camera, 100);
        scene.setScreenDistance(100);
        scene.setCamera(camera);
        Plane plane = new Plane(new Vector(0, 1, 0), new Point3D(0.0, -65.0, 0.0));
        plane.setEmission(Color.BLUE);
        plane.setShininess(200.0);
        plane.setMaterial(new Material(0, 0, 0, 0, 0));
        scene.addGeometry(plane);
        Sphere s = new Sphere(1060.0, new Point3D(0.0, 0.0, -1300.0));
        s.setEmission(new Color(17, 15, 116));
        // s.setMaterial(new Material(1, 1, 0, 0, 1));
        s.setShininess(50);
        scene.addGeometry(s);
//scene.addGeometry(new Triangle(new Point3D(1000.0, -200.0, -600.0), new Point3D(-100.0, -200.0, -1000.0), new Point3D(400.0, 1000.0, -800.0), new Color(0,0,0), 1.0 , new Material(0, 0, 1, 0 ,0)));
//scene.addGeometry(new Sphere(700.0, new Point3D(-200.0, 0.0 , -1500.0),new Color(116,15,17), 35.0 , new Material(0.5, 0.5, 0, 0.5,1)));
//scene.addGeometry(new Sphere(300.0, new Point3D(-200.0, 0.0 , -1500.0),new Color(15,200,17), 35.0 , new Material(1, 1, 0, 0,1)));
        scene.addLight(new PointLight(new Color(255, 50, 50), new Point3D(200.0, 200.0, -20.0), 0.00001, 0.00001, 0.00001));
//scene.addLight(new SpotLight(new Color(255, 50, 50), new Point3D(200.0, 200.0, -20.0), new Vector(-200.0, -200.0, -1280.0),0.002, 0.002, 0.001));
        ImageWriter imageWriter = new ImageWriter("Point_Light", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage(); //render.getImageWriter().writeToimage();

//****************************************************************************************************


//*******************************************************************************************************




    }

    @Test
    public void spotLightTest2(){

        Scene scene = new Scene();
        scene.setScreenDistance(200);
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Point3D(-35, -135, -260),
                new Point3D(-135, -35, -260),
                new Point3D(-135, -135, -270));

        triangle.setEmission(new Color (0, 0, 100));
        triangle.setShininess(4);
        scene.addGeometry(triangle);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Spot test 2", 500, 500, 500, 500);

        Render render = new Render(imageWriter,scene);

        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void PointTestmirror1() throws Exception {
        ImageWriter imageWriter = new ImageWriter(" _mirror1", 500, 500, 1, 1);
        Scene scene = new Scene();
        scene.setAmbientLight(new AmbientLight());
        scene.setBackground(Color.BLACK);
        scene.setCamera(new Camera());
        PointLight sp = new PointLight(Color.red, new Point3D(0, 50, -20), 0.1, 0.05, 0);
        scene.addLight(sp);
        Sphere s = new Sphere(5, new Point3D(0, -4.2, -50));
        s.setKr(1);
        s.setEmission(new Color(0, 14, 53));
        s.setShininess(4);
        s.setKd(1);
         s.setKs(1);
        scene.addGeometry(s);
        Sphere s2 = new Sphere(3, new Point3D(5, 3, -46));
        s2.setEmission(new Color(53, 3, 28));
        s2.setShininess(4);
        s2.setKd(2);
        s2.setKs(1);
        scene.addGeometry(s2);
        Plane p = new Plane(new Vector(0, 100, 3), new Point3D(0, -10, -10));
        p.setKd(2);
        p.setEmission(new Color(68, 6, 0));
        scene.addGeometry(p);


        Render render = new Render( imageWriter,scene);
        render.renderImage();

        render.writeToImage();


    }

    @Test
    public void recursiveTest2(){

        Scene scene = new Scene();
        scene.setScreenDistance(300);
        Camera c = scene.getCamera();
      //  c.set_p0(new Point3D(0,0,1300));
        scene.setCamera(c);

        Sphere sphere = new Sphere(300, new Point3D(-550, -500, -1000));
        sphere.setShininess(20);
        sphere.setEmission(new Color(0, 0, 100));
        sphere.setKt(0.5);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(150, new Point3D(-550, -500, -1000));
        sphere2.setShininess(20);
        sphere2.setEmission(new Color(100, 20, 20));
        sphere2.setKt(0);
        scene.addGeometry(sphere2);

        Triangle triangle = new Triangle(new Point3D(  1500, -1500, -1500),
                new Point3D( -1500,  1500, -1500),
                new Point3D(  200,  200, -375));

        Triangle triangle2 = new Triangle(new Point3D(  1500, -1500, -1500),
                new Point3D( -1500,  1500, -1500),
                new Point3D( -1500, -1500, -1500));

        triangle.setEmission(new Color(20, 20, 20));
        triangle2.setEmission(new Color(20, 20, 20));
        triangle.setKr(1);
        triangle2.setKr(0.5);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100),  new Point3D(200, 200, -150),
                new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Recursive Test 2", 500, 500, 500, 500);

        Render render = new Render(imageWriter,scene);


        render.renderImage();
        render.writeToImage();

    }

   /* @Test
    public void emmissionTest()
    {
        Scene scene = new Scene();
        scene.setScreenDistance(80);

        Sphere RenderSphere = new Sphere(50, new Point3D(0.0, 0.0, -100));
        Triangle triangle = new Triangle(new Point3D( 150, 0, -100),
                new Point3D(  0, 150, -100),
                new Point3D( 150, 150, -100));

        Triangle triangle2 = new Triangle(new Point3D( 150, 0, -100),
                new Point3D(  0, -150, -100),
                new Point3D( 150,-150, -100));

        Triangle triangle3 = new Triangle(new Point3D(-150, 0, -100),
                new Point3D(  0, 150, -100),
                new Point3D(-150, 150, -100));

        Triangle triangle4 = new Triangle(new Point3D(-150, 0, -100),
                new Point3D(  0,  -150, -100),
                new Point3D(-150, -150, -100));

        RenderSphere.setEmmission(new Color (255, 255, 255));
        triangle.setEmmission(new Color (255, 255, 255));
        triangle2.setEmmission(new Color (255, 255, 255));
        triangle3.setEmmission(new Color (255, 255, 255));
        triangle4.setEmmission(new Color (255, 255, 255));

        scene.addGeometry(RenderSphere);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter = new ImageWriter("Emmission test", 500, 500, 500, 500);

        scene.setBackground(Color.BLACK);

        Render render = new Render(scene,imageWriter);
        render.renderImage();
        render.printGrid(50);
        render.writeToImage();

    }*/

}