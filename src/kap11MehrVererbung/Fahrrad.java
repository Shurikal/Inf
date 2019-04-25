package kap11MehrVererbung;

public class Fahrrad extends Fahrzeug
{
    double factor = 1/10.0;

    public Fahrrad(String farbe, String marke)
    {
        super(farbe,marke);
    }

    public double preis()
    {
        return super.preis()*factor;
    }

    public String toString()
    {
        return super.toString();
    }
}
