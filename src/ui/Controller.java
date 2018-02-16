package ui;

/**
 * Created by Neria Tzidkani on 13/06/2017.
 */

import elements.DirectionalLight;
import elements.LightSource;
import elements.PointLight;
import elements.SpotLight;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import primitives.Point3D;
import primitives.Vector;

import java.io.IOException;


public class Controller {

    ///////////////////////////
    //  Fields               //
    ///////////////////////////
    String path = System.getProperty("user.dir") + "\\test_outpic\\..\\";

    private static RenderObject renderObject = new RenderObject();
    java.awt.Color color;
    //region FXML fields
    @FXML
    private Button exit_btn; //not implemented yet in fxml
    @FXML
    private Button open_btn;
    @FXML
    private Button animal_btn;
    @FXML
    private Button sphere_btn;
    @FXML
    private Button rec_btn;
    @FXML
    private RadioButton pl;
    @FXML
    private RadioButton dl;
    @FXML
    private RadioButton sl;

    @FXML
    private Slider x;
    @FXML
    private Slider y;
    @FXML
    private Slider z;

    @FXML
    private Slider factor;

    @FXML
    private TextField filename;

    @FXML
    private TextField res;

    @FXML
    private ImageView image;
//endregion

    ///////////////////////////
    //  Initialization       //
    ///////////////////////////

    @FXML
    private void initialize() {
        color = java.awt.Color.white;
    }

    ///////////////////////////
    //  Admin                //
    ///////////////////////////

    /**
     * converts javafx.scene.paint.Color object to java.awt.Color
     *
     * @param color
     * @return converted color
     */
    private java.awt.Color colorAdaptor(javafx.scene.paint.Color color) {
        return new java.awt.Color((int) (color.getRed() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255));
    }

    /**
     * event that occurred when picking up a color
     */
    @FXML
    private void setColor(ActionEvent event) {
        javafx.scene.paint.Color p = ((ColorPicker) event.getSource()).getValue();
        color = colorAdaptor(p);
    }

    /**
     * event on clicking exit button
     * NO OBJECT IMPLEMENT THIS METHOD
     */
    public void ext(ActionEvent event) {
        if (event.getSource() == exit_btn)
            exit_btn.getScene().getWindow().hide();
    }

    /**
     * opens rendered picture, as well as updating the image preview in  th GUI
     */
    public void open(ActionEvent e) {
        try {
            //String path = System.getProperty("user.dir") + "\\test_outpic\\";
            String jpg = ".jpg";
            //image.setImage(new Image("file:/" + path + filename.getText() + jpg));
            Runtime.getRuntime().exec("explorer.exe /open," + path + filename.getText() + jpg);
        } catch (IOException io) {
            System.out.println("Unable to open file explorer");
        }
    }

    /**
     * probably something went wrong, let's open a picture that shows it to the user
     */
    private void awSnap() {
        try {
            //String path = System.getProperty("user.dir") + "\\test_outpic\\";
            String jpg = "aw.jpg";

            image.setImage(new Image("file:/" + path + jpg));
            //Runtime.getRuntime().exec("explorer.exe /open," + path + jpg);
        } catch (Exception io) {
            System.out.println("Unable to operate");
        }
    }

    /**
     * update the image in the preview
     */
    private void updatePreview() {
       // String path = System.getProperty("user.dir") + "\\test_outpic\\";
        String jpg = ".jpg";
        image.setImage(new Image("file:/" + path + filename.getText() + jpg));
    }

    /**
     * generate light from user input
     *
     * @return
     */
    private LightSource getLight() {
        double kc = 0;//50 - factor.getValue() / 2;
        double percent = factor.getValue() / 100;
        java.awt.Color adColor = new java.awt.Color((int) (color.getRed() * percent), (int) (color.getGreen() * percent), (int) (color.getBlue() * percent));
        try {
            if (pl.isSelected()) return new PointLight(adColor, new Point3D(), kc, 0, 0);
            else if (sl.isSelected())
                return new SpotLight(adColor, new Point3D(), new Vector(x.getValue(), y.getValue(), z.getValue()), kc, 0, 0);
            else return new DirectionalLight(adColor, new Vector(x.getValue(), y.getValue(), z.getValue()));
        } catch (Exception e) {
            awSnap();
            return new PointLight(new java.awt.Color(0, 0, 0), new Point3D(), kc, 0, 0);
        }
    }

    /**
     * get size for rendered picture by user input
     *
     * @return image resolution
     */
    private int getSize() {
        try {
            return Integer.parseInt(res.getText());
        } catch (Exception ex) {
            return 500;
        }
    }


    /**
     * user probably hit the RenderAnimal button, we shall render a realistic RenderAnimal
     */
    public void RenderAnimal(ActionEvent e) {
        boolean success = renderObject.Animal(getLight(), filename.getText(), getSize());
        if (success) updatePreview();
        else awSnap();
    }

    /**
     * user probably hit the RenderAnimal button, we shall render an unrealistic spheres lighten by user
     * shadow wow
     */
    public void RenderSphere(ActionEvent e) {
        LightSource l =getLight();
        boolean success = renderObject.Sphere(getLight(), filename.getText(), getSize());
        if (success) updatePreview();
        else awSnap();

    }

    /**
     * user probably hit the RenderRecursive button, we shall render an unrealistic RenderRecursive spheres lighten by user
     * shadow wow
     */
    public void RenderRecursive(ActionEvent e) {
        boolean success = renderObject.Recursive(getLight(), filename.getText(), getSize());
        if (success) updatePreview();
        else awSnap();
    }

}
