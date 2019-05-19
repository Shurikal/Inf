package Multi_Pong_Local;




import javax.swing.*;
import java.awt.*;

public class Schlaeger2_0 extends JPanel
{

    private int width=20;

    private int acc = 5;

    private int heigth;

    private int yPos =50;
    private int xPos;
    private Ball b;

    private int li_re;

    private boolean bounce;

    public Schlaeger2_0(int heigth, int x, Ball b,int li_re)
    {
        this.heigth = heigth;
        setBackground(Color.green);
        this.b = b;
        setBounds(x, yPos, width, heigth);
        this.li_re =li_re;
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
        double angle = 0;
        boolean x2 = getX()+getWidth()>b.getX() && getX() < b.getX() + b.getWidth();
        boolean y2 = getY() + getHeight() > b.getY() && getY()< b.getY() + b.getHeight();
        if (y2 && x2 && !bounce){
            angle = Math.atan2(li_re*(this.getHeight()/2+b.getWidth()/2),(b.getY()+b.getHeight()/2)-(this.getY()+this.getHeight()/2));
            b.setAngle(angle);
        }else{
            bounce = false;
        }
    }

}
