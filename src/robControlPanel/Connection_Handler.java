package robControlPanel;

public class Connection_Handler implements Runnable
{

    GUI gui;

    private static Rob_Connection rob1, rob2;

    public Connection_Handler(GUI gui) {
    this.gui = gui;
    }

    public void connect_Rob1() {
        rob1 = new Rob_Connection("169.254.1.1",2000);
    }

    public void connect_Rob2() {
        rob2 = new Rob_Connection("169.254.1.2",2000);
    }

    public void disconnect_Rob1() {
        if(rob1!=null) {
            rob1.disconnect();
        }
        rob1 = null;
    }

    public void disconnect_Rob2() {
        if(rob2!=null){
        rob2.disconnect();
        }
        rob2 = null;
    }

    public boolean connected_Rob1() {
        if(rob1 == null) {
            return false;
        }
        return rob1.connected();
    }

    public Rob_Connection getRob1(){
        if(rob1!=null){
            return rob1;
        }else{
            return null;
        }
    }

    public Rob_Connection getRob2(){
        if(rob2!=null){
            return rob2;
        }else{
            return null;
        }
    }

    public boolean connected_Rob2() {
        if(rob2 == null) {
            return false;
        }
        return rob2.connected();
    }

    public static void sendDataRob1(int i){
        if(rob1 != null){
            rob1.cmd.writeCmd(i);
        }
    }

    public static void sendDataRob2(int i){
        if(rob2 != null){
            rob2.cmd.writeCmd(i);
        }
    }

    @Override
    public void run() {
        while(true){

            //Reads data from Rob1
            if(rob1 !=null) {
                while (rob1.cmd.readCmd() == CmdInt.Type.Cmd) {
                    int i = rob1.cmd.getInt();
                    gui.addText(i + " <- Rob1");
                    if(rob2 != null){
                        rob2.cmd.writeCmd(i);
                    }
                }
            }

            if(rob2 !=null) {
                while (rob2.cmd.readCmd() == CmdInt.Type.Cmd) {
                    int i = rob2.cmd.getInt();
                    gui.addText(i + " <- Rob1");
                    if(rob1 !=null){
                        rob1.cmd.writeCmd(i);
                    }
                }
            }

            try{
                Thread.sleep(5);
            }catch (Exception e){}
        }
    }
}
