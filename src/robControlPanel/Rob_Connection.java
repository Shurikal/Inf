package robControlPanel;

import java.io.*;
import java.net.Socket;

public class Rob_Connection implements Runnable
{

    private byte[] tx, rx;

    private  Socket socket;

    public  PrintWriter out;
    public  BufferedReader in;

    private  String rob;

    private  int port;

    public Rob_Connection(String ip, int port)
    {
        this.rob = ip;
        this.port = port;

        tx = new byte[5];
    }

    public  void connect() throws IOException
    {
        System.out.println("Test");

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
    }

    public  boolean connected()
    {
        if(socket == null)
        {
            return false;
        }
        return socket.isConnected();
    }

    @Override
    public void run()
    {
        try {
            socket = new Socket(rob, port);

            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e)
        {
            System.out.println(e);
        }

        while (socket.isConnected())
        {
            try {
                System.out.println(in.readLine());
            }catch (IOException e){
                System.out.println(e);

                try{
                    Thread.sleep(200);
                }catch (InterruptedException f)
                {
                    System.out.println(f);
                }
        }


        }
    }
}
