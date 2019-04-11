package aufgaben_gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MailClientGui
{
    private JLabel fromLabel, toLabel, subjectLabel;
    private JButton sendMailButton, receiveMailButton;
    private JTextArea textArea;
    private JTextField fromTextField, toTextField, subjectTextField;
    private JScrollPane scrollPane;

    private JFrame frame;

    public MailClientGui(String benutzer)
    {
        fromLabel = new JLabel("From:");
        toLabel = new JLabel("To:");
        subjectLabel = new JLabel("Subject:");
        sendMailButton = new JButton("Send");
        receiveMailButton = new JButton("Receive");
        textArea = new JTextArea();
        fromTextField = new JTextField();
        toTextField = new JTextField();
        subjectTextField = new JTextField();
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new TitledBorder("Text:"));

        frame = new JFrame("Mail Client:" + benutzer);
        frame.setMinimumSize(new Dimension(400,300));


    }
}
