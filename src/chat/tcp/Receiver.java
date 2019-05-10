package chat.tcp;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver
{
    ServerSocket server;

    public Receiver()
    {
        try {
            server = new ServerSocket(5555);
             // Wait and accept a connection      // Get a communication stream associated with the socket

        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void run () {
        while (true) {
            try {
                Socket client = this.server.accept();
                String sclient = client.getInetAddress().getHostAddress();
                System.out.println("New Connection from: "+ sclient);

                if(true)
                {
                    Runnable appendText = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                OutputStream s1out = client.getOutputStream();
                                DataOutputStream dos = new DataOutputStream(s1out);      // Send a string!
                                dos.writeUTF("Hi");      // Close the connection, but not the server socket
                                dos.close();
                                s1out.close();
                                client.close();
                            } catch (Exception e)
                            {

                            }
                        }
                    };
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
