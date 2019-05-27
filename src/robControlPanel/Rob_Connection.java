package robControlPanel;

import java.io.*;
import java.lang.annotation.Target;
import java.net.Socket;

public class Rob_Connection implements Runnable
{
    private static final byte OPEN = (byte)0xd5;
    private static final byte CLOSE = (byte)0xd4;
    private static final byte HEARTBEAT = (byte)0xd3;
    private static final byte CMD = '$';

    private static final byte SLIP_CMD = (byte)0xc0;

    private static final byte ESCAPE = (byte)0xf1;
    private static final byte ESCAPED_ESCAPE = (byte)0xf3;
    private static final byte ESCAPED_OPEN = (byte)0xf2;
    private static final byte ESCAPED_CLOSE = (byte)0xf5;
    private static final byte ESCAPED_HEARTBEAT = (byte)0xe6;
    private static final byte ESCAPED_CMD = (byte)0xf0;
    private static final byte[] CMD_MODE ={ CMD, CMD, CMD };
    private static final byte[] CMD_TERMINATOR = "\r\n".getBytes();
    private static final byte[] CMD_MODE_ENTERED = "CMD".getBytes();
    private static final byte[] CMD_RESULT_OK = "AOK".getBytes();

    private static final double TASK_PERIOD = 0.5;

    private  Socket socket;

    private GUI gui;

    public DataOutputStream out;

    public DataInputStream in;


    //public  BufferedReader in;
    //public BufferedInputStream in;      //Bytewise Input

    private  String rob;

    private int tickCounter=0;

    private  int port;


    public Rob_Connection(String ip, int port, GUI gui) {
        this.rob = ip;
        this.port = port;

        this.gui = gui;

    }


    //Disconnect Method
    public  void disconnect() throws IOException {
        if (out != null)
            out.close();
        if (in != null)
            in.close();
        if (socket != null)
            socket.close();
    }

    //Send a cmd
    public void sendCMD(int i) {
        try {
            out.write(i);
        }catch (Exception e) { }
    }

    //Returns if socket is connected
    public  boolean connected() {
        if(socket == null)
        {
            return false;
        }
        return !socket.isClosed();
    }

    @Override
    public void run() {

        //Try to create a new socket
        try {
            socket = new Socket(rob, port);
            System.out.println("Socket erstellt");
             //out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            out = new DataOutputStream(socket.getOutputStream());

            //out = new DataOutputStream(System.out);
            //in = new DataInputStream(socket.getInputStream());

            in = new DataInputStream(System.in);
            //System.out.println("OUT erstellt");
            //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //System.out.println("IN erstellt");
        } catch (IOException e) {
            gui.addText(e.getLocalizedMessage());
        }

        //Wenn kein socket erstellt werden kann
        if(socket == null) {
            gui.addText("Keine Verbindung möglich");
        }else{

            System.out.println("Connection running");
            long starttime = System.currentTimeMillis();
            int c =0;
            try {

                out.write(SLIP_CMD);
                gui.addText(""+ SLIP_CMD);
                out.write(OPEN);
                out.write(SLIP_CMD);
                out.flush();

            } catch (Exception e) {

            }
            while (!socket.isClosed()) {
                int i = 0;

                try {
                    long oldtime = System.currentTimeMillis();

                    while(((i = in.read()) != -1) && !(System.currentTimeMillis() > oldtime+500)) {

                        gui.addText(decompile(i));

                        c++;
                    }

                    if(i ==-1){
                        in = null;
                        in =new DataInputStream(socket.getInputStream());
                        }

                } catch (IOException e) {
                    gui.addText(e.getLocalizedMessage());
                    System.out.println(e);
                }


                try {
                    //gui.addText(s);
                    if (tickCounter % (1 / TASK_PERIOD) == 0) {
                        /*out.write((byte)0xc0);
                        out.write(HEARTBEAT);
                        out.write((byte)0xc0);*/
                        out.write(SLIP_CMD);
                        out.write(HEARTBEAT);
                        out.write(SLIP_CMD);
                        out.flush();

                        System.out.println("Heartbeat");
                    }

                    out.flush();
                    //out.close();
                } catch (Exception e)
                {

                }
                tickCounter++;
                System.out.println(tickCounter);

                //Pause Thread
                long oldtime = System.currentTimeMillis();
                while(System.currentTimeMillis() < oldtime + 500)
                {
                    try {
                        Thread.sleep((int)(1));
                    } catch (InterruptedException f) {
                        System.out.println(f);
                    }
                }


            }
        }
        System.out.println("Connection lost");
    }


    public String decompile(int i) {
        int a = i>>8;
        int b = (byte)i;
        return (""+ i);
    }

}
