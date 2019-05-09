package chat;

import chat.chat_vorlagen.message.Communication;
import chat.chat_vorlagen.message.Message;
import chat.chat_vorlagen.message.PostingMessage;

import javax.swing.*;


public class Receiver extends Thread
{
    private Communication communication;
    private GUI gui;

    public Receiver(Communication communication, GUI gui)
    {
        this.communication = communication;
        this.gui = gui;
    }

    public void run () {
        while (true) {
            try {
                communication.waitForMessage();

                Message message = communication.getMessage();
                if(message instanceof PostingMessage)
                {
                    Runnable appendText = new Runnable() {
                        @Override
                        public void run() {
                            PostingMessage pm = (PostingMessage) message;
                            gui.addMessage(pm);
                        }
                    };
                    SwingUtilities.invokeLater(appendText);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
