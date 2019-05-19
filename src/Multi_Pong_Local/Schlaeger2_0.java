package Multi_Pong_Local;




import javax.swing.*;
import java.awt.*;

public class Schlaeger2_0 extends JPanel
{

    private int width=20;

    private int acc = 30;

    private int heigth;

    private int yPos =50;
    private int xPos;
    private Ball b;

    private boolean bounce;

    public Schlaeger2_0(int heigth, int x, Ball b)
    {
        this.heigth = heigth;
        setBackground(Color.green);
        this.b = b;
        setBounds(x, yPos, width, heigth);

    }

    public void setXY(int x,int y)
    {
        this.setBounds(x, y, width, heigth);
    }

    public void setHeigth(int heigth)
    {
        this.heigth = heigth;
    }


    public void addy()
    {
        yPos += acc;
        this.setXY(this.getX(),yPos);
    }

    public void suby()
    {
        yPos -= acc;
        this.setXY(this.getX(),yPos);
    }

    public void collisionUpdate()
    {
        boolean x2 = getX()+getWidth()>b.getX() && getX() < b.getX() + b.getWidth();
        boolean y2 = getY() + getHeight() > b.getY() && getY()< b.getY() + b.getHeight();
        if (y2 && x2 && !bounce){
            b.wechsleXRichtung();
            bounce = true;
        }else{
            bounce = false;
        }
    }

}
