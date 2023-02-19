package com.fzz.rabbitmq.test2;

import com.fzz.rabbitmq.utils.GetChannel;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class Consumer2 {
    private static final String DEAD_QUEUE="dead_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = GetChannel.getChannel();
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println("Consumer2接收消息："+new String(message.getBody()));
        };
        channel.basicConsume(DEAD_QUEUE,true,deliverCallback,consumerTag->{});
    }
}
