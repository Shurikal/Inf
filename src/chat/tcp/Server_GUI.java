package chat.tcp;

import chat.tcp.example.TCPMultiClient;
import chat.tcp.example.TCPMultiServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Server_GUI
{

    private JTextField portField;

    private JTextField textfeld;

    private ServerManager sm;

    private boolean serverRunning;

    private JButton startButton;

    public Server_GUI()
    {
        JFrame fenster = new JFrame("ServerManager");
        Container contentPane = fenster.getContentPane();
        contentPane.setLayout(new BorderLayout());
        menu(fenster);
        sm = new ServerManager();


        contentPane.add(top(), BorderLayout.NORTH);

        contentPane.add(center(), BorderLayout.CENTER);

        fenster.pack();
        fenster.setVisible(true);
    }

    public static void main(String[] args)
    {
        new Server_GUI();
    }


    private void menu(JFrame fenster)
    {
        final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menuezeile = new JMenuBar();
        fenster.setJMenuBar(menuezeile);

        JMenu menue;
        JMenuItem eintrag;

        menue = new JMenu("Datei");
        menuezeile.add(menue);

        eintrag = new JMenuItem("Beenden");
        eintrag.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        eintrag.addActionListener(e -> { beenden(); });
        menue.add(eintrag);
    }


    private JPanel top()
    {
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());

        JLabel portLabel = new JLabel("Port:");
        portLabel.setPreferredSize(new Dimension(100, 0));

        startButton = new JButton("Start");
        startButton.addActionListener( e->
        {
            start();
        });

        portField = new JTextField();

        top.add(portLabel, BorderLayout.WEST);
        top.add(portField, BorderLayout.CENTER);
        top.add(startButton, BorderLayout.EAST);
        return top;
    }


    private JPanel center()
    {
        JPanel center = new JPanel();


        center.setLayout(new BorderLayout());
        textfeld = new JTextField();
        textfeld.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textfeld);

        center.add(scrollPane,BorderLayout.CENTER);
        return center;
    }

    private void beenden()
    {
        sm.stopAll();
        System.exit(0);
    }

    private void start()
    {
        if(!serverRunning)
        {
            try {
                TCPMultiServer.start();

            }catch (Exception e)
            {

            }
            serverRunning = true;
            System.out.println("ServerManager Start");
            startButton.setText("Stop");

        }else{
            TCPMultiServer.stop();
            serverRunning = false;

            System.out.println("ServerManager Stop");
            startButton.setText("Start");
        }
    }
}
