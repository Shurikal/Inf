package robControlPanel;

import java.net.ServerSocket;
import java.net.Socket;

public class RobEmulator
{

    private ServerSocket socket;

    private Socket client;

    public RobEmulator()
    {
        try {
            socket = new ServerSocket(5555);
        }catch(Exception e) { }

        while(true){

            boolean b = false;

            try {
                System.out.println("Warte auf Rob");
                client = socket.accept();
                System.out.println("Rob verbunden");

            }catch (Exception e){}

            if(!client.isClosed()){
                new Thread(new Rob_Receiver(client)).start();
                new Thread(new Rob_Sender(client,true)).start();
            }
        }

    }

    public static void main(String[] args) {
        new RobEmulator();
    }

    public void stop(){
        try{
            if (client != null)
            socket.close();
        }catch(Exception e){}
    }
}
