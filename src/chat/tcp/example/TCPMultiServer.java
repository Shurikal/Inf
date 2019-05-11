package chat.tcp.example;

import java.net.ServerSocket;
import java.net.Socket;



public class TCPMultiServer {



    private static boolean b = true;


    public static void main(String argv[]) throws Exception {

    }


    public static void start() throws Exception
    {
        b = true;
        {

            ServerSocket welcomeSocket = new ServerSocket(6789);

            Responder h = new Responder();
            // server runs for infinite time and
            // wait for clients to connect
            while (true) {
                System.out.println("Waiting!");
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
        }
    }

    public static void stop()
    {
        b = false;
    }
}
