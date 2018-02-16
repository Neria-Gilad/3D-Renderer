package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.*;
import geometries.Quadrangle;
import geometries.Triangle;
import org.junit.Test;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import java.awt.*;

/**
 * Created by Neria Tzidkani on 12/06/2017.
 */
public class AnimalTest {
    @Test
    public void name() throws Exception {
        Camera camera = new Camera();
        camera.set_p0(new Point3D(1.5, 11, 2500));
        Scene scene = new Scene();
        scene.setScreenDistance(130);
        scene.setCamera(camera);
        //scene.setAmbientLight(new AmbientLight(Color.white));
        //scene.addLight(new PointLight(Color.white, new Point3D(10, 15, 110), 0, 0.4, 0.0005));
        scene.addLight(new DirectionalLight(new Color(74, 74, 74), new Vector(-0.5, -0.5, -2.5)));
//        scene.addLight(new PointLight(new Color(255, 111, 111), new Point3D(8, 8, 120), 0, 0.1, 0.000005));
        // scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(8, 6, 80), 0, 0.000001, 0.005));
        // scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(2, 6, 120), 0, 0.000001, 0.005));

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

        //endregion


        double poZ = 400;

        double neZ = poZ - 400;
        Point3D pt1 = new Point3D(-15, -10, poZ);
        Point3D pt2 = new Point3D(15, 5, neZ);

        Triangle t1 = new Triangle(new Point3D(-25, 5, neZ), pt2, pt1);
        t1.setEmission(new Color(0, 145, 28));
        t1.setKd(2);


        Triangle t3 = new Triangle(pt1, pt2, new Point3D(22, 0, neZ));
        t3.setEmission(t1.getEmission());
        t3.setKd(2.4);

        Triangle t4 = new Triangle(pt1, new Point3D(22, 0, neZ),new Point3D(30, -10, poZ));
        t4.setEmission(t3.getEmission());
        t4.setKd(2.4);

        pt1.add(new Point3D(1, -0.5, 0));
        pt2.add(new Point3D(1, -0.5, 0));

        Quadrangle t2 = new Quadrangle(pt1, pt2, new Point3D(25, 5, poZ), new Point3D(25, -10, poZ));
        t2.setEmission(Color.blue);
        t2.setKd(2);
        t2.setKt(0.7);
        t2.setKr(0.3);

        scene.addGeometry(t1);
        scene.addGeometry(t2);
        scene.addGeometry(t3);
        scene.addGeometry(t4);

        scene.setBackground(new Color(25, 185, 255));
        ImageWriter imageWriter = new ImageWriter("Animal test", 3000, 3000, 2, 2);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }
}
