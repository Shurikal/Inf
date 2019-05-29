package robControlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlPanel extends JFrame implements KeyListener, Runnable {
    private boolean[] buttonMask;

    private boolean vorwaerts,rueckwaerts,uz,guz,spanne,loese,hole;

    int VORWAERTS				= 4200;
    int RUECKWAERTS				= 4201;
    int DREHE_UZ				= 4202;
    int DREHE_GUZ				= 4203;

    int KURVEV_UZ				= 4204;
    int KURVEV_GUZ				= 4205;

    int KURVER_UZ				= 4206;
    int KURVER_GUZ				= 4207;

    private Timer timer;

    private Rob_Connection rob;

    public ControlPanel(Rob_Connection rob) {

        buttonMask = new boolean[6];
        setPreferredSize(new Dimension(200, 200));


        addKeyListener(this);
        this.setFocusable(true);

        setLayout(null);

        /*timer = new Timer(50, e -> {
            update();
        });
        timer.start();*/

        this.pack();
        this.setVisible(true);

        new Thread(this).start();
    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        System.out.println(e);
        switch (e.getKeyChar()) {
            case 'q':
                buttonMask[4] = true;
                break;
            case 'e':
                buttonMask[5] = true;
                break;
            case 'r':
                hole = true;
                break;
            case 'f':
                loese = true;
            case 'a':
                guz = true;
                break;
            case 'd':
                uz = true;
                break;

            case 'w':
                vorwaerts = true;
                break;
            case 's':
                rueckwaerts = true;
                break;
            default:


        }

    }


    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {

            case 'w':
                vorwaerts = false;
                break;
            case 's':

                rueckwaerts = false;
                break;

            case 'q':
                buttonMask[4] = false;
                break;
            case 'e':
                buttonMask[5] = false;
                break;
            case 'a':
                guz = false;
                break;
            case 'd':
                uz = false;
                break;
            default:


        }

    }

    public void run() {
        while (true) {
            this.requestFocus();

            if(!this.isVisible()){
                this.dispose();
                break;
            }

            if(vorwaerts && !(rueckwaerts || guz || uz)){
                Connection_Handler.sendDataRob1(VORWAERTS);
            }else if(rueckwaerts && !(vorwaerts || guz || uz)){
                Connection_Handler.sendDataRob1(RUECKWAERTS);
            }else if(uz && !(vorwaerts || rueckwaerts || guz)){
                Connection_Handler.sendDataRob1(DREHE_UZ);
            }else if(guz && !(vorwaerts || rueckwaerts || uz)){
                Connection_Handler.sendDataRob1(DREHE_GUZ);
            }else if(vorwaerts && uz && !(rueckwaerts || guz)){
                Connection_Handler.sendDataRob1(KURVEV_UZ);
            }else if(vorwaerts && guz && !(rueckwaerts || uz)){
                Connection_Handler.sendDataRob1(KURVEV_GUZ);
            }else if(rueckwaerts && guz && !(vorwaerts || uz)){
                Connection_Handler.sendDataRob1(KURVER_GUZ);
            }else if(rueckwaerts && uz && !(vorwaerts || guz)){
                Connection_Handler.sendDataRob1(KURVER_UZ);
            }else {
                Connection_Handler.sendDataRob1(0);
            }


            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }
    }
}

