package com.learning.redis.pubsub;

import com.learning.redis.pubsub.configuration.ChannelProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ChannelProperties.class})
public class LearningRedisPubsubApplication {

  public static void main(String[] args) {
    SpringApplication.run(LearningRedisPubsubApplication.class, args);
  }
}
