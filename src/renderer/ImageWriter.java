package renderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class ImageWriter {
    ///////////////////////////
    //  Fields               //
    ///////////////////////////
    private int _imageWidth;     //pixels in x axis
    private int _imageHeight;   //pixels in y axis
    private int _Ny, _Nx;      //sizes of x and y axis
    final String PROJECT_PATH = System.getProperty("user.dir");// /* + "\\test_outpic\\";*/ //path to save image to
    private BufferedImage _image;  //image to reside in memory, ready to be saved to file
    private String _imageName;    //name of the image file

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * parametric constructor
     *
     * @param imageName name of image file
     * @param width     width in pixels
     * @param height    height in pixels
     * @param Ny        size of y axis
     * @param Nx        size of x axis
     */
    public ImageWriter(String imageName, int width, int height, int Ny, int Nx) {
        _Nx = Nx;
        _Ny = Ny;
        _imageWidth = width;
        _imageHeight = height;
        _imageName = imageName;
        _image = new BufferedImage(_imageWidth, _imageHeight, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * copy constructor
     *
     * @param imageWriter imageWriter to copy
     */
    public ImageWriter(ImageWriter imageWriter) {
        _Nx = imageWriter._Nx;
        _Ny = imageWriter._Ny;
        _imageWidth = imageWriter.getWidth();
        _imageHeight = imageWriter.getHeight();
        _imageName = imageWriter._imageName;
        _image = new BufferedImage(_imageWidth, _imageHeight, BufferedImage.TYPE_INT_RGB);
    }

    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * get function for number of x pixels
     *
     * @return pixels in x axis
     */
    public int getWidth() {
        return _imageWidth;
    }

    /**
     * get function for number of y pixels
     *
     * @return pixels in y axis
     */
    public int getHeight() {
        return _imageHeight;
    }

    /**
     * get function for size of y axis
     *
     * @return size of y axis
     */
    public int getNy() {
        return _Ny;
    }

    /**
     * get function for size of x axis
     *
     * @return size of x axis
     */
    public int getNx() {
        return _Nx;
    }

    /**
     * set function for size of y axis
     *
     * @param _Ny size of y axis
     */
    public void setNy(int _Ny) {
        this._Ny = _Ny;
    }

    /**
     * set function for size of y axis
     *
     * @param _Nx size of y axis
     */
    public void setNx(int _Nx) {
        this._Nx = _Nx;
    }

    ///////////////////////////
    //  I/O Functions        //
    ///////////////////////////

    /**
     * turn buffered image into file
     */
    public void writeToimage() {

        File ouFile = new File(PROJECT_PATH + "/" + _imageName + ".jpg");

        try {
            ImageIO.write(_image, "jpg", ouFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * color a pixel in the buffered image
     *
     * @param xIndex index of x pixel
     * @param yIndex index of y pixel
     * @param r      value of red
     * @param g      value of green
     * @param b      value of blue
     */
    public void writePixel(int xIndex, int yIndex, int r, int g, int b) {

        int rgb = new Color(r, g, b).getRGB();
        _image.setRGB(xIndex, yIndex, rgb);

    }

    /**
     * color a pixel in the buffered image
     *
     * @param xIndex   index of x pixel
     * @param yIndex   index of y pixel
     * @param rgbArray array of colors [R,G,B]
     */
    public void writePixel(int xIndex, int yIndex, int[] rgbArray) {

        int rgb = new Color(rgbArray[0], rgbArray[1], rgbArray[2]).getRGB();
        _image.setRGB(xIndex, yIndex, rgb);

    }

    /**
     * color a pixel in the buffered image
     *
     * @param xIndex index of x pixel
     * @param yIndex index of y pixel
     * @param color  color to set
     */
    public void writePixel(int xIndex, int yIndex, Color color) {

        _image.setRGB(xIndex, yIndex, color.getRGB());

    }


    ///////////////////////////
    //  Overriding Functions //
    ///////////////////////////

    /**
     * checks if all fields are equal
     * @param o to compare with
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageWriter)) return false;

        ImageWriter that = (ImageWriter) o;

        if (_imageWidth != that._imageWidth) return false;
        if (_imageHeight != that._imageHeight) return false;
        if (_Ny != that._Ny) return false;
        if (_Nx != that._Nx) return false;
        if (PROJECT_PATH != null ? !PROJECT_PATH.equals(that.PROJECT_PATH) : that.PROJECT_PATH != null) return false;
        if (_image != null ? !_image.equals(that._image) : that._image != null) return false;
        return _imageName != null ? _imageName.equals(that._imageName) : that._imageName == null;
    }


}
