package robControlPanel;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Rob_Sender implements Runnable {
    private DataOutputStream out;
    private Socket socket;

    private static final byte OPEN = (byte)0xd5;
    private static final byte CLOSE = (byte)0xd4;
    private static final byte HEARTBEAT = (byte)0xd3;
    private static final byte CMD = '$';

    private static final byte SLIP_CMD = (byte)0xc0;

    private GUI gui;


    public Rob_Sender(Socket socket, GUI gui) {
        this.socket = socket;
    }

    public Rob_Sender(Socket socket) {
        this.socket = socket;
        this.gui = null;
    }

    public void run() {   //Try to create a new InputStream
        try {
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println("Could not create Input");
        }

        int i;


        while (!socket.isClosed()) {
            try {
               sendHeartbeat();
            } catch (Exception e) { }



            long oldSysTime = System.currentTimeMillis();
            while (System.currentTimeMillis() < oldSysTime + 200) {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.out.println("Could not sleep...");
                }
            }
        }
        System.out.println("Socket closed -> Output Thread terminated");
    }


    private void sendHeartbeat() throws IOException {
        out.write(HEARTBEAT);
    }

    public synchronized void sendByte(Byte b){
        try {
            out.write(b);
        }catch (Exception e){}
    }
}
