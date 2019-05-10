package chat.tcp;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String args[])
    {      // Register service on port 1254

    }

    public Server()
    {
        try {
            ServerSocket s = new ServerSocket(5555);
            Socket s1 = s.accept(); // Wait and accept a connection      // Get a communication stream associated with the socket
            OutputStream s1out = s1.getOutputStream();
            DataOutputStream dos = new DataOutputStream(s1out);      // Send a string!
            dos.writeUTF("Hi");      // Close the connection, but not the server socket
            dos.close();
            s1out.close();
            s1.close();
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
