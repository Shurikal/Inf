package chat.tcp.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPMultiClient
{
    private Socket clientSocket;
    public static void main(String argv[]) throws Exception
    {
        String sentence;
        String modifiedSentence;

        BufferedReader inFromUser =
                new BufferedReader(
                        new InputStreamReader(System.in));

        Socket clientSocket = new Socket("localhost", 6789);
        System.out.println("Server Start");

        while (true) {
            DataOutputStream outToServer =
                    new DataOutputStream(
                            clientSocket.getOutputStream());

            BufferedReader inFromServer =
                    new BufferedReader(
                            new InputStreamReader(
                                    clientSocket.getInputStream()));

            sentence = inFromUser.readLine();

            outToServer.writeBytes(sentence + '\n');

            if (sentence.equals("EXIT")) {
                break;
            }

            modifiedSentence = inFromServer.readLine();

            System.out.println("FROM SERVER: " + modifiedSentence);

        }
        clientSocket.close();
    }
    public void connect(int port)
    {
        try {
            clientSocket = new Socket("localhost", port);
            System.out.println("Server Start");
        }catch (Exception e)
        {

        }
    }

    public void sendMessage(String s)
    {
        try {
            System.out.println("asdf");
            DataOutputStream outToServer =
                    new DataOutputStream(
                            clientSocket.getOutputStream());
            outToServer.writeBytes(s + '\n');
            System.out.println("fdsa");
        } catch (Exception e)
        {

        }
    }

    public void disconnect()
    {
        try {
            clientSocket.close();
        } catch (Exception e)
        {

        }
    }
}
