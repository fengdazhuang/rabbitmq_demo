package com.fzz.rabbitmq.test1;

import com.fzz.rabbitmq.utils.GetChannel;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;


public class Consumer2 {
    private static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws Exception {
        Channel channel2 = GetChannel.getChannel();
        System.out.println("消费者2接收信息");
        DeliverCallback deliverCallback=(consumerTag,delivery)->{
            String message= new String(delivery.getBody());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(message);
            channel2.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        };

        CancelCallback cancelCallback=consumerTag->{
            System.out.println("队列突然终止");
        };
        channel2.basicQos(1);
        channel2.basicConsume(QUEUE_NAME,false,deliverCallback,cancelCallback);
    }
}
