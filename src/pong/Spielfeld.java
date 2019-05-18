package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Spielfeld extends JPanel implements MouseMotionListener, KeyListener
{
    private Ball ball;
    private Schlaeger schlaeger;
    private Timer timer;
    private int width = 500;
    private int height = 500;

    private int delay =20;

    private int currentscore;

    private boolean aiON;
    private boolean debugON;

    private JLabel score;
    private JLabel speed;

    public Spielfeld()
    {

        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(120,55,205));
        ball = new Ball(50);
        schlaeger = new Schlaeger(200);
        setLayout(null);

        this.add(schlaeger);
        this.add(ball);

        addMouseMotionListener(this);

        addKeyListener(this);
        this.setFocusable(true);
        timer = new Timer(delay,e -> {
            update();
        });
        timer.start();

        speed = new JLabel("Speed: "+delay);
        speed.setBounds(width - 150, 40,100, 20);
        speed.setForeground(Color.WHITE);
        score = new JLabel("Score: 0");
        score.setBounds(width  -150,20,100, 20);
        this.add(score);
        this.add(speed);
        speed.setVisible(false);
        score.setForeground(Color.WHITE);
    }

    public void keyTyped(KeyEvent e)
    {

    }

    public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyChar())
        {
            case 'q':
                delay += 1;
                break;
            case 'e':
                if(delay >0){
                    delay -= 1;}
                break;
            case 'b':
                aiON =!aiON;
                break;
            case 'a':
                schlaeger.setWidth(schlaeger.getWidth()+5);
                break;
            case 'd':
                schlaeger.setWidth(schlaeger.getWidth()-5);
                break;
            case 'f':
                debugON =!debugON;
        }
        speed.setText("Speed: " + delay);
        timer.setDelay(delay);
        timer.start();
    }

    public void keyReleased(KeyEvent e)
    {

    }

    public void mouseDragged(MouseEvent e)
    {

    }

    public void mouseMoved(MouseEvent e)
    {
        if(!aiON) {
            schlaeger.setXY(e.getX() - schlaeger.getWidth() / 2, this.getHeight() - 50);
        }
      //  schlaeger.setXY(ball.getX()  schlaeger.getWidth() / 2, this.getHeight() - 50);
    }

    public void update()
    {
        this.requestFocus();
        if(aiON) {
            schlaeger.setXY(ball.getX() - schlaeger.getWidth() / 2, this.getHeight() - 50);
        }
        //Wand Detection
        if (ball.getLocation().getX() < 0 || ball.getLocation().getX() >= this.getWidth()-ball.getWidth()) {
            ball.wechsleXRichtung();
        }

        if (ball.getLocation().getY() < 0) {
            ball.wechsleYRichtung();
        }

        if(ball.getLocation().getY() >= this.getHeight()-ball.getHeight()){
            ball.restart();
            currentscore =0;
            score.setText("Score : 0");
        }

        //Schlaeger Erkennung
        boolean test = ball.getLocation().getY()+ball.getHeight()>schlaeger.getLocation().getY() && ball.getLocation().getY() < schlaeger.getLocation().getY() + schlaeger.getY();
        boolean y1 = ball.getLocation().getY()+ball.getHeight() > schlaeger.getLocation().getY() && ball.getLocation().getY() < schlaeger.getLocation().getY()+schlaeger.getHeight() ;
        boolean y2 = true;//ball.getLocation().getY()<schlaeger.getLocation().getY()+schlaeger.getHeight();
        boolean x1 =ball.getLocation().getX()+ball.getWidth() > schlaeger.getLocation().getX() && ball.getLocation().getX() < schlaeger.getLocation().getX()+schlaeger.getWidth();
        if (y1 && x1 && y2){
            ball.wechslepYRichtung();
            currentscore +=1;
            score.setText("Score : " + currentscore);
        }

        speed.setVisible(debugON);
        if(this.getHeight() != height || this.getWidth() != width)
        {
            width = this.getWidth();
            height = this.getHeight();
            speed.setBounds(width - 150, 40,100, 20);
            score.setBounds(width -150,20,100, 20);
            ball.restart();
            currentscore =0;
            score.setText("Score : 0");
        }


        ball.setLocation(ball.gibNeuePosition());
    }
}
