package com.example.garden.service;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class MqttMessageHandler implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) {

        String topic = message.getHeaders()
                .get("mqtt_receivedTopic")
                .toString();

        String payload = message.getPayload().toString();

        System.out.println("MQTT RECEIVED: " + topic + " → " + payload);

        // TODO:
        // 1. Extract deviceId from topic
        // 2. Determine sensor type
        // 3. Save to DB
    }
}