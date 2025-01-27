package uk.co.payr.payrordersapi.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import uk.co.payr.payrordersapi.order.config.KafkaConfigProps;
import uk.co.payr.payrordersapi.order.model.event.OrderEvent;

@Service
@RequiredArgsConstructor
@Flogger
public class EventServiceImpl implements  EventService {

    private final ObjectMapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfigProps kafkaConfigProps;

    @Override
    public void sendEvent(OrderEvent orderEvent) {
        try {
            log.atInfo().log("Sending order event: %s", orderEvent);
            kafkaTemplate.send(kafkaConfigProps.getTopicOrdersNew(), mapper.writeValueAsString(orderEvent)).thenAcceptAsync(result -> {
                log.atInfo().log("Order event sent: %s", result);
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
