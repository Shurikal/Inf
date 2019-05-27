package robControlPanel;

import java.io.IOException;
import java.net.Socket;

public class Rob_Connection2 implements Runnable
{
    private String ip;
    private int port;
    private GUI gui;
    private Rob_Sender sender;
    private Rob_Receiver receiver;
    private Socket socket;


    public Rob_Connection2(String ip, int port, GUI gui) {
        this.ip = ip;
        this.port = port;
        this.gui = gui;
    }

    public void run(){

        try{
            socket = new Socket(ip, port);
        }catch (Exception e){
            System.out.println("Could not create socket");
        }
        new Thread(new Rob_Receiver(socket,gui)).start();
        new Thread(new Rob_Sender(socket,gui)).start();

    }

    public void disconnect() throws IOException
    {}

    public  boolean connected() {
        if(socket == null)
        {
            return false;
        }
        return !socket.isClosed();
    }
}



