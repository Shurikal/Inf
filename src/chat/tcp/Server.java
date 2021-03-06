package chat.tcp;

import chat.tcp.example.MyServer;
import chat.tcp.example.Responder;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread
{


    Responder h;
    Socket connectionSocket;

    private boolean brun = true;


    public Server(Responder h, Socket connectionSocket)
    {
        this.h = h;
        this.connectionSocket = connectionSocket;
    }



    public void run()
    {
        while (h.responderMethod(connectionSocket) && brun)
        {
            try {
                // once an conversation with one client done,
                // give chance to other threads
                // so make this thread sleep
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        try {
            connectionSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void stopServer()
    {
        brun = false;
    }

}
