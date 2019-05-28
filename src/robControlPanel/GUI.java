package robControlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class GUI extends JPanel
{
    private boolean[] buttonMask;


    private JTextArea log;

    private JButton connectRob1, connectRob2, disconnectRob1,disconnectRob2;

    private JTextField cmdField;

    private Connection_Handler robs;

    public GUI()
    {
        buttonMask = new boolean[4];

        this.setLayout(new BorderLayout());
        this.add(topPanel(),BorderLayout.NORTH);
        this.add(centerPanel(),BorderLayout.CENTER);
        this.add(botPanel(), BorderLayout.SOUTH);
        robs = new Connection_Handler(this);

    }

    private JPanel topPanel()
    {
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(2,2));

        connectRob1 = new JButton("Verbinde Rob1");
        connectRob2 = new JButton("Verbinde Rob2");

        disconnectRob1 = new JButton("Trenne Rob1");
        disconnectRob2 = new JButton("Trenne Rob2");

        connectRob1.addActionListener(e-> connectRob1());
        connectRob2.addActionListener(e-> connectRob2());

        top.add(connectRob1);
        top.add(connectRob2);

        disconnectRob1.addActionListener(e-> robs.disconnect_Rob1());

        top.add(disconnectRob1);
        top.add(disconnectRob2);

        return top;
    }

    private void connectRob1()
    {

        System.out.println("Verbinde...");
        if(robs.connected_Rob1())
        {

        }else{
            connectRob1.setText("Verbinde ...");


            robs.connect_Rob1();
            if(robs.connected_Rob1())
            {
                System.out.println("Verbindung Rob1 erfolgreich");
                addText("Verbindung Rob1 erfolgreich!");
                connectRob1.setText("Trenne Rob1");
            } else {
                connectRob1.setText("Verbinde Rob1");
            }


        }
    }

    private void connectRob2()
    {
        if(robs.connected_Rob2())
        {

        }else{
            connectRob2.setText("Verbinde ...");
            try {
                robs.connect_Rob2();
                if(robs.connected_Rob2())
                {
                    addText("Verbindung Rob2 erfolgreich!");
                    connectRob2.setText("Trenne Rob2");
                }
            }catch (Exception e)
            {
                addText(e.toString());
                connectRob2.setText("Verbinde Rob2");
            }
        }
    }

    private JPanel centerPanel()
    {
        JPanel center = new JPanel();

        center.setLayout(new BorderLayout());

        log = new JTextArea();
        log.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(log);

        center.add(scrollPane,BorderLayout.CENTER);

        return center;
    }

    private JPanel botPanel()
    {
        JPanel bot = new JPanel();
        bot.setLayout(new GridLayout(1,2));

        cmdField = new JTextField();
        cmdField.addActionListener(e->{
            if(cmdField.getText().length()!=0)
            sendToAll();
        });


        JPanel subBot = new JPanel();
        subBot.setLayout(new GridLayout(3, 1));
        JButton sendToAll = new JButton("Sende allen");
        sendToAll.addActionListener(e-> sendToAll());

        JButton sendToRob1 = new JButton("Sende zu Rob1");
        sendToRob1.addActionListener(e-> sendToRob1());

        JButton sendToRob2 = new JButton("Sende zu Rob2");
        sendToRob2.addActionListener(e-> sendToRob2());

        subBot.add(sendToAll);
        subBot.add(sendToRob1);
        subBot.add(sendToRob2);


        bot.add(cmdField);
        bot.add(subBot);
        return bot;
    }

    public void addText(String s)
    {
        log.append( LocalTime.now()+ " : " + s + "\r\n");
    }

    private void sendToAll()
    {
        addText(cmdField.getText() + " -> all");
        cmdField.setText("");
    }

    private void sendToRob1()
    {
        /*addText(cmdField.getText() + " -> Rob1");
        cmdField.setText("");*/
        System.out.println((byte)(0335 & 0xFF));
    }

    private void sendToRob2()
    {
        addText(cmdField.getText() + " -> Rob2");
        cmdField.setText("");
    }


    private void delRob1ActionListeners()
    {
        for(ActionListener al : connectRob1.getActionListeners())
        {
            connectRob1.removeActionListener(al);
        }
    }

    private void delRob2ActionListeners()
    {
        for(ActionListener al : connectRob2.getActionListeners())
        {
            connectRob2.removeActionListener(al);
        }
    }
}
