package robControlPanel;

import java.io.*;
import java.net.Socket;

public class Rob_Connection implements Runnable
{

    private  Socket socket;

    public  PrintWriter out;
    public  BufferedReader in;

    private  String rob = "169.254.1.1";

    private  int port = 2000;

    private boolean connected;

    public Rob_Connection(String ip, int port)
    {
        this.rob = ip;
        this.port = port;
    }

    public  void connect() throws IOException
    {
        socket = new Socket(rob, port);
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        connected = true;
        this.run();
    }

    public  void disconnect() throws IOException
    {
        if (out != null)
            out.close();
        if (in != null)
            in.close();
        if (socket != null)
            socket.close();
        connected = false;
    }

    public  boolean connected()
    {
        return connected;
    }

    @Override
    public void run()
    {
        while(connected)
        {

        }
    }
}
