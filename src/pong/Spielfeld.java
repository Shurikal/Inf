package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Spielfeld extends JPanel implements MouseMotionListener
{
    private Ball ball;
    private Schlaeger schlaeger;

    public Spielfeld()
    {
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.yellow);
        ball = new Ball();
        schlaeger = new Schlaeger(50);
        setLayout(null);


        addMouseMotionListener(this);
    }

    public void mouseDragged(MouseEvent e)
    {

    }

    public void mouseMoved(MouseEvent e)
    {

    }
}
