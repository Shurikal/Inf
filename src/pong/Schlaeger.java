package pong;


import javax.swing.*;
import java.awt.*;

public class Schlaeger extends JPanel
{

    private int width;

    public Schlaeger(int width)
    {
        this.width = width;
        setBackground(Color.green);
        setBounds(0, 450, width, 20);

    }

    public void setX(int x)
    {
        super.setBounds(x, 450, width, 20);
    }
}
