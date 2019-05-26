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

    private static final double TASK_PERIOD = 0.5;

    private  Socket socket;

    private GUI gui;

    //public  PrintWriter out;

    public BufferedOutputStream out;

    //public  BufferedReader in;
    public BufferedInputStream in;      //Bytewise Input

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
        }catch (Exception e)
        {

        }
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
            out = new BufferedOutputStream(socket.getOutputStream());

        System.out.println("OUT erstellt");
        //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        in = new BufferedInputStream(socket.getInputStream(),32*1024);

        System.out.println("IN erstellt");
        } catch (IOException e) {
            gui.addText(e.getLocalizedMessage());
        }

        //Wenn kein socket erstellt werden kann
        if(socket == null) {
            gui.addText("Keine Verbindung möglich");
            out = null;
            in = null;
        }else{

            System.out.println("Connection running");
            while (!socket.isClosed()) {
                //String s = "";

                int i = 0;

                try {

                    //if (in.ready()) {
                    if(in.available()>0){
                        int c =0;
                        System.out.println("For While");

                        long oldtime = System.currentTimeMillis();
                        while(((i = in.read()) != -1) && !(System.currentTimeMillis() > oldtime+50)) {
                            //s = in.readLine(true);

                            gui.addText(decompile(i));
                            System.out.println(c);
                            c++;
                        }
                        System.out.println("After While");
                    }
                } catch (IOException e) {
                    gui.addText(e.getLocalizedMessage());
                    System.out.println(e);
                }


                try {
                    //gui.addText(s);
                    if (tickCounter % (1 / TASK_PERIOD) == 0) {
                        out.write(192);
                        out.write(213);
                        out.write(192);
                        System.out.println("Heartbeat");
                    }

                    out.flush();
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
