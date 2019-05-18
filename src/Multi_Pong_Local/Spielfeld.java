package Multi_Pong_Local;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

public class Spielfeld extends JPanel implements MouseMotionListener, KeyListener
{
    private Ball ball;
    private Schlaeger schlaegerLi, schlaegerRe;
    private Timer timer;
    private int width = 500;
    private int height = 500;

    private int delay =20;

    private int currentscore;

    private boolean aiON;
    private boolean debugON;

    private boolean[] buttonMask= new boolean[4];

    private JLabel score;
    private JLabel speed;

    public Spielfeld()
    {

        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(120,55,205));
        ball = new Ball(50);
        schlaegerLi = new Schlaeger(200,50,ball);
        schlaegerRe = new Schlaeger(200,450,ball);

        schlaegerLi.setXY(50-schlaegerLi.getWidth(),50);

        setLayout(null);

        this.add(schlaegerLi);
        this.add(schlaegerRe);
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

                break;
            case 'd':

                break;
            case 'f':
                debugON =!debugON;
                break;
            case 'w':
                buttonMask[0]=true;
                break;
            case 's':
                buttonMask[1]=true;
                break;
            default:
                switch(e.getKeyCode())
                {
                    case 38:
                        buttonMask[2]=true;
                        break;
                    case 40:
                        buttonMask[3]=true;
                        break;

                }

        }
        speed.setText("Speed: " + delay);
        timer.setDelay(delay);
        timer.start();
    }

    public void keyReleased(KeyEvent e)
    {
        switch(e.getKeyChar())
        {

            case 'w':
                buttonMask[0]=false;
                break;
            case 's':
                buttonMask[1]=false;
                break;
            default:
                switch(e.getKeyCode())
                {
                    case 38:
                        buttonMask[2]=false;
                        break;
                    case 40:
                        buttonMask[3]=false;
                        break;

                }

        }
    }

    public void mouseDragged(MouseEvent e)
    {

    }

    public void mouseMoved(MouseEvent e)
    {
        if(!aiON) {
            //schlaeger.setXY(e.getX() - schlaeger.getWidth() / 2, this.getHeight() - 50);
        }
      //  schlaeger.setXY(ball.getX()  schlaeger.getWidth() / 2, this.getHeight() - 50);
    }

    public void update()
    {
        this.requestFocus();

        //Wand Detection
        if (ball.getLocation().getX() < 0 || ball.getLocation().getX() >= this.getWidth()-ball.getWidth()) {
            ball.wechsleXRichtung();
        }

        if (ball.getLocation().getY() < 0 || ball.getLocation().getY() >= this.getHeight()-ball.getHeight()) {
            ball.wechsleYRichtung();
        }


        speed.setVisible(debugON);
        if(this.getHeight() != height || this.getWidth() != width)
        {

        }

        if(buttonMask[0]) {
            //W
            schlaegerLi.suby();
        }

        if (buttonMask[1]) {
            //S
            schlaegerLi.addy();
        }

        if (buttonMask[2]) {
            //UP
            schlaegerRe.suby();
        }

        if (buttonMask[3]) {
            //Down
            schlaegerRe.addy();
        }
        ball.setLocation(ball.gibNeuePosition());

        schlaegerRe.collisionUpdate();
        schlaegerLi.collisionUpdate();


        if(this.getHeight() != height || this.getWidth() != width)
        {
            width = this.getWidth();
            height = this.getHeight();
            schlaegerRe.setXY(this.width-50,schlaegerRe.getY());

        }
    }


}