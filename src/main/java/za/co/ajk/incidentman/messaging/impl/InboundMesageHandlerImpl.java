package za.co.ajk.incidentman.messaging.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;

import com.rabbitmq.client.Channel;
import za.co.ajk.incidentman.messaging.ConsumerChannels;
import za.co.ajk.incidentman.messaging.ExchangeMessage;
import za.co.ajk.incidentman.messaging.InboundMessageHandler;

@EnableBinding(Sink.class)
public class InboundMesageHandlerImpl implements InboundMessageHandler {
    
    private final Logger log = LoggerFactory.getLogger(InboundMesageHandlerImpl.class);
    
    @Override
    @StreamListener(ConsumerChannels.BILLING_INPUT)
    public void receive(Message<ExchangeMessage> m,
                        @Header(AmqpHeaders.CHANNEL) Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) {
        try {
            log.info("Billing - DeliveryTag                 : " + deliveryTag);
            log.info("Channel no                  : " + channel.getChannelNumber());
            log.info("Channel next publish seq no : " + channel.getNextPublishSeqNo());
            log.info("Received ExchangeMessage message   : " + m.toString());

                channel.basicAck(deliveryTag, false);   // only acknowledge this message
        } catch (IOException ioe) {
            log.error("Error acknowledging message");
        }
    }
    
    //@Override
    @StreamListener(ConsumerChannels.BROADCASTS)
    public void receiveEventBroadCasts(Message<ExchangeMessage> m,
                                       @Header(AmqpHeaders.CHANNEL) Channel channel,
                                       @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) {
        try {
            log.info("Billing - receiveEventBroadCasts DeliveryTag : " + deliveryTag);
            log.info("Channel no                         : " + channel.getChannelNumber());
            log.info("Channel next publish seq no        : " + channel.getNextPublishSeqNo());
            log.info("Received InboundMessage message    : " + m.toString());
            
            channel.basicAck(deliveryTag, false);   // only acknowledge this message
            
        } catch (IOException ioe) {
            log.error("Error acknowledging message");
        }
    }
}
/*
//    @RabbitListener(bindings = @QueueBinding(value= @Queue(value = "billing-handler-input", durable="true" ),
//        exchange = @Exchange(value="TX", type = "topic", durable = "true"), key = "rk1"))
 */
