package chat.tcp;

import chat.tcp.example.MyServer;
import chat.tcp.example.Responder;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerManager extends Thread
{
    private boolean active;

    private ArrayList<Thread> threads;

    private ServerSocket welcomeSocket;

    private int port= 6789;

    public ServerManager()
    {
        threads = new ArrayList<>();


    }

    public void run()
    {
        try{
        System.out.println("Server Start @port "+ port);
        welcomeSocket = new ServerSocket(port);
    }catch(Exception e)
    {

    }
        active = true;
        System.out.println("Server run");
        try{
        ServerSocket welcomeSocket = new ServerSocket(6789);

        Responder h = new Responder();
        // server runs for infinite time and
        // wait for clients to connect
        while (true) {
            // waiting..
            Socket connectionSocket = welcomeSocket.accept();

            // on connection establishment start a new thread for each client
            // each thread shares a common responder object
            // which will be used to respond every client request
            // need to synchronize method of common object not to have unexpected behaviour
            Thread t = new Thread(new MyServer(h, connectionSocket));

            // start thread
            t.start();
        }
        }catch (Exception e)
            {
            }

    }

    public void stopAll()
    {
        for(Thread t : threads)
        {
            if(t instanceof Server)
            {
                ((Server) t).stopServer();
            }
        }
    }
}



