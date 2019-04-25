package kap11MehrVererbung;

public class PKW extends Motorfahrzeug
{
    private int sitze;

    public PKW( String farbe, String marke, double leistung, int sitze)
    {
        super(farbe,marke,leistung);
        this.sitze = sitze;
    }

    public double preis()
    {
        return super.preis()*(sitze);
    }

    public String toString()
    {
        return super.toString()+ " Sitze " + sitze;
    }

}
