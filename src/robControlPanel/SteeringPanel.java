package robControlPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SteeringPanel extends JPanel implements KeyListener
{

    private boolean[] buttonMask;

    public SteeringPanel()
    {
        buttonMask = new boolean[4];
    }

    public void keyTyped(KeyEvent e)
    {

    }

    public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyChar())
        {
            case 'q':

                break;
            case 'e':

                break;
            case 'b':

                break;
            case 'a':

                break;
            case 'd':

                break;
            case 'f':

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
    }

    public void keyReleased(KeyEvent e)
    {
        switch(e.getKeyChar())
        {

            case 'w':
                buttonMask[1]=false;
                break;
            case 's':
                buttonMask[0]=false;
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
}
