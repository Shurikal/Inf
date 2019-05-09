package chat.chat_vorlagen.client;

import chat.chat_vorlagen.message.*;

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