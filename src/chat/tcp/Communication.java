package chat.tcp;

import java.net.Socket;

public class Communication
{
    private int port;
    private String host;


    public void connect(String host, int port)
    {
        this.host = host;
        try {
            Socket s1 = new Socket("localhost", 1254);
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void send()
    {

    }
}
