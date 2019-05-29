package robControlPanel;

import java.net.Socket;

public class Rob_Connection2
{
    private String ip;
    private int port;
    private GUI gui;
    private Socket socket;

    public CmdInt cmd;

    private ByteFifo rx,tx;

    public Rob_Connection2(String ip, int port) {
        this.ip = ip;
        this.port = port;
        rx = new ByteFifo(2047);
        tx = new ByteFifo(2047);

        cmd = new CmdInt(new SLIP(rx, tx));

        try{
            socket = new Socket(ip, port);
        }catch (Exception e){
            System.out.println("Could not create socket");
        }
        new Thread(new Rob_Receiver(socket,rx,cmd)).start();
        new Thread(new Rob_Sender(socket,tx,cmd)).start();
    }

    public void disconnect() {
        try {
            socket.close();

        }catch(Exception e){}
    }

    public  boolean connected() {
        if(socket == null)
        {
            return false;
        }
        return !socket.isClosed();
    }
}



