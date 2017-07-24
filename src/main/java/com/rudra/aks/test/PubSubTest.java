package com.rudra.aks.test;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.rudra.aks.RedisAppConfiguration;
import com.rudra.aks.listener.UserMessageListener;
import com.rudra.aks.publisher.MessagePublisher;

public class PubSubTest {
	
	private static final Logger logger = Logger.getLogger(PubSubTest.class);
	
	
	public static void main(String[] args) {
		ApplicationContext appContext = new AnnotationConfigApplicationContext(RedisAppConfiguration.class);
		MessagePublisher publisher = appContext.getBean(MessagePublisher.class);
				
		logger.info("Started publish to channel ...");
		publisher.publishMessage("hello avro!");
		
		logger.info("Subscriber started listening...");
		//listener.onMessage(message, pattern);
	}

}
