package robControlPanel;

import java.io.DataInputStream;
import java.net.Socket;

public class Rob_Receiver implements Runnable
{
    private DataInputStream in;
    private Socket socket;
    private GUI gui;

    public Rob_Receiver(Socket socket, GUI gui) {
        this.socket = socket;
        this.gui = gui;
    }

    public Rob_Receiver(Socket socket) {
        this.socket = socket;
        this.gui = null;
    }

    public void run(){
        //Try to create a new InputStream
        try {
            in = new DataInputStream(socket.getInputStream());
        }catch (Exception e){
            System.out.println("Could not create Input");
        }

        int i;


        while(!socket.isClosed()){
            try{
                while(((i = in.read()) != -1)) {

                gui.addText(""+i);

                }
            }catch (Exception e) {

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


}

