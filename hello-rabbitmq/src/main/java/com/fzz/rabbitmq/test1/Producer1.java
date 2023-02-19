package com.fzz.rabbitmq.test1;

import com.fzz.rabbitmq.utils.GetChannel;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.util.Scanner;


public class Producer1 {
    private static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws Exception{
        Channel channel = GetChannel.getChannel();
        //开启发布确认
        channel.confirmSelect();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        System.out.println("生产者1发送信息");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String message=scanner.next();
            channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        }


    }
}
