package chat;

import chat_vorlagen.message.Message;
import chat_vorlagen.tests.MessageTest;

public class Test_Client
{
    public static void main(String[] args)
    {
        MessageTest mt = new MessageTest();
        mt.sendeMessage("pc1313.ntb.ch");
    }
}
