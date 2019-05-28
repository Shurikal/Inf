package robControlPanel;

import java.io.DataInputStream;
import java.net.Socket;

public class Rob_Receiver implements Runnable
{
    private DataInputStream in;
    private Socket socket;
    private GUI gui;

    private static final byte OPEN = (byte)0xd5;
    private static final byte CLOSE = (byte)0xd4;
    private static final byte HEARTBEAT = (byte)0xd3;
    private static final byte CMD = '$';
    private static final byte ESCAPE = (byte)0xf1;
    private static final byte ESCAPED_ESCAPE = (byte)0xf3;
    private static final byte ESCAPED_OPEN = (byte)0xf2;
    private static final byte ESCAPED_CLOSE = (byte)0xf5;
    private static final byte ESCAPED_HEARTBEAT = (byte)0xe6;
    private static final byte ESCAPED_CMD = (byte)0xf0;

    private ByteFifo rx;

    private CmdInt cmd;

    private boolean rxEscapeMode = false;

    public Rob_Receiver(Socket socket, GUI gui) {
        this.socket = socket;
        this.gui = gui;
        rx = new ByteFifo(2047);
        this.cmd = new CmdInt(new SLIP(rx, null));
    }

    public Rob_Receiver(Socket socket) {
        this.socket = socket;
        this.gui = null;
        rx = new ByteFifo(2047);
        this.cmd = new CmdInt(new SLIP(rx, null));
    }

    public void run(){
        //Try to create a new InputStream
        try {
            in = new DataInputStream(socket.getInputStream());
            System.out.println("Input Stream erstellt");
        }catch (Exception e){
            System.out.println("Could not create Input");
        }

        int i;


        while(!socket.isClosed() && socket !=null){
            try{
                long oldSysTime = System.currentTimeMillis();
                while(((i = in.read()) != -1) && System.currentTimeMillis() < oldSysTime+ 50) {
                    addByte((byte)i);
                }
            }catch (Exception e) {

            }

            while(cmd.readCmd() == CmdInt.Type.Cmd){
                System.out.println("CMD read");
                if(gui!=null) {

                    gui.addText("" + cmd.getInt());
                }
            }

            long oldSysTime = System.currentTimeMillis();
            while(System.currentTimeMillis() < oldSysTime + 200)
            {
                try {
                    Thread.sleep(1);
                }catch(Exception e){
                    System.out.println("Could not sleep...");
                }
            }
        }
        System.out.println("Socket closed -> Input Thread terminated");
    }


    public void addByte(byte b){
        if (b == OPEN) {
            // ignore
        }
        else if (b == CLOSE) {
            // ignore
        }
        else if (b == HEARTBEAT) {
            // ignore
        }
        else if (rxEscapeMode) {
            if (b == ESCAPED_OPEN) {
                rx.enqueue(OPEN);
            }
            else if (b == ESCAPED_CLOSE) {
                rx.enqueue(CLOSE);
            }
            else if (b == ESCAPED_HEARTBEAT) {
                rx.enqueue(HEARTBEAT);
            }
            else if (b == ESCAPED_CMD) {
                rx.enqueue(CMD);
            }
            else if (b == ESCAPED_ESCAPE) {
                rx.enqueue(ESCAPE);
            }
            else {
                rx.enqueue(b);
            }
            rxEscapeMode = false;
        }
        else if (b == ESCAPE) {
            rxEscapeMode = true;
        }
        else {
            rx.enqueue(b);
        }
    }

}

