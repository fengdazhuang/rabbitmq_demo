package com.fzz.rabbitmq.test2;

import com.fzz.rabbitmq.utils.GetChannel;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;
import java.util.Map;

public class Consumer1 {
    private static final String NORMAL_EXCHANGE="normal_exchange";
    private static final String DEAD_EXCHANGE="dead_exchange";
    private static final String NORMAL_QUEUE="normal_queue";
    private static final String DEAD_QUEUE="dead_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = GetChannel.getChannel();
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE,BuiltinExchangeType.DIRECT);

        channel.queueDeclare(DEAD_QUEUE,false,false,false,null);
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"lisi");

        Map<String,Object> map=new HashMap<>();
        map.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key","lisi");
//        map.put("x-max-length",6);
        channel.queueDeclare(NORMAL_QUEUE,false,false,false,map);

        channel.queueBind(NORMAL_QUEUE,NORMAL_EXCHANGE,"zhangsan");



        DeliverCallback deliverCallback=(consumerTag,message)->{
            String msg = new String(message.getBody());
            if(msg.equals("info5")){
                channel.basicReject(message.getEnvelope().getDeliveryTag(),false);
                System.out.println("消息"+msg+"此消息未被接受");
            }else{
                channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
                System.out.println("消息"+msg+"接受");
            }
        };
        channel.basicConsume(NORMAL_QUEUE,false,deliverCallback,consumerTag->{});

    }
}
