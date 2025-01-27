package uk.co.payr.payrordersapi.order.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.co.payr.payrordersapi.order.model.Order;

public interface OrderRepository extends CrudRepository
        <Order, String>, PagingAndSortingRepository<Order, String> {
}
