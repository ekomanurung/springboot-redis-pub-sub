package com.learning.redis.pubsub.subscriber;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
@Slf4j
public class MessageListener {
  public void onReceive(String message) {
    log.info("receive message with data: {}", message);
  }
}
