package robControlPanel;

public class SLIP {

    private Byte[] buffer = new Byte[1024];
    private int counter;
    private boolean packetComplete;


    private static final byte END = (byte)0300;
    private static final byte ESC = (byte)0333;
    private static final byte ESC_END = (byte)0334;
    private static final byte ESC_ESC = (byte)0335;

    public SLIP() {
        packetComplete= false;
        counter =0;
    }

    public int deparse(Byte[] b) {

        return 0;
    }

    public Byte[] parse(int i) {
        Byte[] b = new Byte[4];

        return b;
    }

    public void addByte(Byte b){

        if(b == END && counter==0){
            //Data Packet begins
            buffer[0] = b;
            counter ++;
        }else if(b!= END && counter !=0){
            //Normal Data
            buffer[counter] = b;
            counter ++;
        }else if(b== END && counter>0) {
            buffer[counter] = b;
            packetComplete = true;
        }
        if(b == ESC_END && buffer[counter-1] == ESC){
            buffer[counter-1] = END;
        }else if(b == ESC_ESC && buffer[counter-1]== ESC){
            buffer[counter-1] = ESC;
        }

    }

    public boolean packetComplete(){
        if(buffer.length <2){
            return false;
        }
        if(buffer[counter] == 192){
            return true;
        }else{
            return false;
        }
    }
}
