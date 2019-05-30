package robControlPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GUI
{
    private JTextArea log;

    private JButton connectRob1, connectRob2, disconnectRob1,disconnectRob2, controlPanel;

    private JTextField cmdField;

    private JPanel gui;

    private JFrame fenster;

    private Rob_Connection rob1,rob2;

    private Connection_Handler robs;

    private boolean autoscroll;

    private JScrollPane scrollPane;

    public GUI() {
        fenster = new JFrame("Java Proxy");

        Container contentpane = fenster.getContentPane();

        contentpane.setLayout(new BorderLayout());

        gui = new JPanel();

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

        JButton autoscrollButton = new JButton("Autoscroll");
        autoscrollButton.addActionListener(e-> autoscroll=!autoscroll);

        top.add(connectRob1);
        top.add(connectRob2);
        top.add(controlPanel);

        disconnectRob1.addActionListener(e-> {if(rob1!=null){rob1.disconnect();}});
        disconnectRob2.addActionListener(e-> {if(rob2!=null){rob2.disconnect();}});

        top.add(disconnectRob1);
        top.add(disconnectRob2);
        top.add(autoscrollButton);
        return top;
    }

    private void connectRob1() {
        rob1 = new Rob_Connection("localhost",5555,"Rob1");
        if(!rob1.connected()){
            addText("Could not create socket");
        }else{
            robs.addRob(rob1);
        }
    }

    private void connectRob2() {
        rob2 = new Rob_Connection("169.254.1.2",2000,"Rob2");
        if(!rob1.connected()){
            addText("Could not create socket");
        }else{
            robs.addRob(rob2);
        }
    }

    private JPanel centerPanel() {
        JPanel center = new JPanel();

        center.setLayout(new BorderLayout());

        log = new JTextArea();
        log.setEditable(false);

        scrollPane = new JScrollPane(log);

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
        if(rob1 != null && rob2 !=null) {
            if(rob1.connected() && rob2.connected()){
                try {
                    int i = Integer.parseInt(cmdField.getText());
                    rob1.cmd.writeCmd(i);
                    rob2.cmd.writeCmd(i);
                    addText(i + " -> all");
                    cmdField.setText("");
                }catch (NumberFormatException e){}
            }
        }
    }

    private void sendToRob1() {
        if(rob1 != null && rob1.connected()){
            try {
                rob1.cmd.writeCmd(Integer.parseInt(cmdField.getText()));
                addText(cmdField.getText() + " -> Rob1");
                cmdField.setText("");
            }catch (NumberFormatException e){
                System.out.println(e);
            }
        }
    }

    private void sendToRob2() {
        if(rob2 != null && rob2.connected()){
            try{
                rob2.cmd.writeCmd(Integer.parseInt(cmdField.getText()));
                addText(cmdField.getText() + " -> Rob2");
                cmdField.setText("");
            }catch (NumberFormatException e){}
        }

    }

    private void openControlPanel1(){
        new ControlPanel(rob1);
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

    public void autoscroll(){
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue( vertical.getMaximum() );
    }

    public boolean autoScrollEnabled(){
        return autoscroll;
    }
}
