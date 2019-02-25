package br.com.caelum.topic.consumer;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Topic;
import javax.naming.InitialContext;
import java.util.Scanner;

public class NotaFiscal {
    public static void main(String[] args) throws Exception {
        InitialContext initialContext = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) initialContext.lookup("jms/RemoteConnectionFactory");

        Topic topic = (Topic) initialContext.lookup("jms/TOPICO.FINANCEIRO");

        Scanner keyboard = new Scanner(System.in);

        try(JMSContext context = factory.createContext("jms", "jms2")) {

            context.setClientID("NF");

            JMSConsumer consumer = context.createDurableConsumer(topic, "NF",
                    "rating >= 0.7", false);

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
