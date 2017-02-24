package com.sot.ecommerce.jms;

import java.util.HashMap;
import java.util.Map;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Autowired;

public class MessageReceiver implements MessageListener {
	 @Autowired
	private MessageSender sender;
    public MessageSender getSender() {
		return sender;
	}
	public void setSender(MessageSender sender) {
		this.sender = sender;
	}
	public void onMessage(final Message message) {
        if (message instanceof MapMessage) {
            final MapMessage mapMessage = (MapMessage) message;
            
            Map map = new HashMap();
            map.put("Name", "MYNAME");
            map.put("testqueue","testQueueOne");
            
           sender.send(map);
           System.out.println("messsage send");
        }
    }
 
}