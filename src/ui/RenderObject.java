package ui;

import elements.*;
import geometries.Plane;
import geometries.Quadrangle;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.awt.*;

/**
 * Created by Neria Tzidkani on 13/06/2017.
 */
public class RenderObject {

    public static boolean Animal(LightSource ls, String filename,int size) {
        try {
            Camera camera = new Camera();
            camera.set_p0(new Point3D(7.5, 8, 2500));
            Scene scene = new Scene();

            scene.setScreenDistance(130);
            scene.setCamera(camera);

            if (ls instanceof PointLight) {
                ((PointLight) ls).setKl(0.04);
                ((PointLight) ls).setKq(0.00005);
                ((PointLight) ls).setPosition(new Point3D(10, 15, 110));
            }


            scene.addLight(ls);
            //region POINTS

            Point3D p1 = new Point3D(0.666, 0.5, 102);
            Point3D p2 = new Point3D(0, 3.1, 102);
            Point3D p3 = new Point3D(0.333, 5.5, 100 - 0.5); //
            Point3D p4 = new Point3D(0.333, 7.75, 102.25);
            Point3D p4b = new Point3D(0.333, 7.75, 98.75);
            Point3D p5 = new Point3D(2, 9.666, 99.7);
            Point3D p6 = new Point3D(6, 9, 100);
            Point3D p7 = new Point3D(8, 9.5, 99.7);
            Point3D p8 = new Point3D(10, 9, 100);
            Point3D p9 = new Point3D(12, 11.333, 100);
            Point3D p10 = new Point3D(12, 12.5, 99.5);
            Point3D p11 = new Point3D(14, 12.5, 100.5);
            Point3D p12 = new Point3D(13, 12, 99.8);
            Point3D p13 = new Point3D(14.5, 10.5, 100);
            Point3D p14 = new Point3D(15.8, 9.5, 100);
            Point3D p15 = new Point3D(15.8, 8.7, 100);
            Point3D p16 = new Point3D(13.5, 9.666, 100.71);
            Point3D p17 = new Point3D(13, 9.25, 100.7);
            Point3D p18 = new Point3D(11.5, 5, 100);
            Point3D p19 = new Point3D(10.15, -0.2, 98); //y = 0.5
            Point3D p20 = new Point3D(9.333, -0.2, 102); // y = 0.5
            Point3D p21 = new Point3D(10, 2.5, 102);
            Point3D p22 = new Point3D(10, 5.5, 99.7);
            Point3D p23 = new Point3D(7, 6, 102.25);
            Point3D p24 = new Point3D(3.333, 5.9, 101.5);
            Point3D p25 = new Point3D(3, 5.5, 101.5);
            Point3D p26 = new Point3D(2.5, 5.2, 100.1);
            Point3D p27 = new Point3D(1.75, 0.5, 98);
            Point3D p28 = new Point3D(4.8, 3.8, 100.5);
            Point3D p29 = new Point3D(9, 4, 100);
            Point3D p30 = new Point3D(1.666, 4.45, 101.7);
            Point3D p31 = new Point3D(11.5, 8, 98);
            Point3D p32 = new Point3D(12.8, 11.333, 100);
            Point3D p33 = new Point3D(13.4, 11.333, 100);
            Point3D p34 = new Point3D(9.5, 3.25, 100 - 0.1); //29r

//endregion

            //region HORN POINTS & TRIANGLES & ADDING TO SCENE
            Point3D k1 = new Point3D(11, 15, 0.5 + 100);
            Point3D k2 = new Point3D(11.3, 14.5, 0.4 + 100);
            Point3D k3 = new Point3D(7, 16, 0.7 + 100);
            Point3D k4 = new Point3D(7.7, 15.75, 0.65 + 100);
            Point3D k5 = new Point3D(4, 15, 0.7 + 100);

            //Point3D k32 = new Point3D(p32);
            //Point3D k33 = new Point3D(p33);


            Triangle h1 = new Triangle(p33, p32, k1);
            Triangle h2 = new Triangle(k1, k2, k3);
            Triangle h3 = new Triangle(k3, k4, k5);

            scene.addGeometry(h1);
            scene.addGeometry(h2);
            scene.addGeometry(h3);

            //endregion

            //region TRIANGLES

            Triangle a = new Triangle(p1, p2, p30);
            a.setEmission(new Color(142, 112, 86));
            Triangle b = new Triangle(p2, p3, p25);
            b.setEmission(new Color(142, 112, 86));

            Triangle c = new Triangle(p3, p4, p25);
            c.setEmission(new Color(142, 112, 86));

            Triangle d = new Triangle(p4, p6, p25);
            d.setEmission(new Color(142, 112, 86));

            Triangle e = new Triangle(p5, p6, p4);
            e.setEmission(new Color(142, 112, 86));

            Triangle f = new Triangle(p6, p23, p24);
            f.setEmission(new Color(142, 112, 86));

            Triangle g = new Triangle(p24, p23, p28);
            g.setEmission(new Color(142, 123, 90));

            Triangle h = new Triangle(p26, p24, p28);
            h.setEmission(new Color(142, 123, 90));

            Triangle i = new Triangle(p4b, p26, p27);
            i.setEmission(new Color(142, 112, 86));

            Triangle j = new Triangle(p28, p23, p29);
            j.setEmission(new Color(142, 123, 100));

            Triangle k = new Triangle(p22, p21, p23);
            k.setEmission(new Color(142, 112, 86));

            Triangle l = new Triangle(p20, p34, p21);
            l.setEmission(new Color(142, 112, 86));

            Triangle m = new Triangle(p19, p22, p31);
            m.setEmission(new Color(142, 112, 86));

            Triangle n = new Triangle(p8, p18, p29);
            n.setEmission(new Color(46, 13, 0));

            Triangle o = new Triangle(p8, p22, p23);
            o.setEmission(new Color(142, 112, 86));

            Triangle p = new Triangle(p6, p8, p23);
            p.setEmission(new Color(142, 112, 86));

            Triangle q = new Triangle(p6, p7, p8);
            q.setEmission(new Color(142, 112, 86));

            Triangle r = new Triangle(p8, p17, p18);
            r.setEmission(new Color(46, 13, 0));

            Triangle s = new Triangle(p17, p13, p18);
            s.setEmission(new Color(46, 13, 0));

            Triangle t = new Triangle(p8, p9, p17);
            t.setEmission(new Color(46, 13, 0));

            Triangle u = new Triangle(p9, p32, p17);
            u.setEmission(new Color(46, 30, 0));

            Triangle v = new Triangle(p9, p10, p32);
            v.setEmission(new Color(46, 13, 0));

            Triangle w = new Triangle(p32, p11, p33);
            w.setEmission(new Color(116, 100, 93));

            Quadrangle x = new Quadrangle(p13, p17, p32, p33);
            x.setEmission(new Color(142, 112, 86));

            Triangle x_ = new Triangle(p12, p13, p17);
            x_.setEmission(new Color(142, 112, 86));

            Triangle y = new Triangle(p13, p14, p16);
            y.setEmission(new Color(116, 100, 93));

            Triangle z = new Triangle(p14, p15, p16);
            z.setEmission(new Color(116, 100, 93));

            Triangle aa = new Triangle(p16, p15, p17);
            aa.setEmission(new Color(116, 100, 93));


            //endregion

            //region ADD TO SCENE
            scene.addGeometry(a);
            scene.addGeometry(b);
            scene.addGeometry(c);
            scene.addGeometry(d);
            scene.addGeometry(e);
            scene.addGeometry(f);
            scene.addGeometry(g);
            scene.addGeometry(h);
            scene.addGeometry(i);
            scene.addGeometry(j);
            scene.addGeometry(k);
            scene.addGeometry(l);
            scene.addGeometry(m);
            scene.addGeometry(n);
            scene.addGeometry(o);
            scene.addGeometry(p);
            scene.addGeometry(q);
            scene.addGeometry(r);
            scene.addGeometry(s);
            scene.addGeometry(t);
            scene.addGeometry(u);
            scene.addGeometry(v);
            scene.addGeometry(w);
            scene.addGeometry(x);
            scene.addGeometry(y);
            scene.addGeometry(z);
            scene.addGeometry(aa);


            ImageWriter imageWriter = new ImageWriter(filename, size, size, 1, 1);
            Render render = new Render(imageWriter, scene);
            render.renderImage();
            render.writeToImage();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean Sphere(LightSource ls, String filename,int size) {
        try {
            ImageWriter imageWriter = new ImageWriter(filename, size, size, 1, 1);
            Scene scene = new Scene();
            //scene.setAmbientLight(new AmbientLight());
            //scene.setBackground(Color.BLACK);
            //scene.setCamera(new Camera());
            //LightSource sp = new PointLight(Color.red, new Point3D(30, 50, -20), 0.1, 0.05, 0);
            //scene.addLight(sp);

            if (ls instanceof PointLight) {
                ((PointLight) ls).setKl(0.015);
                ((PointLight) ls).setKq(0.00005);
                ((PointLight) ls).setPosition(new Point3D(30, 50, -20));
            }
            scene.addLight(ls);

            //Point3D PL = new Point3D(15, 22, -20);


            Sphere s = new Sphere(5, new Point3D(0, -4.2, -50));
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
            s.setEmission(new Color(53, 3, 28));
            s.setShininess(4);
            s.setKd(2);
            s.setKs(1);
            scene.addGeometry(s);

            s = new Sphere(2, new Point3D(2, 3, -45));
            s.setEmission(new Color(53, 3, 28));
            s.setShininess(4);
            s.setKd(2);
            s.setKs(1);
            scene.addGeometry(s);
            Quadrangle p = new Quadrangle(new Point3D(-100, -11, -1000), new Point3D(100, -11, -1000), new Point3D(100, -9, 1), new Point3D(-100, -9, 1));
            p.setKd(2);
            // p.setEmission(new Color(68, 6, 0));
            scene.addGeometry(p);


            Render render = new Render(imageWriter, scene);
            render.renderImage();

            render.writeToImage();
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public static boolean Recursive(LightSource ls, String filename,int size) {
        try {
            Scene scene = new Scene();
            scene.setScreenDistance(300);

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

            if (ls instanceof PointLight) {
                ((PointLight) ls).setKl(0.000015);
                ((PointLight) ls).setKq(0.0000005);
                ((PointLight) ls).setPosition(new Point3D(30, 50, -20));
            }
            scene.addLight(ls);


            ImageWriter imageWriter = new ImageWriter(filename, size, size, 500, 500);

            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();

        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
