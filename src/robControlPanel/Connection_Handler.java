package robControlPanel;


import java.io.*;
import java.net.Socket;


public class Connection_Handler
{

    Thread trob1,trob2;
    GUI gui;



    private Rob_Connection rob1, rob2;

    public Connection_Handler(GUI gui)
    {
    this.gui = gui;

    }

    public static void main(String[] args)
    {

    }

    public void connect_Rob1()
    {

        rob1 = new Rob_Connection("169.254.1.1",2000,gui);
        trob1 = new Thread(rob1);
        trob1.start();
    }

    public void connect_Rob2()
    {

        rob2 = new Rob_Connection("169.254.1.2",2000,gui);
        trob2 = new Thread(rob2);
        trob2.start();
    }

    public void disconnect_Rob1()
    {
        try {
            rob1.disconnect();

        }catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public void disconnect_Rob2() throws IOException
    {
        rob2.disconnect();
    }

    public boolean connected_Rob1()
    {
        if(rob1 == null)
        {
            return false;
        }
        return rob1.connected();
    }

    public boolean connected_Rob2()
    {
        return rob2.connected();
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
