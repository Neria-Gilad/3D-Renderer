package primitives;

/**
 * Created by Gilad Weiss on 3/20/2017.
 */
public class Material {
    private double _Kd; //diffusion attenuation coefficient
    private double _Ks; //specular attenuation coefficient
    private double _Kr; //reflection coefficient (1 for mirror)
    private double _Kt; //refraction coefficient (1 for transparent)
    private double _n; // reflection index

    ///////////////////////////
    //  Constructors         //
    ///////////////////////////

    /**
     * Default constructor for material
     */
    public Material() {
        this._Kd = 1;
        this._Ks = 1;
        this._Kr = 0;
        this._Kt = 0;
        this._n = 1;
    }

    /**
     * Copy constructor for material
     *
     * @param that
     */
    public Material(Material that) {
        this._Kd = that._Kd;
        this._Ks = that._Ks;
        this._Kr = that._Kr;
        this._Kt = that._Kt;
        this._n = that._n;
    }

    public Material(double _Kd, double _Ks, double _Kr, double _Kt, double _n) {
        this._Kd = _Kd;
        this._Ks = _Ks;
        this._Kr = _Kr;
        this._Kt = _Kt;
        this._n = _n;
    }

    ///////////////////////////
    //  Set & Get  Functions //
    ///////////////////////////

    /**
     * setter for Kd
     *
     * @param _Kd
     */
    public void setKd(double _Kd) {
        this._Kd = _Kd;
    }

    /**
     * setter for Ks
     *
     * @param _Ks
     */
    public void setKs(double _Ks) {
        this._Ks = _Ks;
    }

    /**
     * setter for Kr
     *
     * @param _Kr
     */
    public void setKr(double _Kr) {
        this._Kr = _Kr;
    }

    /**
     * setter for Kt
     *
     * @param _Kt
     */
    public void setKt(double _Kt) {
        this._Kt = _Kt;
    }

    /**
     * setter for N
     *
     * @param _n
     */
    public void setN(double _n) {
        this._n = _n;
    }

    /**
     * getter for Kd
     *
     * @return Kd
     */
    public double getKd() {
        return _Kd;
    }

    /**
     * getter for Ks
     *
     * @return Ks
     */
    public double getKs() {
        return _Ks;
    }

    /**
     * getter for Kr
     *
     * @return Kr
     */
    public double getKr() {
        return _Kr;
    }

    /**
     * getter for Kt
     *
     * @return Kt
     */
    public double getKt() {
        return _Kt;
    }

    /**
     * getter for N
     *
     * @return N
     */
    public double getN() {
        return _n;
    }
}


