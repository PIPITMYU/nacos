package com.demo.rocketmq.example.base.consumer;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/1/29
 * Time: 15:52
 * Created with IntelliJ IDEA
 * Description: No Description
 */
public class Consumer2 {
    public static void main(String[] args) throws Exception {
        //1.创建消费者consumer,制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        //2.指定NameServer地址
        consumer.setNamesrvAddr("localhost:9876");
        //3.订阅主题Topic和Tage
//        consumer.subscribe("base","*"); //消费topic下所有tag
        consumer.subscribe("base","tag1 || tag2 || tag3");

        //重试次数设置 默认的重试次数是16次 daley等级从3开始3-18
        consumer.setMaxReconsumeTimes(2);
        //设置消费模式
//        consumer.setMessageModel();
        //4.设置回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            //接收消息内容
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for(MessageExt messageExt:list) {
                    try {
                        System.out.println(messageExt);
                        System.out.println("consumer:"+new String(messageExt.getBody()));
                        int i = 1/0;
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }catch (Exception e){
                        if(messageExt.getReconsumeTimes() >= 3){
                            //最多重试 3次
                            System.out.println("max reconsume times");
                            return  ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                        return  ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

        });
        //5.启动消费者
        consumer.start();
        System.out.println("consumer2:启动成功");
    }
}
