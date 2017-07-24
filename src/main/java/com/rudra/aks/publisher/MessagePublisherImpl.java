package com.rudra.aks.publisher;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisherImpl implements MessagePublisher{
	
	/*
	 * Logger
	 */
	public static final Logger logger = Logger.getLogger(MessagePublisherImpl.class);
	private byte[]	channelByte;
	
	
	@Autowired
	RedisTemplate<String, Object> template;
	
	@Autowired
	ChannelTopic	topic;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		channelByte = ((RedisSerializer<String>)template.getKeySerializer()).serialize(topic.getTopic());
		//messageByte = ((RedisSerializer<String>)template.getKeySerializer()).serialize(MESSAGE);
	}
	
	@Override
	public void publishMessage(final String message) {
		logger.info("Begin : " + getClass().getName() + " : publishMessage()");
		try {
			long publishResult = template.execute(new RedisCallback<Long>() {

				@SuppressWarnings("unchecked")
				@Override
				public Long doInRedis(RedisConnection connection) throws DataAccessException {
					return connection.publish(channelByte, 
							((RedisSerializer<String>)template.getKeySerializer()).serialize(message));
				}
				
			});
			logger.info("Message published to : " + topic.getTopic() + " : noOfClients : " + publishResult);
		} catch (Exception e) {
			logger.info("Exception while pulishing message to channel : " + e);
		}
		//template.convertAndSend(topic.getTopic(), message);
		logger.info("End : " + getClass().getName() + " : publishMessage()");
		
	}
}
