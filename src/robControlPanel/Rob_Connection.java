package robControlPanel;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class Rob_Connection implements Runnable
{

    private byte[] tx, rx;

    private static final byte OPEN = (byte)0xd5;
    private static final byte CLOSE = (byte)0xd4;
    private static final byte HEARTBEAT = (byte)0xd3;
    private static final byte CMD = '$';

    private  Socket socket;

    private GUI gui;

    public  PrintWriter out;
    public  BufferedReader in;

    private  String rob;

    private  int port;


    public Rob_Connection(String ip, int port, GUI gui)
    {
        this.rob = ip;
        this.port = port;

        this.gui = gui;

        tx = new byte[5];
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
        return !socket.isClosed();
    }

    @Override
    public void run()
    {
        try {

        socket = new Socket(rob, port);
        System.out.println("Socket erstellt");

        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        System.out.println("OUT erstellt");
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()),2048);
        System.out.println("IN erstellt");

        } catch (IOException e)
        {
            gui.addText(e.getLocalizedMessage());
        }

        if(socket == null)
        {
            gui.addText("Keine Verbindung möglich");
            out = null;
            in = null;

        }else {
            System.out.println("Connection running");
            while (!socket.isClosed()) {
                //String s = "";

                int i = 0;

                try {
                    if (in.ready()) {
                        while(i >=0) {
                            //s = in.readLine(true);
                            i = in.read();

                            if(i>=1)
                            {
                                if(i>32_767 )
                                {
                                    i -= 65_535;
                                }
                                gui.addText("" + i);
                            }
                        }
                    }
                } catch (IOException e) {
                    gui.addText(e.getLocalizedMessage());
                    System.out.println(e);
                }

                //gui.addText(s);

                out.write(213);
                out.flush();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException f) {
                    System.out.println(f);
                }
            }
        }
    }
}
