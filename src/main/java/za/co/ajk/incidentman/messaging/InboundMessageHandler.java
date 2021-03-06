package za.co.ajk.incidentman.messaging;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;

import com.rabbitmq.client.Channel;

public interface InboundMessageHandler {
    
    void receive(Message<ExchangeMessage> m,
                 @Header(AmqpHeaders.CHANNEL) Channel channel,
                 @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag);
    
    void receiveEventBroadCasts(Message<ExchangeMessage> m,
                                @Header(AmqpHeaders.CHANNEL) Channel channel,
                                @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag);
}
