package kap11MehrVererbung;

public class LKW extends Motorfahrzeug
{
    private double nutzlast;

    public LKW( String farbe, String marke, double leistung,double nutzlast)
    {
        super(farbe,marke,leistung);
        this.nutzlast = nutzlast;
    }

    public double preis()
    {
        return super.preis()*(nutzlast/10.0);
    }

    public String toString()
    {
        return super.toString()+ " Nutzlast " + nutzlast;
    }

}
