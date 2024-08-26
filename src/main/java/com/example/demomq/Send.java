package com.example.demomq;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

public class Send {
    private final static String QUEUE_NAME = "Hello!!";

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.56.105");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);

            String message = "Hello World";
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println(" [x] 送訊息 '" + message + "'");

        }
    }
}
