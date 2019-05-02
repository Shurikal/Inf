package chat;

import chat_vorlagen.message.Message;
import chat_vorlagen.tests.MessageTest;

public class Test_Client
{
    public static void main(String[] args)
    {
        MessageTest mt = new MessageTest();

        mt.sendeMessage("PCklz508.ntb.ch");
        mt.sendeMessage("desktop-36f9d6s.ntb.ch");
    }
}
