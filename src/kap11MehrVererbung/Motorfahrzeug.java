package kap11MehrVererbung;

public class Motorfahrzeug extends Fahrzeug
{
    private double leistung;

    public Motorfahrzeug(String farbe, String marke, double leistung)
    {
        super(farbe,marke);
        this.leistung = leistung;
    }

    public double preis()
    {
        return super.preis()*(leistung/10);
    }

    public String toString()
    {
        return super.toString() +" Leistung " + leistung;
    }
}
