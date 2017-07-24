package com.rudra.aks.listener;

import org.apache.log4j.Logger;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;


@Component
public class UserMessageListener implements MessageListener {

	/**
	 * Logger
	*/
	private static final Logger logger = Logger.getLogger(UserMessageListener.class);
	
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		logger.info("Begin: " + getClass().getName() + " onMessage()");
		//Add processing upon receiving message from channel pattern
		logger.info("Message Received : " + message.toString());
	}

}
