package chat.chat_vorlagen.client;

import javax.swing.*;

import chat.chat_vorlagen.message.*;

public class GUI {

    //private static final  String  CRLF        = "\r\n";
    private static final  int     SERVER_PORT = 5555;
    private static final  String  SERVER_HOST = "localhost";


    
    private  Communication  communication;    

    public GUI (Communication communication) throws Exception {
        
        JFrame fenster = new JFrame("SimpleChatClient NTB Version");
        
        // hier kommt Ihr Code -- siehe die einzelnen Teile unten


        /************* CONFIGURATION PANEL ************/
        // create server panel

        // create button panel

        // create name panel

        // create configuration panel

        /************* RECEIVED MESSAGES PANEL ************/
        // create messages panel


        /************* SEND MESSAGE PANEL ************/
        // create message panel
        
        /************* ALLES ZUSAMMENF�GEN ************/

        // setup ActionListeners for buttons


        fenster.pack ();
        fenster.setVisible (true);
    } 

    
    // hier kommt Ihr Code f�r die drei Event-Handler
    private void send (){
        // send the user message
    } 

    private void register () {
        // register the user
    } 

    private void unregister () {
        // unregister the user
    } 

    // we need these to give the Receiver access to the GUI to update the GUI as needed
    public JTextArea getMessagesText(){
        return null; // replace this!
    }

    public JTextField getFilterField(){
        return null; // replace this!
    }

    public JTextField getNameField(){
        return null; // replace this!
    }
} 