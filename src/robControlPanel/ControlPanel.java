package robControlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlPanel extends JPanel implements KeyListener
{
    private boolean[] buttonMask;

    public ControlPanel()
    {
        buttonMask = new boolean[4];
        this.setLayout(new BorderLayout());
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


        }

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



        }

    }
}

