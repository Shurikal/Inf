package PruefH2018;

import java.util.ArrayList;

public class Instrument
{
    private ArrayList<Note> noten;

    protected Instrument(ArrayList<Note> n)
    {
        noten = n;
    }

    public void spieleLied()
    {
        for(Note n: noten)
        {
            spiele(n);
        }
    }

    protected void spiele(Note n)
    {

    }
}
