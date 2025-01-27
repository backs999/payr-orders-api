package uk.co.payr.payrordersapi.order.service;

import uk.co.payr.payrordersapi.order.model.event.OrderEvent;

public interface EventService {

    void sendEvent(final OrderEvent orderEvent);

}
