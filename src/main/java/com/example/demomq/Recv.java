package com.example.demomq;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

public class Recv {
    private final static String QUEUE_NAME = "Hello!!";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.56.105");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] 等待訊息中. 結束請輸入 CTRL+C");

        DeliverCallback deliverCallback = (consumerTag,delivery) -> {
            String message =  new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("[x] 傳送'" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});

    }
}
