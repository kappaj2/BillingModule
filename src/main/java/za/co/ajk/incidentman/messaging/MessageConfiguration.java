package za.co.ajk.incidentman.messaging;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding({ConsumerChannels.class, ProducerChannels.class})
public class MessageConfiguration {
}
