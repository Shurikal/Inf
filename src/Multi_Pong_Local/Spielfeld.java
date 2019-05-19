package Multi_Pong_Local;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Spielfeld extends JPanel implements MouseMotionListener, KeyListener
{
    private Ball ball;
    private Schlaeger2_0 schlaegerLi, schlaegerRe;
    private Timer timer;
    private int width = 500;
    private int height = 500;

    private int delay =20;

    private boolean aiON;
    private boolean debugON;

    private boolean[] buttonMask= new boolean[4];

    private JLabel speed,scoreLi,scoreRe;

    private int iscoreRe,iscoreLi;

    public Spielfeld()
    {

        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(120,55,205));
        addMouseMotionListener(this);

        addKeyListener(this);
        this.setFocusable(true);
        ball = new Ball(10,this);
        schlaegerLi = new Schlaeger2_0(200,50,ball,1);
        schlaegerRe = new Schlaeger2_0(200,450,ball,-1);

        schlaegerLi.setXY(50-schlaegerLi.getWidth(),50);

        setLayout(null);

        this.add(schlaegerLi);
        this.add(schlaegerRe);
        this.add(ball);


        timer = new Timer(delay,e -> {
            update();
        });
        timer.start();


        scoreLi = new JLabel(""+iscoreLi);
        scoreRe = new JLabel(""+iscoreRe);

        scoreRe.setForeground(Color.WHITE);
        scoreLi.setForeground(Color.WHITE);

        scoreRe.setFont(new Font("Arial",Font.BOLD,40));
        scoreLi.setFont(new Font("Arial",Font.BOLD,40));

        scoreRe.setBounds(400,20,100,100);
        scoreLi.setBounds(0,20,100,100);

        scoreRe.setHorizontalAlignment(SwingConstants.LEFT);

        scoreLi.setHorizontalAlignment(SwingConstants.RIGHT);
        speed = new JLabel("Speed: "+delay);
        speed.setBounds(width - 150, 40,100, 20);
        speed.setForeground(Color.WHITE);


        this.add(scoreLi);
        this.add(scoreRe);

        this.add(speed);
        speed.setVisible(false);

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
        if (ball.getLocation().getY() < 0 || ball.getLocation().getY() >= this.getHeight()-ball.getHeight()) {
            ball.wechsleYRichtung();
        }


        //Links
        if (ball.getLocation().getX() < 0) {
            iscoreRe+=1;
            scoreRe.setText(""+iscoreRe);
            ball.restart();
        }

        //Rechts
        if( ball.getLocation().getX() >= this.getWidth()-ball.getWidth())
        {
            iscoreLi +=1;
            scoreLi.setText(""+iscoreLi);
            ball.restart();
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
            ball.restart();

        }
    }
}
