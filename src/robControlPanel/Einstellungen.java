package robControlPanel;


import javax.swing.*;
import java.awt.*;

public class Einstellungen extends JDialog
{
    private Connection_Handler connectionHandler;

    public Einstellungen(Connection_Handler connectionHandler, JFrame fenster){
        super(fenster, "Einstellungen",Dialog.ModalityType.DOCUMENT_MODAL);
        this.connectionHandler = connectionHandler;
        this.setLayout(new BorderLayout());

        this.add(new JLabel("Test"));

        //this.add(new JOptionPane());
        this.pack();
        this.setVisible(true);
    }

}
