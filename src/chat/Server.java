package chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server
{

    DatagramSocket socket;
    byte[] receiveBuffer;

    public Server()
    {
        try
        {
            socket = new DatagramSocket(4321);

            receiveBuffer = new byte[2048];



        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void getData()
    {
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        try {
            socket.receive(receivePacket);
            String text = new String(receiveBuffer);

            System.out.println(text);
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void run()
    {
        Thread receiver = new Thread(){
            public void run()
            {

                    getData();

            }
        };

        receiver.run();
    }
}
