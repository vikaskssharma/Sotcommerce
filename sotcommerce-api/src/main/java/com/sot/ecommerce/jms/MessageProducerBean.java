package com.sot.ecommerce.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
 
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

 
public class MessageProducerBean {
 
 //JMS Template object
 private JmsTemplate jmsTemplate;
 private Destination destination;
 public void setJmsTemplate(JmsTemplate jmsTemplate) {
     this.jmsTemplate = jmsTemplate;
 }
 public void setDestination(Destination destination) {
     this.destination = destination;
 }
 public void sendMessage(final MessageObject messageObj) {
  jmsTemplate.send(destination, new MessageCreator() {
  public Message createMessage(Session session) throws JMSException {
        MapMessage message = session.createMapMessage();
        message.setString("mailId", messageObj.getMailId());
        message.setString("message", messageObj.getMessage());
        return message;
       }
  }); //send method close here
 }//method ends here
}