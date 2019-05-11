package chat.tcp.example;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServer implements Runnable
{
    Responder h;
    Socket connectionSocket;
    private boolean bstop;

    public MyServer(Responder h, Socket connectionSocket)
    {
        this.h = h;
        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run()
    {

        while (h.responderMethod(connectionSocket) && !bstop)
        {
            try {
                System.out.println("New Thread!");
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

    public void stop()
    {
        bstop = true;
    }
}
