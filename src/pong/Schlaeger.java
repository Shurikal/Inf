package pong;


import javax.swing.*;
import java.awt.*;

public class Schlaeger extends JPanel
{

    private int height=20;

    private int width;

    public Schlaeger(int width)
    {
        this.width = width;
        setBackground(Color.green);
        setBounds(0, 450, width, height);

    }

    public void setXY(int x,int y)
    {
        this.setBounds(x, y, width, height);
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

}
