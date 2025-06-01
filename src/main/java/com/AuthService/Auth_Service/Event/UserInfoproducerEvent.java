package com.AuthService.Auth_Service.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.AuthService.Auth_Service.model.UserEvent;

@Service
public class UserInfoproducerEvent {

    // private final KafkaTemplate<String, UserEvent> kafkaTemplate;
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String userTopic;

    @Autowired
    UserInfoproducerEvent(KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserEventToKafka(UserEvent event) {
        kafkaTemplate.send(userTopic, event);
        System.out.println("Event published: " + event);
    }

}
