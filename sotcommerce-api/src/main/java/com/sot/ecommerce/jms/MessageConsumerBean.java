package com.sot.ecommerce.jms;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.print.attribute.standard.Destination;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;

 
public class MessageConsumerBean{
 
 private JmsTemplate jmsTemplate;
 private Destination destination;
   
 public void setJmsTemplate(JmsTemplate jmsTemplate) {
     this.jmsTemplate = jmsTemplate;
 }
 public void setDestination(Destination destination) {
     this.destination = destination;
 }
  
 public MessageObject receiveMessage() {
  MapMessage message = (MapMessage) jmsTemplate.receive(destination);
  try {
       MessageObject messageObj = new MessageObject();
       messageObj.setMailId(message.getString("mailId"));
       messageObj.setMessage(message.getString("message"));
       return messageObj;
       } catch (JMSException e) {
         throw JmsUtils.convertJmsAccessException(e);
       }
   }
}
