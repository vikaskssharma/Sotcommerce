package com.sot.ecommerce.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;



public class MessageSend {

	public static void main(String[] args) throws JMSException {
		// TODO Auto-generated method stub

		InitialContext initCtx;
		try {
			initCtx = new InitialContext();
		
        Context envCtx = (Context) initCtx.lookup("java:comp/env");

		ConnectionFactory connectionFactory = (ConnectionFactory) envCtx
				.lookup("jms/mqConnectionFactory");

		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		TextMessage message = session.createTextMessage();
		message.setText("My text message was send and received");
		connection.start();

		Destination destination = session.createQueue("jms/myQueue");
		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		System.out.println(message+"messagesent");
		System.out.println("messager sent");
		
		producer.send(message);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
