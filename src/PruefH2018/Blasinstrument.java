package PruefH2018;

import java.util.ArrayList;

public class Blasinstrument extends Instrument
{

    public Blasinstrument(ArrayList<Note> n)
    {
        super(n);
    }

    public void spiele(Note n)
    {
        System.out.println("Bläser " +n.toString());
    }
}
