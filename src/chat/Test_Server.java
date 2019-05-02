package chat;

import chat_vorlagen.message.Communication;

public class Test_Server
{
    public static void main(String[] args)
    {
        Communication cm = new Communication();
        cm.open(5678);
        while(true) {

            cm.waitForMessage();
            System.out.println(cm.getMessage());
        }
    }
}
