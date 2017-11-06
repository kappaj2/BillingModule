package za.co.ajk.incidentman.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannels {
    
    String BILLING_INPUT = "billing-input";
    String BROADCASTS = "broadcasts";
    
    @Input(BILLING_INPUT)
    SubscribableChannel eventInputChannel();
    
    @Input(BROADCASTS)
    SubscribableChannel broadcastChannel();
    
}
