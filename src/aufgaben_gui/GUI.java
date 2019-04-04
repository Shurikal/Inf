package aufgaben_gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI
{

    JFrame fenster;
    Container inhaltsFlaeche;
    JTextArea textArea;
    JTextField suchenTextFeld;

    final String musterText = "Lorem ipsum dolor sit amet, consetetur ...";

    public GUI()
    {
        fenster = new JFrame("TestGui");
        inhaltsFlaeche = fenster.getContentPane();
        inhaltsFlaeche.setPreferredSize(new Dimension(250, 100));
        inhaltsFlaeche.setLayout(new BorderLayout());

        fensterInhaltErzeugen();
        fenster.pack();
        fenster.setVisible(true);
    }


    private void fensterInhaltErzeugen()
    {
        textArea = new JTextArea();
        textArea.append(musterText);

        //Hier kommt der eigene Code
        JScrollPane scroll = new JScrollPane(textArea);        
        inhaltsFlaeche.add(scroll, BorderLayout.CENTER);     
        JButton k1 = new JButton("K1");       
        k1.addActionListener (e -> {System.out.println("K1");});     
        JButton k2 = new JButton("K2");        
        JPanel knoepfe = new JPanel();
        knoepfe.setLayout(new BoxLayout(knoepfe, BoxLayout.Y_AXIS));  
        knoepfe.add(k1);
        knoepfe.add(k2);
        inhaltsFlaeche.add(knoepfe, BorderLayout.EAST);
    }

    private void erzeugen()
    {
        fenster = new JFrame("Texteditor");
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.LEADING));
        JButton jb = new JButton(new ImageIcon("exit.png"));
        jb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        }
        );
        p.add(jb);
        jb = new JButton(new ImageIcon("save.png"));
        p.add(jb);
        fenster.add(p, BorderLayout.NORTH);
        JTextArea jt = new JTextArea(20, 20);
        fenster.add(jt);
        fenster.pack();
        fenster.setVisible(true);
    }
}
