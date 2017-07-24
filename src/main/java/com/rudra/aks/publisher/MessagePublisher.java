package com.rudra.aks.publisher;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {
	
	/*
	 * Logger
	 */
	public static final Logger logger = Logger.getLogger(MessagePublisher.class);
	private final String CHANNLE = "USER_CHANNEL";
	
	private byte[] channelByte;
	
	
	@Autowired
	RedisTemplate<String, Object> template;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		channelByte = ((RedisSerializer<String>)template.getKeySerializer()).serialize(CHANNLE);
		//messageByte = ((RedisSerializer<String>)template.getKeySerializer()).serialize(MESSAGE);
	}
	
	public void publishMessage(final String message) {
		logger.info("Begin : " + getClass().getName() + " : publishMessage()");
		try {
			long publishResult = template.execute(new RedisCallback<Long>() {

				@Override
				public Long doInRedis(RedisConnection connection) throws DataAccessException {
					return connection.publish(channelByte, 
							((RedisSerializer<String>)template.getKeySerializer()).serialize(message));
				}
				
			});
			logger.info("Message published to channel : " + CHANNLE + " : " + publishResult);
		} catch (Exception e) {
			logger.info("Exception while pulishing message to channel : " + e);
		}
		logger.info("End : " + getClass().getName() + " : publishMessage()");
		
	}
}
