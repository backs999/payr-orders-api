package uk.co.payr.payrordersapi.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.co.payr.payrordersapi.order.data.OrderRepository;
import uk.co.payr.payrordersapi.order.model.Order;
import uk.co.payr.payrordersapi.order.model.event.OrderEvent;

@Service
@RequiredArgsConstructor
@Flogger
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final EventService eventService;

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order placeOrder(Order order) {

        log.atInfo().log("Placing order for user " + order.getOrderedByUserId());
        final var saved = orderRepository.save(order);

        eventService.sendEvent(OrderEvent.builder()
                .userId(saved.getOrderedByUserId())
                .orderId(saved.getId())
                .build());

        log.atInfo().log("Order placed for user " + order.getOrderedByUserId() + " under order ID " + order.getId());
        return saved;
    }
}
