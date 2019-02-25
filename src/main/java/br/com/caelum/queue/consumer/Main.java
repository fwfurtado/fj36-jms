package br.com.caelum.queue.consumer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NamingException, InterruptedException {
        InitialContext initialContext = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");

        Queue queue = (Queue) initialContext.lookup("queues/FILA.FINANCEIRO");

        Scanner keyboard = new Scanner(System.in);

        try(JMSContext context = factory.createContext("admin", "admin")) {
//
//            JMSProducer producer = context.createProducer();
//            producer.send(queue, "HElloo!");
            JMSConsumer consumer = context.createConsumer(queue);

            consumer.setMessageListener(new LogListener());

            context.start();

            keyboard.next();

            context.stop();
        }
    }
}


class LogListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
//            throw new RuntimeException("Deu ruim " + textMessage.getText());
            System.out.println(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}


















