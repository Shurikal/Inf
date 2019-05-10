package chat.tcp;

import bildverarbeitung.Settings;

import chat.chat_vorlagen.message.Communication;
import chat.chat_vorlagen.message.PostingMessage;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GUI
{
    private JFrame fenster;

    private JTextArea textfeld;

    private JTextField server, name,message;

    private chat.chat_vorlagen.message.Communication com;

    private boolean registred;

    public GUI()
    {

        Settings.set();
        fenster = new JFrame("Mein Chat");
        com = new Communication();

        com.open();

        fenster.add(addContent());
        fenster.pack();
        fenster.setVisible(true);

    }


    public static void main(String[] args)
    {
        new GUI();
    }

    private JPanel addContent()
    {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(centerPanel(), BorderLayout.CENTER);

        contentPane.add(topPanel(), BorderLayout.NORTH);

        contentPane.add(sendPanel(), BorderLayout.SOUTH);

        return contentPane;
    }

    private JPanel centerPanel()
    {
        JPanel center = new JPanel();
        textfeld = new JTextArea();
        textfeld.setEditable(false);
        center.setLayout(new BorderLayout());

        center.setBorder(new TitledBorder("Recieved Messages"));
        JScrollPane scrollPane = new JScrollPane(textfeld);

        center.add(scrollPane,BorderLayout.CENTER);

        return center;
    }

    private JPanel topPanel()
    {
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(2,1));

        top.setBorder(new TitledBorder("Configuration"));
        top.add(server());
        top.add(name());

        return top;
    }

    private JPanel server()
    {
        JPanel serverPanel = new JPanel();
        serverPanel.setLayout(new BorderLayout());

        server = new JTextField();

        JLabel serverLabel = new JLabel("Server:");

        serverLabel.setPreferredSize(new Dimension(150,0));

        serverPanel.add(serverLabel,BorderLayout.WEST);
        serverPanel.add(server,BorderLayout.CENTER);
        return serverPanel;
    }

    private JPanel name()
    {
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BorderLayout());

        name = new JTextField();


        JButton register = new JButton("Register");
        //register.setPreferredSize(new Dimension(400,0));
        register.addActionListener(e->{
            register();
        });

        JButton unregister = new JButton("Unregister");
        //unregister.setPreferredSize(new Dimension(400,0));
        unregister.addActionListener(e->{
            unregister();
        });
        JPanel grid = new JPanel();
        grid.setLayout(new FlowLayout(FlowLayout.RIGHT));
        grid.add(register);
        grid.add(unregister);

        JLabel nameLabel = new JLabel("Name:");

        nameLabel.setPreferredSize(new Dimension(150,0));

        namePanel.add(grid, BorderLayout.EAST);
        namePanel.add(nameLabel,BorderLayout.WEST);
        namePanel.add(name,BorderLayout.CENTER);

        return namePanel;
    }



    private JPanel sendPanel()
    {
        JPanel sendPanel = new JPanel();
        message = new JTextField();

        sendPanel.setLayout(new BorderLayout());

        JButton send = new JButton("Send");
        send.addActionListener(e->
        {
            send();
        });

        sendPanel.setBorder(new TitledBorder("Send Message"));
        sendPanel.add(message,BorderLayout.CENTER);
        sendPanel.add(send, BorderLayout.EAST);
        return sendPanel;
    }

    private void register()
    {

    }

    private void unregister()
    {

    }

    private void send()
    {

    }

    private int getPort(String s)
    {
        return Integer.parseInt(s.substring(s.indexOf(":")+1));
    }

    private String getHost(String s)
    {
        return s.substring(0,s.indexOf(":"));
    }

    public void addMessage(PostingMessage pm)
    {
        textfeld.append(pm.getUser()+"\t:"+pm.getText()+"\r\n");
    }

}