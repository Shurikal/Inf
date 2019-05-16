package pong;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ball extends JPanel
{
    private int velx, vely;
    private Random rnd;

    public Ball()
    {
        setBackground(Color.black);
        setBounds(0, 0, 20, 20);
        velx = rnd.nextInt(20)-10;
        vely = rnd.nextInt(10);
    }

    public void wechseXRichtung()
    {
        velx = -1*velx;
    }

    public void wechsleYRichtung()
    {
        vely = -1* vely;
    }

    public Point gibNeuePosition()
    {
        return new Point(0, 0);
    }
}
