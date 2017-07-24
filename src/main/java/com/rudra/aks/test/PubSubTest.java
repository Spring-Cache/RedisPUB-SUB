package com.rudra.aks.test;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.rudra.aks.RedisAppConfiguration;
import com.rudra.aks.publisher.MessagePublisherImpl;

public class PubSubTest {
	
	private static final Logger logger = Logger.getLogger(PubSubTest.class);
	
	
	public static void main(String[] args) {
		AbstractApplicationContext appContext = new AnnotationConfigApplicationContext(RedisAppConfiguration.class);
		MessagePublisherImpl publisher = appContext.getBean(MessagePublisherImpl.class);
				
		logger.info("Started publish to channel ...");
		publisher.publishMessage("hello qqqq!");
		
		logger.info("Subscriber started listening...");
		publisher.publishMessage("hello aks!");
		appContext.close();
	}

}
