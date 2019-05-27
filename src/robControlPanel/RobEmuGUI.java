package robControlPanel;

import javax.swing.*;
import java.awt.*;

public class RobEmuGUI
{
    private JFrame fenster;

    private JButton start,stop;
    private RobEmulator emu;

    public static void main(String[] args){
        new RobEmuGUI();
    }

    public RobEmuGUI(){
        fenster = new JFrame("GUI Emulator");

        Container contentPane = fenster.getContentPane();

        contentPane.setLayout(new BorderLayout());

        contentPane.add(top(), BorderLayout.NORTH);


        fenster.pack();
        fenster.setVisible(true);
    }

    private JPanel top(){
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1, 2));

        start = new JButton("Start Server");
        stop = new JButton("Stop Server");

        start.addActionListener(e -> startServer());
        stop.addActionListener(e -> stopServer());



        top.add(start);
        top.add(stop);


        return top;
    }


    private void startServer(){
        emu = new RobEmulator();
    }

    private void stopServer(){
        emu.stop();
    }
}

