package pong;


import javax.swing.*;
import java.awt.*;

public class Schlaeger extends JPanel
{

    private int height=20;

    private int old_x;

    private int width;

    public Schlaeger(int width)
    {
        this.width = width;
        setBackground(Color.green);
        setBounds(0, 450, width, height);

    }

    public void setXY(int x,int y)
    {
        int deltax = old_x -x;
        old_x = this.getX() -deltax;
        if(deltax > 10)
        {
            deltax = 10;
        }
        if(deltax < -10)
        {
            deltax = -10;
        }
        this.setBounds(this.getX()-deltax, y, width, height);
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

}
