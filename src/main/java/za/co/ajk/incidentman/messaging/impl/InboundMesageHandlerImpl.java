package za.co.ajk.incidentman.messaging.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;

import com.rabbitmq.client.Channel;
import za.co.ajk.incidentman.messaging.ExchangeMessage;
import za.co.ajk.incidentman.messaging.InboundMessageHandler;

@EnableBinding(Sink.class)
public class InboundMesageHandlerImpl implements InboundMessageHandler {
    
    private final Logger log = LoggerFactory.getLogger(InboundMesageHandlerImpl.class);
    
    @Override
    @StreamListener(Sink.INPUT)
    public void receive(Message<ExchangeMessage> m,
                        @Header(AmqpHeaders.CHANNEL) Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) {
        try {
            log.info("DeliveryTag                 : " + deliveryTag);
            log.info("Channel no                  : " + channel.getChannelNumber());
            log.info("Channel next publish seq no : " + channel.getNextPublishSeqNo());
            log.info("Received ExchangeMessage message   : " + m.toString());
            
            //if (deliveryTag % 2 == 0) {
                //  Acknowledge success for test
                channel.basicAck(deliveryTag, false);   // only acknowledge this message
//            } else {
//                //  Acknowledge failure and flag for resubmit for test
//                channel.basicNack(deliveryTag, false, true);    // requeue
//            }
        } catch (IOException ioe) {
            log.error("Error acknowledging message");
        }
    }
    
}
