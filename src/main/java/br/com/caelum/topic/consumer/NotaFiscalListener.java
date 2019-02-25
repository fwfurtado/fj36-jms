package br.com.caelum.topic.consumer;

import javax.jms.Message;
import javax.jms.MessageListener;

//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.MessageDriven;
//import javax.inject.Inject;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//

//@MessageDriven(activationConfig = {
//    @ActivationConfigProperty(  propertyName = "destinationType",
//                                propertyValue = "javax.jms.Topic"),
//
//    @ActivationConfigProperty(  propertyName = "destinationLookup",
//                                propertyValue = "jms/TOPICO.LIVRARIA"),
//
//    @ActivationConfigProperty( propertyName = "messageSelector",
//                                propertyValue = "rating >= 0.7")
//
//
//})
public class NotaFiscalListener  implements MessageListener {

//    @Inject
//    private NotafiscalDao dao;


    @Override
    public void onMessage(Message message) {

    }
}
