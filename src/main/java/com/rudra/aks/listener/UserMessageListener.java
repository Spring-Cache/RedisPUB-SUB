package com.rudra.aks.listener;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;


@Component
public class UserMessageListener implements MessageListener {

	/**
	 * Logger
	*/
	private static final Logger logger = Logger.getLogger(UserMessageListener.class);
	public static List<String>	messageList = new ArrayList<String>();
	
 	@Override
	public void onMessage(Message message, byte[] pattern) {
		String serMsg = new StringRedisSerializer().deserialize(message.getBody());
		messageList.add(serMsg);
		//Add processing upon receiving message from channel pattern
		logger.info("Message Received : " + serMsg);
	}
 	
 	
 	public String	messageList() {
 		return messageList.toString();
 	}

}
