package chat_vorlagen.tests;
import message.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MessageTestMitGUI
{
    JTextField userTextFeld;
    JTextField hostTextFeld;
    Communication com;
    
    class RegisterHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Erzeugen einer RegisterMessage
            RegisterMessage m = new RegisterMessage(userTextFeld.getText());
            // Senden dieser Message
            com.sendMessage(hostTextFeld.getText(), 6666, m);
        }
    }
    
    public MessageTestMitGUI() {
        com = new Communication();
        com.open();
        
        JFrame frame = new JFrame("Message Tests");
        Container contentPane = frame.getContentPane();
        
        JButton sendButton = new JButton("Register");
        sendButton.addActionListener(new RegisterHandler());
        contentPane.add(sendButton, BorderLayout.EAST);
        
        userTextFeld = new JTextField(20);
        contentPane.add(userTextFeld, BorderLayout.NORTH);
        
        hostTextFeld = new JTextField(20);
        contentPane.add(hostTextFeld, BorderLayout.SOUTH);
        
        
        frame.pack();
        frame. setVisible(true);
    }
}
