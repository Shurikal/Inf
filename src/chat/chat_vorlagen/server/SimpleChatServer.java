package chat.chat_vorlagen.server;

import java.net.*;
import java.util.*;

import bildverarbeitung.Settings;
import chat.chat_vorlagen.message.*;

import java.util.Observable;

public class SimpleChatServer extends Observable {
    public static final int PORT = 5555;
    private  Map<String, InetSocketAddress>  clients;
    private Communication communication;

    public SimpleChatServer() {
        clients = new HashMap<String, InetSocketAddress> ();
        Settings.set();
        GUI gui = new GUI(this);
        this.addObserver(gui);

        try {
            communication = new Communication ();  
            communication.open(PORT);

            System.out.println ("SimpleChatServer starting...");
            InetAddress localhost = InetAddress.getLocalHost ();
            System.out.println ("IP Address: " + localhost + ", Port: " + PORT);

            while (true) {     
                // Wait to receive a datagram, blocking call
                System.out.println ("Waiting for message...");
                System.out.println ();
                setChanged(); notifyObservers();
                communication.waitForMessage ();  

                Message message = communication.getMessage ();
                InetSocketAddress socketAddress = (InetSocketAddress) communication.getSocketAddress ();

                if (message instanceof PostingMessage) {                      
                    PostingMessage p = (PostingMessage) message;
                    System.out.println ("PostingMessage received: " + p.getUser() + ": " + p.getText());
                    for (InetSocketAddress address : clients.values()) {
                        communication.sendMessage  (address, p); 
                        System.out.println ("Posted to " + address.getHostName() + "/" + address.getPort());
                    } 

                } else if (message instanceof RegisterMessage) {
                    // register client
                    RegisterMessage r = (RegisterMessage) message;
                    String user = r.getUser();
                    System.out.print ("RegisterMessage received: " + user);
                    if (clients.keySet().contains(user)) {
                        System.out.println (" rejected"); 
                        r = new RegisterMessage(null);
                    } else {
                        clients.put (user, socketAddress);
                        System.out.println (" registered");
                    }
                    communication.sendMessage  (socketAddress, r);
                } else if (message instanceof UnregisterMessage) {
                    // unregister client
                    UnregisterMessage u = (UnregisterMessage) message;
                    String user = u.getUser();
                    clients.remove (user);
                    System.out.println ("UnregisterMessage received: " + user + " unregistered");
                } 
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