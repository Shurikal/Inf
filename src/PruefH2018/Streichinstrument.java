package PruefH2018;

import java.util.ArrayList;

public class Streichinstrument extends Instrument
{
    public Streichinstrument(ArrayList<Note> n)
    {
        super(n);
    }

    public void spiele(Note n)
    {
        System.out.println("Streicher " +n.toString());
    }
}
