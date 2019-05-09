package chat.chat_vorlagen.tests;

import chat.chat_vorlagen.message.*;

public class MessageTest
{
    Communication communication;

    public MessageTest() {
        communication = new Communication();
    }

    public void sendeMessage(String host){
        PostingMessage pm = new PostingMessage("Chris", "Hallo");
        communication.open();
        communication.sendMessage(host,5678,pm);
    }

    public void empfangeMessage() {
        // hier kommt Ihr Code zum Empfang einer beliebigen Message
    }

    public void empfangeMessages () {
        while (true)
            empfangeMessage ();
    } 

    public void beenden() {
        communication.close();
    }
}
