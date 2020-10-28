package com.learning.redis.pubsub.configuration;

import com.learning.redis.pubsub.subscriber.MessageListener;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
@AllArgsConstructor
public class RedisConfiguration {

  private final ChannelProperties channelProperties;

  @Bean
  public MessageListenerAdapter listenerAdapter(MessageListener messageListener){
    return new MessageListenerAdapter(messageListener, "onReceive");
  }

  @Bean
  public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter) {

    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(listenerAdapter, new ChannelTopic(channelProperties.getHeadline()));

    return container;
  }

  @Bean
  ReactiveRedisTemplate<String, String> stringReactiveRedisTemplate(ReactiveRedisConnectionFactory redisConnectionFactory) {
    return new ReactiveRedisTemplate<>(redisConnectionFactory, RedisSerializationContext.string());
  }
}
