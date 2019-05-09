package chat.chat_vorlagen.client;
import chat.chat_vorlagen.message.*;

public class Receiver extends Thread
{
    private Communication communication;
    private GUI gui;
    
    public Receiver(Communication communication, GUI gui)
    {
       this.communication = communication;
       this.gui = gui;
    }
    
    public void run () { 
                while (true) {
                    try {
                        // Wait to receive a datagram, blocking call
                        // datagram was received
                        // check message type and process accordingly
                        
                        // hier kommt Ihr Code
                        

                    } catch (Exception e) {
                        e.printStackTrace();
                    } 
                } 
            }   
}
