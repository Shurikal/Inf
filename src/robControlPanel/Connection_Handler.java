package robControlPanel;

import java.io.*;

public class Connection_Handler implements Runnable
{

    GUI gui;

    private static Rob_Connection2 rob1, rob2;

    public Connection_Handler(GUI gui)
    {
    this.gui = gui;
    }

    public static void main(String[] args)
    {

    }

    public void connect_Rob1()
    {
        rob1 = new Rob_Connection2("169.254.1.1",2000);
    }

    public void connect_Rob2()
    {
        rob2 = new Rob_Connection2("169.254.1.2",2000);
    }

    public void disconnect_Rob1()
    {
            rob1.disconnect();
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

    public Rob_Connection2 getRob1(){
        if(rob1!=null){
            return rob1;
        }else{
            return null;
        }
    }

    public boolean connected_Rob2()
    {
        return rob2.connected();
    }

    public static void sendDataRob1(int i){
        rob1.cmd.writeCmd(i);
    }

    @Override
    public void run() {
        while(true){

            //Reads data from Rob1
            if(rob1 !=null) {
                while (rob1.cmd.readCmd() == CmdInt.Type.Cmd) {
                    int i = rob1.cmd.getInt();
                    gui.addText(i + " <- Rob1");
                }
            }

            try{
                Thread.sleep(5);
            }catch (Exception e){}
        }
    }
}
