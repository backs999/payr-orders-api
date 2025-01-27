package uk.co.payr.payrordersapi.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uk.co.payr.payrordersapi.order.model.Order;

import java.util.Set;

public interface OrderService {

    Page<Order> getOrders(final Pageable pageable);

    Order placeOrder(final Order order);
}
