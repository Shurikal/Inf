package chat_vorlagen.tests;

import java.io.*;
import java.net.*;

public class DatagramTest
{
    DatagramSocket socket;

    public DatagramTest() {
        // hier kommt Ihr Code
    }

    public DatagramTest(int meinePortNummer) {
        // hier kommt Ihr Code
    }

    public void sendeDatagramm (String destinationsHostName, int destinationsPortNummer){
        String text = "Hoi Du.";
        byte[] inhalt = text.getBytes();
        // hier kommt Ihr Code
    }

    public void empfangeDatagramm (){
        byte[] empfangsPuffer = new byte[2048];
        DatagramPacket receivePacket = null;
        // hier kommt Ihr Code
    }

    public void beenden() {
        if (socket != null){
            socket.close();
        }
    }

}
