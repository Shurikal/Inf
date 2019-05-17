package pong;

import javax.swing.*;

public class Pong
{
    public static void main(String[] args)
    {
        JFrame fenster = new JFrame();
        fenster.add(new Spielfeld());

        fenster.pack();
        fenster.setVisible(true);

    }
}
