package chat_vorlagen.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import chat_vorlagen.message.*;

// diese Klasse m�ssen Sie nicht �ndern

public class SimpleChatClient {


    private Thread receiver;

    private  Communication  communication;    

    public SimpleChatClient() throws Exception {
        communication = new Communication ();
        communication.open();
        
        GUI gui = new GUI(communication);

        receiver = new Receiver(communication, gui);
        receiver.start ();
    } 


    public static void main (String argv[]) {
        try {
            new SimpleChatClient ();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

} 