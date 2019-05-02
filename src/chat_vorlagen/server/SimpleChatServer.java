package chat_vorlagen.server;

import java.net.*;
import java.util.*;
import chat_vorlagen.message.*;

import java.util.Observable;

public class SimpleChatServer extends Observable {
    public static final int PORT = 5555;
    private Map<String, InetSocketAddress> clients = new HashMap<String, InetSocketAddress> ();


    public SimpleChatServer() {

        GUI gui = new GUI(this);
        this.addObserver(gui);

        try {

            System.out.println ("SimpleChatServer starting...");
            InetAddress localhost = InetAddress.getLocalHost ();
            System.out.println ("IP Address: " + localhost + ", Port: " + PORT);

            while (true) {     
                // Wait to receive a datagram, blocking call
                System.out.println ("Waiting for message...");
                System.out.println ();
                setChanged(); notifyObservers();
 
                // wait for message, check type and act accordingly

                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    public static void main (String[] args) {
        try { 
            new SimpleChatServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    public Map<String, InetSocketAddress> getClients () {
        return clients;
    }
} 