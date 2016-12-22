package com.qywenji.product.module.mq;

import com.alibaba.fastjson.JSONObject;
import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by CAI_GC on 2016/12/22.
 */
@Component
public class ProductQueueMessageProducer {
    private static JmsTemplate jmsTemplate;
    private static ActiveMQDestination destination;

    @Resource(name="productQueue")
    public  void setDestination(ActiveMQDestination destination) {
        ProductQueueMessageProducer.destination = destination;
    }
    @Autowired
    public  void setJmsTemplate(JmsTemplate jmsTemplate) {
        ProductQueueMessageProducer.jmsTemplate = jmsTemplate;
    }

    public static void send(Object object) {
        jmsTemplate.convertAndSend(destination, JSONObject.toJSONString(object));
    }
}
