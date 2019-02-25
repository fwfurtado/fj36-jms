package br.com.caelum.topic.producer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.naming.InitialContext;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws Exception {

        InitialContext initialContext = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) initialContext.lookup("jms/RemoteConnectionFactory");

        Destination topic = (Destination) initialContext.lookup("jms/TOPICO.FINANCEIRO");

        try( JMSContext context = factory.createContext("jms", "jms2") ) {

            JMSProducer producer = context.createProducer();
//            producer.setProperty("rating", 0.5);

            System.out.println("Enviando 10 mensagens");

            IntStream.range(1, 11)
                    .peek(System.out::println)
                    .forEach(i -> producer.send(topic, String.format("Message [%d]", i)));
        }
    }
}
