package uk.co.payr.payrordersapi.order.model.event;

import lombok.Builder;

@Builder
public record OrderEvent(String userId, String orderId) {
}
