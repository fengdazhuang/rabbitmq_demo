package com.fzz.rabbitmq.test2;

import com.fzz.rabbitmq.utils.GetChannel;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

public class Producer {

    private static final String NORMAL_EXCHANGE="normal_exchange";

    public static void main(String[] args) throws Exception{
        Channel channel = GetChannel.getChannel();
//        AMQP.BasicProperties properties=new AMQP.BasicProperties().builder().expiration("10000").build();
        for(int i=1;i<11;i++){
            String message="info"+i;
            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",null,message.getBytes());
        }

    }
}
