package pong;

import javax.swing.*;
import java.awt.*;

public class Pong
{
    public static void main(String[] args)
    {
        JFrame fenster = new JFrame("Pong");

        Container contentpane = fenster.getContentPane();

        contentpane.setLayout(new BorderLayout());

        fenster.add(new Spielfeld(),BorderLayout.CENTER);

        fenster.pack();
        fenster.setVisible(true);

    }

}
