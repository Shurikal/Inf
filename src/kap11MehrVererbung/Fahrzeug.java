package kap11MehrVererbung;

public class Fahrzeug
{
    private String farbe,marke;
    private double grundpreis =1;


    public Fahrzeug(String farbe, String marke)
    {
        this.farbe = farbe;
        this.marke = marke;
    }

    public double preis()
    {
        return grundpreis;
    }

    public String toString()
    {
        return (" Farbe "+ farbe + " Marke "+ marke + " Preisfaktor " + preis());
    }
}
