package uk.co.payr.payrordersapi.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uk.co.payr.payrordersapi.order.model.Order;
import uk.co.payr.payrordersapi.order.service.OrderService;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Flogger
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<Order>> orders(Pageable pageable) {
        return ResponseEntity.ok(orderService.getOrders(pageable));
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@Validated @RequestBody Order order) {
        log.atInfo().log("Placing order: %s", order);
        return ResponseEntity.ok(orderService.placeOrder(order));
    }
}
