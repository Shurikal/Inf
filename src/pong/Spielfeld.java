package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Spielfeld extends JPanel implements MouseMotionListener
{
    private Ball ball;
    private Schlaeger schlaeger;
    private Timer timer;
    private int width = 500;
    private int height = 500;

    private int currentscore;

    private JLabel score;

    public Spielfeld()
    {


        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(120,55,205));
        ball = new Ball(10);
        schlaeger = new Schlaeger(200);
        setLayout(null);

        this.add(schlaeger);
        this.add(ball);

        addMouseMotionListener(this);

        timer = new Timer(20,e -> {
            update();
        });
        timer.start();

        score = new JLabel("Score: 0");
        score.setBounds(width / 2 -50,20,100, 20);
        this.add(score);
    }

    public void mouseDragged(MouseEvent e)
    {

    }

    public void mouseMoved(MouseEvent e)
    {
        schlaeger.setX(e.getX()-schlaeger.getWidth()/2);
    }

    public void update()
    {
        //Wand Detection
        if (ball.getLocation().getX() < 0 || ball.getLocation().getX() >= width-ball.getWidth()) {
            ball.wechseXRichtung();
        }

        if (ball.getLocation().getY() < 0) {
            ball.wechsleYRichtung();
        }
        if(ball.getLocation().getY() >= height-ball.getHeight()){
            ball.restart();
            currentscore =0;
            score.setText("Score : 0");
        }

        //Schlaeger Detection
        /**if (ball.getLocation().getX() > schlaeger.getLocation().getX() && ball.getLocation().getX() < schlaeger.getLocation().getX()+schlaeger.getWidth()){
            ball.wechseXRichtung();
        }*/



        boolean y1 = ball.getLocation().getY()+ball.getHeight() > schlaeger.getLocation().getY() && ball.getLocation().getY()+ball.getHeight() < schlaeger.getLocation().getY()+schlaeger.getHeight() ;
        boolean y2 = ball.getLocation().getY()<schlaeger.getLocation().getY()+schlaeger.getHeight();
        boolean x1 =ball.getLocation().getX()+ball.getWidth() > schlaeger.getLocation().getX() && ball.getLocation().getX() < schlaeger.getLocation().getX()+schlaeger.getWidth();
        if (y1 && x1 && y2){
            ball.wechslepYRichtung();
            currentscore +=1;
            score.setText("Score : " + currentscore);
        }




        ball.setLocation(ball.gibNeuePosition());
    }
}
