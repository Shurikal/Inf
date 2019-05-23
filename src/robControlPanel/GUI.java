package robControlPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class GUI extends JPanel
{
    private boolean[] buttonMask;


    private JTextArea log;

    private JButton rob1, rob2;

    private JTextField cmdField;

    public GUI()
    {
        buttonMask = new boolean[4];

        this.setLayout(new BorderLayout());

        this.add(topPanel(),BorderLayout.NORTH);

        this.add(centerPanel(),BorderLayout.CENTER);

        this.add(botPanel(), BorderLayout.SOUTH);

    }

    private JPanel topPanel()
    {
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1,2));

        rob1 = new JButton("Verbinde Rob1");
        rob2 = new JButton("Verbinde Rob2");

        rob1.addActionListener(e-> connectRob1());
        rob2.addActionListener(e-> connectRob2());

        top.add(rob1);
        top.add(rob2);

        return top;
    }

    private void connectRob1()
    {
        if(Connection_Handler.connected_Rob1())
        {

        }else{
            rob1.setText("Verbinde ...");
            try {
                Connection_Handler.connect_Rob1();
                if(Connection_Handler.connected_Rob1())
                {
                    addText("Verbindung Rob1 erfolgreich!");
                    rob1.setText("Trenne Rob1");
                }
            }catch (Exception e)
            {
                addText(e.toString());
                rob1.setText("Verbinde Rob1");
            }

        }
    }

    private void connectRob2()
    {
        if(Connection_Handler.connected_Rob2())
        {

        }else{
            rob2.setText("Verbinde ...");
            try {
                Connection_Handler.connect_Rob2();
                if(Connection_Handler.connected_Rob2())
                {
                    addText("Verbindung Rob2 erfolgreich!");
                    rob2.setText("Trenne Rob2");
                }
            }catch (Exception e)
            {
                addText(e.toString());
                rob2.setText("Verbinde Rob2");
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

    private void addText(String s)
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
        addText(cmdField.getText() + " -> Rob1");
        cmdField.setText("");
    }

    private void sendToRob2()
    {
        addText(cmdField.getText() + " -> Rob2");
        cmdField.setText("");
    }

}
