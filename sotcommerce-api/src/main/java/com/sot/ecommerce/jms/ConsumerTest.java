package com.sot.ecommerce.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

 
public class ConsumerTest {
 public static void main(String[] args) {
    ApplicationContext context =
      new ClassPathXmlApplicationContext("appContext.xml");
    MessageConsumerBean mc =
     (MessageConsumerBean) context.getBean("consumer");
    MessageObject messageObj = mc.receiveMessage();
    System.out.println("Message from " +
     messageObj.getMailId() + " received");
    }
}