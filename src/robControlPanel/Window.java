package robControlPanel;

import javax.swing.*;
import java.awt.*;

public class Window
{
    public static void main(String[] args)
    {
        JFrame fenster = new JFrame("Better Proxy");

        Container contentpane = fenster.getContentPane();

        contentpane.setLayout(new BorderLayout());

        fenster.add(new GUI(),BorderLayout.CENTER);

        fenster.pack();
        fenster.setVisible(true);
    }
}
