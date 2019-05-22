package robControlPanel;


import java.io.*;
import java.net.Socket;


public class Connection
{
    private static Socket socket1, socket2;

    public static PrintWriter out1, out2;
    public static BufferedReader in1, in2;

    private static String rob1 = "169.254.1.1";
    private static String rob2 = "169.254.1.2";

    private static int port1 = 2000;
    private static int port2 = 2000;

    private static boolean connected_Rob1,connected_Rob2;


    public static void connect_Rob1() throws IOException
    {
            socket1 = new Socket(rob1, port1);
            out1 = new PrintWriter(new OutputStreamWriter(socket1.getOutputStream()), true);
            in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
    }

    public static void disconnect_Rob1() throws IOException
    {
            if (out1 != null)
                out1.close();
            if (in1 != null)
                in1.close();
            if (socket1 != null)
                socket1.close();
    }

    public static boolean connected_Rob1()
    {
        return connected_Rob1;
    }

    public static boolean connected_Rob2()
    {
        return connected_Rob2;
    }



    public static void connect_Rob2() throws IOException
    {
            socket2 = new Socket(rob2, port2);
            out2 = new PrintWriter(new OutputStreamWriter(socket2.getOutputStream()), true);
            in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
    }

    public static void disconnect_Rob2() throws IOException
    {
            if (out2 != null)
                out2.close();
            if (in2 != null)
                in2.close();
            if (socket2 != null)
                socket2.close();

    }

    public static void main(String[] args)
    {

    }

    /*public boolean writeCmd(byte header, int cmd) {
        tx[0] = header;  //Default 0x11
        tx[1] = (byte) (cmd >> 24);
        tx[2] = (byte) (cmd >> 16);
        tx[3] = (byte) (cmd >> 8);
        tx[4] = (byte) cmd;
        return slip.write(tx, 0, 5);
    }*/

}
