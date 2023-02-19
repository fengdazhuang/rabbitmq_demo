package com.fzz.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class GetChannel {
    public static Channel getChannel() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("43.142.253.194");
        factory.setUsername("admin");
        factory.setPassword("123");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        return channel;
    }
}
