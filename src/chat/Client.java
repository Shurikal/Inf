package chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Client
{

    DatagramSocket socket;

    public Client()
    {
        try
        {
            socket = new DatagramSocket();
        }catch(Exception e)
        {

        }
    }


    public void send(String destination, int port, String text)
    {
        byte[] sendBuffer = text.getBytes();
        InetSocketAddress destinationSocket = new InetSocketAddress(destination,port);
        DatagramPacket packet = new DatagramPacket(sendBuffer, sendBuffer.length, destinationSocket);
        try {
            socket.send(packet);
            if(socket!=null)
            {
                socket.close();
            }
        }catch(Exception e)
        {

        }
    }
}
