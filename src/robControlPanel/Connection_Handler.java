package robControlPanel;


import java.io.*;
import java.net.Socket;


public class Connection_Handler implements Runnable
{



    private Rob_Connection rob1, rob2;

    public Connection_Handler()
    {
        rob1 = new Rob_Connection("169.254.1.1",2000);
        rob2 = new Rob_Connection("169.254.1.2",2000);
    }

    public static void main(String[] args)
    {

    }

    public void connect_Rob1() throws IOException
    {
        rob1.connect();
    }

    public void connect_Rob2() throws IOException
    {
        rob2.connect();
    }

    public void disconnect_Rob1() throws IOException
    {
        rob1.disconnect();
    }

    public void disconnect_Rob2() throws IOException
    {
        rob2.disconnect();
    }

    public boolean connected_Rob1()
    {
        return rob1.connected();
    }

    public boolean connected_Rob2()
    {
        return rob2.connected();
    }

    @Override
    public void run()
    {
        while (true)
        {


            try{
                Thread.sleep(20);
            }catch (InterruptedException e)
            {
                System.out.println(e);
            }
        }
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
