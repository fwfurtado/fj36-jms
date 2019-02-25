package br.com.caelum.chat;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;

public class ChatSession {
    private final Queue queue;
    private User user;
    private JMSProducer producer;

    public ChatSession(User user) throws Exception {
        this.user = user;


        InitialContext initialContext = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) initialContext.lookup("jms/RemoteConnectionFactory");

        this.queue = (Queue) initialContext.lookup("jms/CHAT");

        JMSContext context = factory.createContext("jms", "jms2");

        this.producer = context.createProducer();
    }

    public void send(String message) {
        producer.send(queue, new ChatMessage(user, message));
    }
}
