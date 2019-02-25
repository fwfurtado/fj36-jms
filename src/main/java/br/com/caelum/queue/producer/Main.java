package br.com.caelum.queue.producer;

import java.util.stream.IntStream;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {

    public static void main(String[] args) throws NamingException {

        InitialContext initialContext = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");

        Queue queue = (Queue) initialContext.lookup("queues/FILA.FINANCEIRO");

        try(JMSContext context = factory.createContext("admin", "admin")) {

            JMSProducer producer = context.createProducer();

            producer.send(queue, "Helloo!");

//            IntStream.range(1, 11)
//                .peek(System.out::println)
//                .mapToObj(number -> context.createTextMessage(String.format("Message [%d]", number)) )
//                .forEach(message -> producer.send(queue, message));
        }
    }
}
