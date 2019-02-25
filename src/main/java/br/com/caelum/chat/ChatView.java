package br.com.caelum.chat;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class ChatView {
    public static void main(String[] args) throws NamingException, InterruptedException {
        InitialContext initialContext = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) initialContext.lookup("jms/RemoteConnectionFactory");

        Queue queue = (Queue) initialContext.lookup("jms/CHAT");

        Scanner keyboard = new Scanner(System.in);

        try(JMSContext context = factory.createContext("jms", "jms2")) {

            JMSConsumer consumer = context.createConsumer(queue);

            consumer.setMessageListener( message -> {
                try {


                    ChatMessage chatMessage = message.getBody(ChatMessage.class);

                    System.out.println(chatMessage.toView());

                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });

            context.start();

            keyboard.next();

            context.stop();
        }
    }
}
