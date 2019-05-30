package robControlPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GUI
{
    private boolean[] buttonMask;

    private JTextArea log;

    private JButton connectRob1, connectRob2, disconnectRob1,disconnectRob2, controlPanel;

    private JTextField cmdField;

    private JPanel gui;

    private JFrame fenster;

    private Connection_Handler robs;

    public GUI() {
        fenster = new JFrame("Java Proxy");

        Container contentpane = fenster.getContentPane();

        contentpane.setLayout(new BorderLayout());

        gui = new JPanel();
        buttonMask = new boolean[4];

        gui.setLayout(new BorderLayout());
        gui.add(topPanel(),BorderLayout.NORTH);
        gui.add(centerPanel(),BorderLayout.CENTER);
        gui.add(botPanel(), BorderLayout.SOUTH);

        menuezeileErzeugen(fenster);

        fenster.add(gui);
        fenster.pack();
        fenster.setVisible(true);
        robs = new Connection_Handler(this);
        new Thread(robs).start();
    }

    private JPanel topPanel() {
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(2,3));

        connectRob1 = new JButton("Verbinde Rob1");
        connectRob2 = new JButton("Verbinde Rob2");

        controlPanel = new JButton("Öffne ControlPanel");
        connectRob1.addActionListener(e-> connectRob1());
        connectRob2.addActionListener(e-> connectRob2());

        controlPanel.addActionListener(e-> openControlPanel1());

        disconnectRob1 = new JButton("Trenne Rob1");
        disconnectRob2 = new JButton("Trenne Rob2");

        top.add(connectRob1);
        top.add(connectRob2);
        top.add(controlPanel);

        disconnectRob1.addActionListener(e-> robs.disconnect_Rob1());
        disconnectRob2.addActionListener(e-> robs.disconnect_Rob2());

        top.add(disconnectRob1);
        top.add(disconnectRob2);

        return top;
    }

    private void connectRob1() {
        robs.connect_Rob1();
        if(!robs.connected_Rob1()){
            addText("Could not create socket");
        }
    }

    private void connectRob2() {
        robs.connect_Rob2();
        if(!robs.connected_Rob2()){
            addText("Could not create socket");
        }
    }

    private JPanel centerPanel() {
        JPanel center = new JPanel();

        center.setLayout(new BorderLayout());

        log = new JTextArea();
        log.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(log);

        center.add(scrollPane,BorderLayout.CENTER);

        return center;
    }

    private JPanel botPanel() {
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

    public void addText(String s) {
        log.append( LocalTime.now().format(DateTimeFormatter.ofPattern("H:m:s.S"))+ " : " + s + "\r\n");
    }

    private void sendToAll() {
        if(robs.connected_Rob1() && robs.connected_Rob2()){
            try {
                int i = Integer.parseInt(cmdField.getText());
                Connection_Handler.sendDataRob1(i);
                Connection_Handler.sendDataRob2(i);
                addText(i + " -> all");
                cmdField.setText("");
            }catch (NumberFormatException e){}
        }
    }

    private void sendToRob1() {
        if(robs.connected_Rob1()){
            try {
                Connection_Handler.sendDataRob1(Integer.parseInt(cmdField.getText()));
                addText(cmdField.getText() + " -> Rob1");
                cmdField.setText("");
            }catch (NumberFormatException e){}
        }
    }

    private void sendToRob2() {
        if(robs.connected_Rob2()){
            try{
                Connection_Handler.sendDataRob2(Integer.parseInt(cmdField.getText()));
                addText(cmdField.getText() + " -> Rob2");
                cmdField.setText("");
            }catch (NumberFormatException e){}
        }

    }

    private void openControlPanel1(){
        new ControlPanel();
    }

    private void menuezeileErzeugen(JFrame fenster)
    {
        JMenuBar menuezeile = new JMenuBar();
        fenster.setJMenuBar(menuezeile);

        JMenu menue;
        JMenuItem eintrag;

        menue = new JMenu("Datei");
        menuezeile.add(menue);

        eintrag = new JMenuItem("Einstellungen");
        eintrag.addActionListener(e -> { einstellungen(); });
        menue.add(eintrag);

        eintrag = new JMenuItem("Beenden");
        eintrag.addActionListener(e -> { beenden(); });
        menue.add(eintrag);

        menue = new JMenu("Hilfe");
        menuezeile.add(menue);

        eintrag = new JMenuItem("Info...");
        eintrag.addActionListener(e -> { zeigeInfo(); });
        menue.add(eintrag);

    }

    private void einstellungen(){
        new Einstellungen(robs, fenster);
    }

    private void beenden(){
        System.exit(0);
    }

    private void zeigeInfo(){}
}
