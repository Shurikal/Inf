package pong;

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

    public Ball(double speed)
    {
        rnd = new Random();
        setBackground(Color.YELLOW);
        this.speed = speed;
        minspeed = speed *0.4;
        restart();
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
        point = new Point(rnd.nextInt(500-50)+25,0);
        setBounds((int)point.getX(),(int)point.getY(), 20, 20);
        while(velx <minspeed && velx>-minspeed) {
            velx = rnd.nextDouble()*speed - speed/2;
        }
        while(vely <minspeed && vely >-minspeed) {
            vely = rnd.nextDouble()*speed/2;
        }
    }
}
