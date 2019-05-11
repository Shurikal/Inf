package chat.tcp;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Client_GUI
{

    private JTextField textfeld;

    public static void main(String[] args)
    {
        new Client_GUI();
    }


    public Client_GUI()
    {
        JFrame fenster = new JFrame("Client");
        Container contentPane = fenster.getContentPane();
        contentPane.setLayout(new BorderLayout());
        menu(fenster);

        contentPane.add(top(), BorderLayout.NORTH);

        contentPane.add(center(), BorderLayout.CENTER);

        fenster.pack();
        fenster.setVisible(true);
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
        top.setLayout(new GridLayout(2,1));

        //ServerManager Linie
        JPanel grid1 = new JPanel();
        grid1.setLayout(new BorderLayout());
        JLabel serverLabel = new JLabel("ServerManager:");
        serverLabel.setPreferredSize(new Dimension(100,0));
        grid1.add(serverLabel,BorderLayout.WEST);
        JTextField serverField = new JTextField();
        serverField.setMinimumSize(new Dimension(100,0));
        grid1.add(serverField, BorderLayout.CENTER);
        JButton connectButton = new JButton("Connect");
        connectButton.setPreferredSize(new Dimension(120,0));
        JButton disconnectButton = new JButton("Disconnect");
        disconnectButton.setPreferredSize(new Dimension(120,0));
        connectButton.addActionListener(e->
        {
            connect();
        });

        disconnectButton.addActionListener(e->
        {
            disconnect();
        });

        grid1.add(connectButton, BorderLayout.EAST);

        //Username
        JPanel grid2 = new JPanel();
        grid2.setLayout(new BorderLayout());
        JLabel userLabel = new JLabel("User:");
        userLabel.setPreferredSize(new Dimension(100, 0));
        JTextField userField = new JTextField();
        userField.setMinimumSize(new Dimension(100,0));
        grid2.add(userLabel, BorderLayout.WEST);
        grid2.add(userField,BorderLayout.CENTER);
        grid2.add(disconnectButton, BorderLayout.EAST);

        top.add(grid1);
        top.add(grid2);

        top.setBorder(new TitledBorder("Verbindung"));
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
        System.exit(0);
    }

    private void disconnect()
    {

    }

    private void connect()
    {

    }
}
