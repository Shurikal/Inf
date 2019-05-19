package Multi_Pong_Local;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ball extends JPanel
{
    private double velx, vely;
    private Point point;
    private Random rnd;
    private double speed;
    private double minspeed;
    private JPanel spielfeld;


    public Ball(double speed)
    {
        rnd = new Random();
        setBackground(Color.YELLOW);
        this.speed = speed;
        minspeed = speed *0.4;
        restart();
    }

    public Ball(double speed, JPanel spielfeld)
    {
        this(speed);
        this.spielfeld = spielfeld;
    }

    /**
     *
     */
    public void wechsleXRichtung()
    {
        velx = -1*velx;
    }

    public void wechsleYRichtung()
    {
        vely = -1* vely;
    }

    public void wechslepYRichtung()
    {
        vely=-Math.abs(vely);
    }

    /**
     *
     * @return blablabla
     */
    public Point gibNeuePosition()
    {
        point.setLocation(point.getX()+velx,point.getY()+vely);
        return point;
    }

    public void restart()
    {
        if(spielfeld ==null)
        {
            point = new Point(500 / 2, 0);

        }else{
            point = new Point(spielfeld.getWidth() / 2, 0);

        }
        setBounds((int)point.getX(),(int)point.getY(), 20, 20);

        double angle = 0;
        while(angle > -Math.PI /8 || angle < -Math.PI*(7.0/8) || ((angle > -Math.PI *(5.0/8)&& angle < -Math.PI *(3.0/8)))) {
            angle = -rnd.nextDouble() * Math.PI;

        }


        velx = speed * Math.cos(angle);
        vely = -speed * Math.sin(angle);

    }

    public void setAngle(double angle)
    {
        velx = speed * Math.sin(angle);
        vely = speed * Math.cos(angle);
    }



}
