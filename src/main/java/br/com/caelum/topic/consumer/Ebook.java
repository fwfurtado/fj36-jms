package br.com.caelum.topic.consumer;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.naming.InitialContext;
import java.util.Scanner;

public class Ebook {

    public static void main(String[] args) throws Exception {
        InitialContext initialContext = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) initialContext.lookup("jms/RemoteConnectionFactory");

        Topic topic = (Topic) initialContext.lookup("jms/TOPICO.FINANCEIRO");

        Scanner keyboard = new Scanner(System.in);

        try(JMSContext context = factory.createContext("jms", "jms2")) {

//            context.setClientID("Ebook");

        JMSConsumer consumer = context.createSharedDurableConsumer(topic, "ebook", "");

            consumer.setMessageListener(message -> {
                try {
                    System.out.println(message.getBody(String.class));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });

            context.start();
            System.out.println("Esperando mensagens: ");
            keyboard.next();

            context.stop();
        }
    }
}
