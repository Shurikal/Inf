package PruefH2018;

import java.util.ArrayList;

public class TestOrchester
{
    public static void main(String[] args)
    {
        ArrayList<Note> noten = new ArrayList<>();



        Instrument i1 = new Blasinstrument(noten);
        Instrument i2 = new Streichinstrument(noten);

        noten.add(new Note("C"));
        noten.add(new Note("E"));

        i1.spieleLied();
        i2.spieleLied();
    }
}
