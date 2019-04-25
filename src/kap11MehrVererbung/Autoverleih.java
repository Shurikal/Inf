package kap11MehrVererbung;

import java.util.ArrayList;

public class Autoverleih
{
    private ArrayList<Fahrzeug> liste;

    public Autoverleih()
    {
        liste = new ArrayList<>();
    }


    public void add(Fahrzeug f)
    {
        liste.add(f);
    }

    public void auflisten()
    {
        for(Fahrzeug f : liste)
        {
            System.out.println(f.toString());
        }
    }

    public static void main(String[] args)
    {
        Autoverleih a = new Autoverleih();
        a.test();
        a.auflisten();
    }

    public void test()
    {
        liste.add(new Fahrrad("rot","S"));
        liste.add(new LKW("blau","Volvo",343,12));
        liste.add(new PKW("blau","BMW",232,12));
    }
}
