package com.rudra.aks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.rudra.aks.listener.UserMessageListener;

@Configuration
@ComponentScan(basePackages="com.rudra.aks.*")
public class RedisAppConfiguration {

	/*
	 * 
	 */
	
	@Bean
	public JedisConnectionFactory	connectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName("localhost");
		factory.setPort(6379);
		return factory;
	}
	
	@Bean
	public RedisTemplate<String, Object>	template() {
		RedisTemplate<String, Object>	template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(connectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		return template;
	}
	
	/**
	 * Configuring message adapter
	 * user message listener will listener/process upon listening to any message in configured container's topic
	 * @return
	 */
	@Bean
	public MessageListenerAdapter	messageListener() {
		return new MessageListenerAdapter( new UserMessageListener());
	}
	
	@Bean
	public RedisMessageListenerContainer	listenerContainer() {
		RedisMessageListenerContainer redisContainer = new RedisMessageListenerContainer();
		redisContainer.setConnectionFactory(connectionFactory());
		redisContainer.addMessageListener(messageListener(), topic());
		return redisContainer;
	}
	
	@Bean
	public ChannelTopic	topic() {
		return new ChannelTopic("channel: user");
	}
}
