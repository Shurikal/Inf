package robControlPanel;


import javax.swing.*;
import java.awt.*;

public class Settings extends JDialog
{
    private Connection_Handler connectionHandler;
    private JTextField address1,address2;
    private GUI gui;

    public Settings(Connection_Handler connectionHandler, JFrame fenster, GUI gui){
        super(fenster, "Einstellungen",Dialog.ModalityType.DOCUMENT_MODAL);
        this.connectionHandler = connectionHandler;
        this.setLayout(new BorderLayout());

        this.gui = gui;

        this.add(center(), BorderLayout.CENTER);
        //this.add(new JOptionPane());
        this.setPreferredSize(new Dimension(200,100));
        this.pack();
        this.setVisible(true);
    }

    private JPanel center(){
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3,1));

        address1 = new JTextField();

        center.add(adress("IP 1 : ", address1, gui.getAddress1(),gui.getPort1()));
        center.add(adress("IP 2 : ", address2,gui.getAddress2(),gui.getPort2()));
        JButton save = new JButton("Speichern");
        save.addActionListener(e-> save());
        center.add(save);
        return center;
    }

    private JPanel adress(String text, JTextField textField, String address,int port){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel(text), BorderLayout.WEST);
        textField = new JTextField(address + ":" + port);
        panel.add(textField,BorderLayout.CENTER);
        return panel;
    }

    private void save(){
        System.out.println(address1.getText());
        String s = address1.getText();
        if(s.contains(":")) {
            try{
                String[] parts = s.split(":");
                int port = Integer.parseInt(parts[1]);
                gui.setAddress1(parts[0]);
                gui.setPort1(port);
            }catch (NumberFormatException e){}

        }
    }

}
